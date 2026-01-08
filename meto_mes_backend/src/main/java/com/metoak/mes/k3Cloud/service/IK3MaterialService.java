package com.metoak.mes.k3Cloud.service;

import com.metoak.mes.traceability.vo.MaterialBindVo;
import com.metoak.mes.traceability.vo.MaterialVo;

import java.time.LocalDateTime;
import java.util.List;

public interface IK3MaterialService {

    List<MaterialVo> listMaterialsFromWorkOrder() throws Exception;

    List<MaterialVo> getBomByMaterialCode(String materialCode) throws Exception;

    List<String> getBatchesByMaterialCode(String materialCode) throws Exception;

}
