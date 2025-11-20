package com.metoak.mes.traceability.service;

import com.metoak.mes.traceability.vo.TraceabilityMaterialVo;

import java.util.List;

public interface ITraceabilityService {

    List<TraceabilityMaterialVo> listProducts();

    List<TraceabilityMaterialVo> getBomByMaterialCode(String materialCode);

    List<String> getBatchesByMaterialCode(String materialCode);
}
