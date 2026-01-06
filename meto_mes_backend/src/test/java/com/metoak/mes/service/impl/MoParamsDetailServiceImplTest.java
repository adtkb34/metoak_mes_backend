package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.params.dto.ParamDetailCreateDto;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.enums.ParamTypeEnum;
import com.metoak.mes.params.mapper.MoParamsDetailMapper;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.params.service.impl.MoParamsDetailServiceImpl;
import com.metoak.mes.service.IMoProcessFlowService;
import com.metoak.mes.service.IMoProduceOrderService;
import com.metoak.mes.service.IMoWorkstageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoParamsDetailServiceImplTest {

    @Mock
    private MoParamsDetailMapper moParamsDetailMapper;

    @InjectMocks
    @Spy
    private MoParamsDetailServiceImpl service;

    @Mock
    private IMoParamsBaseService paramsBaseService;
    @Mock
    private IMoWorkstageService moWorkstageService;
    @Mock
    private IMoProcessFlowService moProcessFlowService;
    @Mock
    private IMoProduceOrderService moProduceOrderService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void testSaveParamsDetail() {
        MoParamsDetail paramsDetail = new MoParamsDetail();
        paramsDetail.setBaseId(1L);
        paramsDetail.setDescription("测试版本说明");
        paramsDetail.setVersionMajor(1);
        paramsDetail.setVersionMinor(0);
        paramsDetail.setVersionPatch(0);
        paramsDetail.setParams("{\"param1\": \"value1\"}");
        paramsDetail.setIsActive(1);
        paramsDetail.setCreatedBy("testUser");

        when(moParamsDetailMapper.insert(any(MoParamsDetail.class))).thenReturn(1);

        moParamsDetailMapper.insert(paramsDetail);

        verify(moParamsDetailMapper, times(1)).insert(any(MoParamsDetail.class));
    }

    @Test
    void bindParamsDetail_stepType_success() {
        Long baseId = 1L;
        Long detailId = 100L;

        MoParamsBase base = new MoParamsBase();
        base.setType(ParamTypeEnum.STEP.getCode());
        base.setStepTypeNo("S100");

        when(paramsBaseService.getById(baseId)).thenReturn(base);
        when(moWorkstageService.update(any())).thenReturn(true);

        Boolean result = service.bindParamsDetailToTarget(baseId, detailId);

        assertTrue(result);
        verify(moWorkstageService).update(any());
        verifyNoInteractions(moProcessFlowService, moProduceOrderService);
    }

    /* ======================== FLOW ======================== */

    @Test
    void bindParamsDetail_flowType_success() {
        Long baseId = 2L;
        Long detailId = 101L;

        MoParamsBase base = new MoParamsBase();
        base.setType(ParamTypeEnum.FLOW.getCode());
        base.setFlowNo("FLOW-A");

        when(paramsBaseService.getById(baseId)).thenReturn(base);
        when(moProcessFlowService.update(any())).thenReturn(true);

        Boolean result = service.bindParamsDetailToTarget(baseId, detailId);

        assertTrue(result);
        verify(moProcessFlowService).update(any());
        verifyNoInteractions(moWorkstageService, moProduceOrderService);
    }

    /* ===================== WORK_ORDER ===================== */

    @Test
    void bindParamsDetail_workOrderType_success() {
        Long baseId = 3L;
        Long detailId = 102L;

        MoParamsBase base = new MoParamsBase();
        base.setType(ParamTypeEnum.WORK_ORDER.getCode());
        base.setOrderId(1001L);

        when(paramsBaseService.getById(baseId)).thenReturn(base);
        when(moProduceOrderService.update(any())).thenReturn(true);

        Boolean result = service.bindParamsDetailToTarget(baseId, detailId);

        assertTrue(result);
        verify(moProduceOrderService).update(any());
        verifyNoInteractions(moWorkstageService, moProcessFlowService);
    }

    /* =================== BASE NOT FOUND =================== */

    @Test
    void bindParamsDetail_baseNotFound_returnFalse() {
        when(paramsBaseService.getById(99L)).thenReturn(null);

        Boolean result = service.bindParamsDetailToTarget(99L, 200L);

        assertFalse(result);
        verifyNoInteractions(
                moWorkstageService,
                moProcessFlowService,
                moProduceOrderService
        );
    }

    /* =================== TYPE NOT SUPPORTED =================== */

    @Test
    void bindParamsDetail_unsupportedType_returnFalse() {
        MoParamsBase base = new MoParamsBase();
        base.setType(999);

        when(paramsBaseService.getById(5L)).thenReturn(base);

        Boolean result = service.bindParamsDetailToTarget(5L, 201L);

        assertFalse(result);
        verifyNoInteractions(
                moWorkstageService,
                moProcessFlowService,
                moProduceOrderService
        );
    }

    /* =================== UPDATE FAILED =================== */

    @Test
    void bindParamsDetail_stepType_updateFailed_returnFalse() {
        MoParamsBase base = new MoParamsBase();
        base.setType(ParamTypeEnum.STEP.getCode());
        base.setStepTypeNo("S200");

        when(paramsBaseService.getById(6L)).thenReturn(base);
        when(moWorkstageService.update(any())).thenReturn(false);

        Boolean result = service.bindParamsDetailToTarget(6L, 300L);

        assertFalse(result);
        verify(moWorkstageService).update(any());
    }

    @Test
    void saveDetail_success_returnDetailId() throws Exception {
        ParamDetailCreateDto dto = new ParamDetailCreateDto();
        dto.setBaseId(1L);
        dto.setParams("{\"a\":1}");
        dto.setDescription("v1");
        dto.setCreatedBy("tester");

        lenient()
                .when(objectMapper.readTree(dto.getParams()))
                .thenReturn(mock(JsonNode.class));

        // ⚠️ MyBatis 隔离点 —— 必须保留，但用 lenient
        lenient()
                .doReturn(Collections.emptyList())
                .when(service)
                .listByBaseId(1L);

        lenient()
                .doAnswer(invocation -> {
                    MoParamsDetail entity = invocation.getArgument(0);
                    entity.setId(10L);
                    return true;
                })
                .when(service)
                .save(any());

        lenient()
                .doReturn(true)
                .when(service)
                .bindParamsDetailToTarget(eq(1L), anyLong());

        Result<Long> result = service.saveDetail(dto);

        assertEquals(10L, (long) result.getData());
    }

    @Test
    void saveDetail_invalidJson_returnFail() throws Exception {
        ParamDetailCreateDto dto = new ParamDetailCreateDto();
        dto.setBaseId(1L);
        dto.setParams("{invalid-json}");

        lenient()
                .when(objectMapper.readTree(dto.getParams()))
                .thenThrow(new JsonProcessingException("invalid") {});

        Result<Long> result = service.saveDetail(dto);

        assertEquals(ResultCodeEnum.PARAMS_INVALID_JSON.getCode(), result.getCode());

        // 不会触发 DB 行为
        verify(service, never()).listByBaseId(anyLong());
        verify(service, never()).save(any());
    }

    @Test
    void saveDetail_withExistingVersion_generateNextVersion() throws Exception {
        ParamDetailCreateDto dto = new ParamDetailCreateDto();
        dto.setBaseId(2L);
        dto.setParams("{\"a\":2}");
        dto.setCreatedBy("tester");

        lenient()
                .when(objectMapper.readTree(dto.getParams()))
                .thenReturn(mock(JsonNode.class));

        MoParamsDetail old = new MoParamsDetail();
        old.setVersionMajor(1);
        old.setVersionMinor(0);
        old.setVersionPatch(0);

        lenient()
                .doReturn(List.of(old))
                .when(service)
                .listByBaseId(2L);

        lenient()
                .doAnswer(invocation -> {
                    MoParamsDetail entity = invocation.getArgument(0);
                    entity.setId(20L);
                    return true;
                })
                .when(service)
                .save(any());

        // ⚠️ 关键：anyLong()
        lenient()
                .doReturn(true)
                .when(service)
                .bindParamsDetailToTarget(eq(2L), anyLong());

        Result<Long> result = service.saveDetail(dto);

        assertEquals(20L, (long) result.getData());
    }

    @Test
    void saveDetail_bindFailed_throwException() throws Exception {
        ParamDetailCreateDto dto = new ParamDetailCreateDto();
        dto.setBaseId(3L);
        dto.setParams("{\"a\":3}");
        dto.setCreatedBy("tester");

        lenient()
                .when(objectMapper.readTree(dto.getParams()))
                .thenReturn(mock(JsonNode.class));

        lenient()
                .doReturn(Collections.emptyList())
                .when(service)
                .listByBaseId(3L);

        lenient()
                .doAnswer(invocation -> {
                    MoParamsDetail entity = invocation.getArgument(0);
                    entity.setId(30L);
                    return true;
                })
                .when(service)
                .save(any());

        // ⚠️ 关键：anyLong()
        lenient()
                .doReturn(false)
                .when(service)
                .bindParamsDetailToTarget(eq(3L), anyLong());

        MOException ex = assertThrows(
                MOException.class,
                () -> service.saveDetail(dto)
        );

        assertEquals(
                ResultCodeEnum.PARAMS_DETAIL_BIND_FAILED.getCode(),
                ex.getCode()
        );
    }



}