package com.metoak.mes.k3Cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.entity.MoMaterialBinding;
import com.metoak.mes.k3Cloud.service.IK3MaterialService;
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
public class K3MaterialServiceImpl implements IK3MaterialService {

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

}
