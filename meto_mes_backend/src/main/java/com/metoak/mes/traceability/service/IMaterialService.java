package com.metoak.mes.traceability.service;

import com.metoak.mes.traceability.vo.MaterialBindVo;

import java.time.LocalDateTime;
import java.util.List;

public interface IMaterialService {

    boolean deleteBindingInfo(String productSn, String materialId, Integer count) throws Exception;

    boolean deleteBySNAndMaterialCode(List<String> cameraSNs, String materialCode) throws Exception;

    List<MaterialBindVo> getBindings(String materialCode, String cameraSn,
                                     LocalDateTime startTime, LocalDateTime endTime) throws Exception;
}