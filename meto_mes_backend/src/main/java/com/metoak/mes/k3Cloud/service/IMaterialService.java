package com.metoak.mes.k3Cloud.service;

import com.metoak.mes.traceability.vo.MaterialVo;

import java.util.List;

public interface IMaterialService {

    List<MaterialVo> listMaterialsFromWorkOrder() throws Exception;

    List<MaterialVo> getBomByMaterialCode(String materialCode) throws Exception;

    List<String> getBatchesByMaterialCode(String materialCode) throws Exception;

    boolean deleteById(Long id);
}
