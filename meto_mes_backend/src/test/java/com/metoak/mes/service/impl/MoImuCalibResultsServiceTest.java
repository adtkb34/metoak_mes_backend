package com.metoak.mes.service.impl;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.entity.MoImuCalibResults;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static com.metoak.mes.common.result.ResultCodeEnum.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class MoImuCalibResultsServiceTest {

    @InjectMocks
    private MoImuCalibResultsServiceImpl service;

    @Mock
    private MoCalibrationServiceImpl moCalibrationService;

    // ========== 工具方法 ==========
    private MoImuCalibResults imuRecord(
            Integer errorCode,
            Long moCalibrationId,
            String root,
            String imu,
            String imucam
    ) {
        MoImuCalibResults r = new MoImuCalibResults();
        r.setErrorCode(errorCode);
        r.setMoCalibrationId(moCalibrationId);
        r.setRoot(root);
        r.setRelativePathYmlImu(imu);
        r.setRelativePathYmlImucam(imucam);
        return r;
    }

    @Test
    void should_fail_when_no_imu_record_found() {
        MoImuCalibResultsServiceImpl spy = Mockito.spy(service);
        Mockito.doReturn(null).when(spy).getOne(any());

        Result result = spy.getLatest("SN001");

//        assertFalse(result.isSuccess());
        assertEquals(NO_IMU_CALCULATION_RECORD.getCode(), result.getCode());
    }

    @Test
    void should_fail_when_imu_error_code_not_zero() {
        MoImuCalibResultsServiceImpl spy = Mockito.spy(service);
        MoImuCalibResults imu = imuRecord(1001, 1L, "r", "imu", "imucam");

        Mockito.doReturn(imu).when(spy).getOne(any());

        Result result = spy.getLatest("SN001");

//        assertFalse(result.isSuccess());
        assertEquals(Integer.valueOf(1001), result.getCode());
    }

    @Test
    void should_fail_when_imu_not_linked_to_stereo() {
        MoImuCalibResultsServiceImpl spy = Mockito.spy(service);
        MoImuCalibResults imu = imuRecord(0, null, "r", "imu", "imucam");

        Mockito.doReturn(imu).when(spy).getOne(any());

        Result result = spy.getLatest("SN001");

//        assertFalse(result.isSuccess());
        assertEquals(IMU_CALCULATION_RECORD_NOT_LINKED_TO_STEREO_CALIBRATION.getCode(), result.getCode());
    }

    @Test
    void should_fail_when_stereo_record_not_found() {
        MoImuCalibResultsServiceImpl spy = Mockito.spy(service);
        MoImuCalibResults imu = imuRecord(0, 10L, "r", "imu", "imucam");

        Mockito.doReturn(imu).when(spy).getOne(any());
        Mockito.when(moCalibrationService.getOne(any())).thenReturn(null);

        Result result = spy.getLatest("SN001");

//        assertFalse(result.isSuccess());
        assertEquals(STEREO_CALIBRATION_RECORD_NOT_FOUND.getCode(), result.getCode());
    }

    @Test
    void should_fail_when_any_result_field_is_null() {
        MoImuCalibResultsServiceImpl spy = Mockito.spy(service);
        MoImuCalibResults imu = imuRecord(0, 10L, null, "imu", "imucam");
        MoCalibration stereo = stereoRecord("stereo.yml");

        Mockito.doReturn(imu).when(spy).getOne(any());
        Mockito.when(moCalibrationService.getOne(any())).thenReturn(stereo);

        Result result = spy.getLatest("SN001");

//        assertFalse(result.isSuccess());
        assertEquals(HAS_NULL_VALUE.getCode(), result.getCode());
    }


    private MoCalibration stereoRecord(String ymlFilename) {
        MoCalibration c = new MoCalibration();
        c.setYmlFilename(ymlFilename);
        return c;
    }
}
