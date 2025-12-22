package com.metoak.mes.service.impl;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.mapper.MoCalibrationMapper;
import com.metoak.mes.service.IMoCalibrationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.metoak.mes.common.result.ResultCodeEnum.Field_NOT_FOUND;
import static com.metoak.mes.common.result.ResultCodeEnum.RECORD_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class MoCalibrationServiceImplTest {

    @InjectMocks
    private MoCalibrationServiceImpl service;


    @Autowired
    private MoCalibrationMapper mapper;

    // ========== 工具方法 ==========
    private MoCalibration mockRecord(int errorCode) {
        MoCalibration record = new MoCalibration();
        record.setErrorCode(errorCode);
        return record;
    }

    // ① SN 没有找到记录
    @Test
    void test_no_record_found() {
        IMoCalibrationService spy = Mockito.spy(service);
        Mockito.doReturn(null).when(spy).getOne(any());

        Result result = spy.getLatestCustomAttrKey("SN001", "clarityLeft");

//        assertFalse(result.isSuccess());
        assertEquals(RECORD_NOT_FOUND.getCode(), result.getCode());

        Map<String, List<String>> data = (Map<String, List<String>>) result.getData();
        assertTrue(data.get("OK").isEmpty());
        assertTrue(data.get("NG").isEmpty());
    }

    // ② 查询字段不存在
    @Test
    void test_field_not_found() throws Exception {
        MoCalibrationServiceImpl spy = Mockito.spy(service);
        MoCalibration record = mockRecord(0);

        Mockito.doReturn(record).when(spy).getOne(any());
        Mockito.doThrow(new RuntimeException("no such field"))
                .when(spy).getFieldValueByAttrKey(record, "notExistField");

        Result result = spy.getLatestCustomAttrKey("SN001", "notExistField");

//        assertFalse(result.isSuccess());
        assertEquals(Field_NOT_FOUND.getCode(), result.getCode());
        assertTrue(result.getMessage().contains("Field not found"));

        Map<String, List<String>> data = (Map<String, List<String>>) result.getData();
        assertTrue(data.get("OK").isEmpty());
        assertTrue(data.get("NG").isEmpty());
    }

    // ③ 最新记录是成功的（errorCode = 0）
    @Test
    void test_latest_record_success() throws Exception {
        MoCalibrationServiceImpl spy = Mockito.spy(service);
        MoCalibration record = mockRecord(0);

        Mockito.doReturn(record).when(spy).getOne(any());
        Mockito.doReturn("123.45")
                .when(spy).getFieldValueByAttrKey(record, "clarityLeft");

        Result result = spy.getLatestCustomAttrKey("SN001", "clarityLeft");

//        assertTrue(result.isSuccess());

        Map<String, List<String>> data = (Map<String, List<String>>) result.getData();
        assertEquals(1, data.get("OK").size());
        assertEquals("123.45", data.get("OK").get(0));
        assertTrue(data.get("NG").isEmpty());
    }

    // ④ 最新记录是失败的（errorCode ≠ 0）
    @Test
    void test_latest_record_failed() throws Exception {
        MoCalibrationServiceImpl spy = Mockito.spy(service);
        MoCalibration record = mockRecord(1001);

        Mockito.doReturn(record).when(spy).getOne(any());
        Mockito.doReturn("0.98")
                .when(spy).getFieldValueByAttrKey(record, "clarityLeft");

        Result result = spy.getLatestCustomAttrKey("SN001", "clarityLeft");

//        assertTrue(result.isSuccess());

        Map<String, List<String>> data = (Map<String, List<String>>) result.getData();
        assertEquals(1, data.get("NG").size());
        assertEquals("0.98", data.get("NG").get(0));
        assertTrue(data.get("OK").isEmpty());
    }

    // ⑤ 多条记录，只取最新一条
    @Test
    void test_multiple_records_only_latest_used() throws Exception {
        MoCalibrationServiceImpl spy = Mockito.spy(service);

        // 模拟“最新一条”为失败
        MoCalibration latest = mockRecord(1);

        Mockito.doReturn(latest).when(spy).getOne(any());
        Mockito.doReturn("LATEST_VALUE")
                .when(spy).getFieldValueByAttrKey(latest, "clarityLeft");

        Result result = spy.getLatestCustomAttrKey("SN001", "clarityLeft");

        Map<String, List<String>> data = (Map<String, List<String>>) result.getData();
        assertEquals(1, data.get("NG").size());
        assertEquals("LATEST_VALUE", data.get("NG").get(0));
    }



}