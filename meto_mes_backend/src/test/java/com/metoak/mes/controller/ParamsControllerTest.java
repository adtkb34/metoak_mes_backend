package com.metoak.mes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.dto.ParamsBaseDto;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.controller.ParamsController;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
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
        mockMvc.perform(post("/api/v1/params/base")
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
        mockMvc.perform(get("/api/v1/params/base/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("测试参数集"));
    }
}