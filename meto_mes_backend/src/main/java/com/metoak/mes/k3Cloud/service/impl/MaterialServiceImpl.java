package com.metoak.mes.k3Cloud.service.impl;

import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.metoak.mes.k3Cloud.service.IMaterialService;
import com.metoak.mes.mapper.MoMaterialBindingMapper;
import com.metoak.mes.service.IMoMaterialBindingService;
import com.metoak.mes.traceability.vo.MaterialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements IMaterialService {

    private K3CloudApi api;

    @Autowired
    private IMoMaterialBindingService moMaterialBindingService;

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
        System.out.println(lists);
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
    public boolean deleteById(Long id) {
        return moMaterialBindingService.removeById(id);
    }
}
