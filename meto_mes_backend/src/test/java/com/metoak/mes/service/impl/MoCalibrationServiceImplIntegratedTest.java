package com.metoak.mes.service.impl;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.mapper.MoCalibrationMapper;
import com.metoak.mes.service.IMoCalibrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.metoak.mes.common.result.ResultCodeEnum.Field_NOT_FOUND;
import static com.metoak.mes.common.result.ResultCodeEnum.RECORD_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class MoCalibrationServiceImplIntegratedTest {

    @Autowired
    private MoCalibrationServiceImpl service;


    @Autowired
    private MoCalibrationMapper mapper;


    @Test
    void should_return_latest_record_by_id_desc() {
        String cameraSN = String.valueOf(System.currentTimeMillis());

        // 插入旧记录
        mapper.insert(MoCalibration.builder()
                .cameraSn(cameraSN)
                .ymlFilename("old")
                .errorCode(0)
                .startTime(LocalDateTime.now())
                .operator("szdev")
                .stationNumber(1)
                .build());

        // 插入新记录
        mapper.insert(MoCalibration.builder()
                .cameraSn(cameraSN)
                .ymlFilename("new")
                .errorCode(0)
                .startTime(LocalDateTime.now())
                .operator("szdev")
                .stationNumber(1)
                .build());

        Result result = service.getLatestCustomAttrKey(cameraSN, "yml_filename");
        Map<String, List<String>> data = (Map<String, List<String>>) result.getData();
        assertEquals("new", data.get("OK").get(0));
    }


}