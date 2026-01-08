package com.metoak.mes.traceability.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.dto.SnSequence;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.common.util.SnParseUtil;
import com.metoak.mes.entity.MoMaterialBinding;
import com.metoak.mes.mapper.MoMaterialBindingMapper;
import com.metoak.mes.service.IMoMaterialBindingService;
import com.metoak.mes.traceability.service.IMaterialService;
import com.metoak.mes.traceability.vo.MaterialBindVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.metoak.mes.common.util.SnParseUtil.parseAndValidateSnSequence;

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
    public boolean deleteBindingInfo(String productSn, String materialCode, Integer count) throws Exception {
        if (productSn == null || materialCode == null || count == null) {
            throw new MOException(MESSAGE_PARAM_MISSING, CODE_PARAM_MISSING);
        }
        SnSequence snSequence = parseAndValidateSnSequence(productSn, count);

        List<String> deletedSnList = new ArrayList<>();

        int i = 0;
        do {
            deletedSnList.add(SnParseUtil.generateSn(snSequence, i));
            i++;
        } while (i < count);

        return deleteBySNAndMaterialCode(deletedSnList, materialCode.trim());
    }

    @Override
    public boolean deleteBySNAndMaterialCode(List<String> cameraSNs, String materialCode) throws Exception {
        LambdaQueryWrapper<MoMaterialBinding> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(MoMaterialBinding::getCameraSn, cameraSNs)
                .eq(MoMaterialBinding::getCategoryNo, materialCode);
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

}
