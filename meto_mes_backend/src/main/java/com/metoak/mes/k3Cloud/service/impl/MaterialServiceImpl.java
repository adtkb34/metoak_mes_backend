package com.metoak.mes.k3Cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.entity.MoMaterialBinding;
import com.metoak.mes.k3Cloud.service.IMaterialService;
import com.metoak.mes.mapper.MoMaterialBindingMapper;
import com.metoak.mes.service.IMoMaterialBindingService;
import com.metoak.mes.traceability.vo.MaterialBindVo;
import com.metoak.mes.traceability.vo.MaterialVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class MaterialServiceImpl implements IMaterialService {

    private static final Pattern PRODUCT_SN_NUMBER_PATTERN = Pattern.compile("(\\d+)$");
    private static final String LOG_DELETE_BINDING_MESSAGE =
            "删除物料绑定信息 - 产品序列号: {}, 物料ID: {}, 删除数量: {}";
    private static final String LOG_GET_BINDINGS_MESSAGE = "{}, {}, {}, {}";
    private static final String LOG_BOM_QUERY_RESULT = "查询到的 BOM 明细: {}";
    private static final String MESSAGE_PARAM_MISSING = "存在参数为空";
    private static final String MESSAGE_PRODUCT_SN_MUST_END_WITH_NUMBER = "产品序列号必须以数字结尾";
    private static final String MESSAGE_QUERY_CONDITION_REQUIRED = "至少需要提供一个查询条件";
    private static final String MESSAGE_START_TIME_AFTER_END_TIME = "开始时间不能晚于结束时间";
    private static final int CODE_PARAM_MISSING = 402;
    private static final int CODE_PRODUCT_SN_INVALID = 401;
    private static final int CODE_BAD_REQUEST = 400;

    private K3CloudApi api;

    @Autowired
    private IMoMaterialBindingService moMaterialBindingService;

    @Autowired
    private MoMaterialBindingMapper moMaterialBindingMapper;

    @Override
    public List<MaterialVo> listMaterialsFromWorkOrder() throws Exception {
        api = new K3CloudApi();
        List<List<Object>> lists = api.executeBillQuery("{\n" +
                "  \"FormId\": \"PRD_MO\",\n" +
                "  \"FieldKeys\": \"FMaterialID.FNumber,FMaterialID.FName\",\n" +
                "}"
        );
        Map<String, MaterialVo> materialMap = lists.stream()
                .collect(Collectors.toMap(
                        item -> (String) item.get(0),  // key: materialCode
                        item -> MaterialVo.builder()
                                .materialCode((String) item.get(0))
                                .materialName((String) item.get(1))
                                .build(),
                        (existing, replacement) -> existing  // 去重策略
                ));

        return new ArrayList<>(materialMap.values());
    }

    @Override
    public List<MaterialVo> getBomByMaterialCode(String materialCode) throws Exception {
        api = new K3CloudApi();
        List<List<Object>> lists = api.executeBillQuery("{\n" +
                "  \"FormId\": \"ENG_BOM\",\n" +
                "  \"FieldKeys\": \"FMATERIALIDCHILD.Fnumber, FMATERIALIDCHILD.Fname\",\n" +
                "  \"FilterString\": \"FMaterialId.Fnumber='" + materialCode + "'\",\n" +
                "}"
        );
        log.debug(LOG_BOM_QUERY_RESULT, lists);
        Map<String, MaterialVo> materialMap = lists.stream()
                .collect(Collectors.toMap(
                        item -> (String) item.get(0),  // key: materialCode
                        item -> MaterialVo.builder()
                                .materialCode((String) item.get(0))
                                .materialName((String) item.get(1))
                                .build(),
                        (existing, replacement) -> existing  // 去重策略
                ));

        return new ArrayList<>(materialMap.values());
    }

    @Override
    public List<String> getBatchesByMaterialCode(String materialCode) throws Exception {
        api = new K3CloudApi();
        List<List<Object>> lists = api.executeBillQuery("{\n" +
                "  \"FormId\": \"BD_BatchMainFile\",\n" +
                "  \"FieldKeys\": \"FNumber\",\n" +
                "  \"FilterString\": \"FMaterialId.FNumber='" + materialCode + "'\",\n" +
                "}"
        );
        return lists.stream()
                .map(item -> (String) item.get(0))
                .toList();
    }

    @Override
    public boolean deleteBindingInfo(String productSn, String materialId, Integer count) throws Exception {
        if (productSn == null || materialId == null || count == null) {
            throw new MOException(MESSAGE_PARAM_MISSING, CODE_PARAM_MISSING);
        }
        log.info(LOG_DELETE_BINDING_MESSAGE, productSn, materialId, count);

        Matcher matcher = PRODUCT_SN_NUMBER_PATTERN.matcher(productSn);
        if (!matcher.find()) {
            throw new MOException(MESSAGE_PRODUCT_SN_MUST_END_WITH_NUMBER, CODE_PRODUCT_SN_INVALID);
        }

        String numberStr = matcher.group();
        String prefix = productSn.substring(0, matcher.start());
        int originalNumber = Integer.parseInt(numberStr);
        int numberLength = numberStr.length();

        List<String> deletedSnList = new ArrayList<>();

        int i = 0;
        do {
            String currentProductSn = generateProductSn(prefix, originalNumber, i, numberLength);
            deletedSnList.add(currentProductSn);
            i++;
        } while (i < count);

        return deleteBySNAndMaterialID(deletedSnList, materialId.trim());
    }

    @Override
    public boolean deleteBySNAndMaterialID(List<String> cameraSNs, String categoryNo) throws Exception {
        LambdaQueryWrapper<MoMaterialBinding> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(MoMaterialBinding::getCameraSn, cameraSNs)
                .eq(MoMaterialBinding::getCategoryNo, categoryNo);
        return moMaterialBindingService.remove(wrapper);
    }

    @Override
    public List<MaterialBindVo> getBindings(String materialCode, String cameraSn,
                                            LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        if (materialCode == null && cameraSn == null && startTime == null && endTime == null) {
            throw new MOException(MESSAGE_QUERY_CONDITION_REQUIRED, CODE_BAD_REQUEST);
        }

        if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
            throw new MOException(MESSAGE_START_TIME_AFTER_END_TIME, CODE_BAD_REQUEST);
        }

        log.info(LOG_GET_BINDINGS_MESSAGE, materialCode, cameraSn, startTime, endTime);
        return moMaterialBindingMapper.getBindings(materialCode, cameraSn,
                startTime, endTime);
    }

    private String generateProductSn(String prefix, int originalNumber, int increment, int numberLength) {
        int currentNumber = originalNumber + increment;
        String currentNumberStr = String.valueOf(currentNumber);
        if (currentNumberStr.length() > numberLength) {
            throw new MOException(ResultCodeEnum.SN_SEQUENCE_EXCEEDS_MAX_LENGTH);
        }
        String formatTemplate = "%0" + numberLength + "d";
        return prefix + String.format(formatTemplate, currentNumber);
    }
}
