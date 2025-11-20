package com.metoak.mes.traceability.service.impl;

import com.metoak.mes.traceability.service.ITraceabilityService;
import com.metoak.mes.traceability.vo.TraceabilityMaterialVo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TraceabilityServiceImpl implements ITraceabilityService {

    @Override
    public List<TraceabilityMaterialVo> listProducts() {
        return Collections.emptyList();
    }

    @Override
    public List<TraceabilityMaterialVo> getBomByMaterialCode(String materialCode) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getBatchesByMaterialCode(String materialCode) {
        return Collections.emptyList();
    }
}
