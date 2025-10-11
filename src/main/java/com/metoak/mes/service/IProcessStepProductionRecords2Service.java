package com.metoak.mes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metoak.mes.dto.*;


public interface IProcessStepProductionRecords2Service {

    Long add(ProductionRecordDto productionRecordDto);

    Boolean executable(ExecutableDto executableDto);


}
