package com.metoak.mes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.dto.ParamsBaseDto;
import com.metoak.mes.entity.MoProduceOrder;
import com.metoak.mes.entity.MoTagInfo;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.controller.ParamsController;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.service.IMoBeamInfoService;
import com.metoak.mes.service.IMoProduceOrderService;
import com.metoak.mes.service.IMoTagInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParamsController.class)
public class ParamsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMoParamsBaseService paramsBaseService;
    
    @MockBean
    private IMoParamsDetailService paramsDetailService;

    @MockBean
    private IMoTagInfoService tagInfoService;

    @MockBean
    private IMoBeamInfoService beamInfoService;

    @MockBean
    private IMoProduceOrderService produceOrderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateParamsBase() throws Exception {
        // 准备测试数据
        ParamsBaseDto paramsBaseDto = new ParamsBaseDto();
        paramsBaseDto.setName("测试参数集");
        paramsBaseDto.setType(0);
        paramsBaseDto.setCreatedBy("testUser");
        paramsBaseDto.setCreatedAt(LocalDateTime.now());

        MoParamsBase paramsBase = new MoParamsBase();
        paramsBase.setId(1L);
        paramsBase.setName("测试参数集");
        paramsBase.setType(0);
        paramsBase.setCreatedBy("testUser");
        paramsBase.setCreatedAt(LocalDateTime.now());

        when(paramsBaseService.save(any(MoParamsBase.class))).thenReturn(true);

        // 执行测试
        mockMvc.perform(post("/api/mes/v1/params/base")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paramsBaseDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetParamsBaseById() throws Exception {
        // 准备测试数据
        MoParamsBase paramsBase = new MoParamsBase();
        paramsBase.setId(1L);
        paramsBase.setName("测试参数集");
        paramsBase.setType(0);
        paramsBase.setCreatedBy("testUser");
        paramsBase.setCreatedAt(LocalDateTime.now());

        when(paramsBaseService.getById(1L)).thenReturn(paramsBase);

        // 执行测试
        mockMvc.perform(get("/api/mes/v1/params/base/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("测试参数集"));
    }

//    @Test
//    public void testGetParamsBySerialNumber() throws Exception {
//        MoTagInfo tagInfo = new MoTagInfo();
//        tagInfo.setWorkOrderCode("WO-001");
//
//        when(tagInfoService.getOne(any(), eq(false))).thenReturn(tagInfo);
//
//        MoProduceOrder produceOrder = new MoProduceOrder();
//        produceOrder.setOrderState(0);
//        produceOrder.setParamsDetailId(10L);
//        when(produceOrderService.getOne(any(), eq(false))).thenReturn(produceOrder);
//
//        MoParamsDetail paramsDetail = new MoParamsDetail();
//        paramsDetail.setParams("{\"key\":\"value\"}");
//        when(paramsDetailService.getById(10L)).thenReturn(paramsDetail);
//
//        mockMvc.perform(get("/api/mes/v1/params/detail/bySn").param("sn", "SN001"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value("{\"key\":\"value\"}"));
//    }
}