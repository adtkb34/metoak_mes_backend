package com.metoak.mes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metoak.mes.dto.*;

import java.util.List;


public interface IProcessStepProductionRecordsService {

    Long add(ProductionRecordDto productionRecordDto);

    List<Long> binding(ProductionRecordDto productionRecordDto);

    Boolean executable(ExecutableDto executableDto);

    GetProductSnResponseDto fetchProductSn(GetProductSnDto getProductSnDto);

    Boolean judgement(JudgementDto judgementDto);

    AttrSpecDto fetchAttrSpec(FetchAttrSpecDto fetchAttrSpecDto);

    Boolean device(DeviceStatusDto deviceStatusDto) throws JsonProcessingException;
}
