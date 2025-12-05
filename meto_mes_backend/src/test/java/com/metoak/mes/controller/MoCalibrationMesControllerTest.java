package com.metoak.mes.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.service.IMoCalibrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoCalibrationMesControllerTest {

    @Autowired
    private IMoCalibrationService moCalibrationService;

    @Autowired
    private MoCalibrationMesController moCalibrationMesController;

    @Test
    void productio_1() throws Exception {
        String cameraSn = String.valueOf(System.currentTimeMillis());
        MoCalibration moCalibration = MoCalibration.builder()
                .cameraSn(cameraSn)
                .errorCode(1)
                .stationNumber(1)
                .operator("szdev")
                .startTime(LocalDateTime.now())
                .build();

        moCalibrationService.save(moCalibration);
        Result result = moCalibrationMesController.getV2LatestSuccessCustomAttrKey(cameraSn, "ymlFilename");
        assertEquals(2, result.getCode());
        assertEquals(null, result.getData());

    }
}