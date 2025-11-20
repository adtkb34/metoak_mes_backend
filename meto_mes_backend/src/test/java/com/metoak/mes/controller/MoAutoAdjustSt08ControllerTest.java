package com.metoak.mes.controller;

import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.util.MD5Util;
import com.metoak.mes.dto.LoginDto;
import com.metoak.mes.dto.LoginResponseDto;
import com.metoak.mes.entity.MoAutoAdjustSt08;
import com.metoak.mes.enums.ResultCodeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MoAutoAdjustSt08ControllerTest {

    @Autowired
    private MoAutoAdjustSt08Controller moAutoAdjustSt08Controller;

    @Test
    void fetch_1() throws Exception {
//        ResultBean<List<MoAutoAdjustSt08>> res = moAutoAdjustSt08Controller.fetch(LocalDate.parse("2025-07-17"), LocalDate.parse("2025-07-18"));
        //        assertEquals(ResultCodeEnum.LOGIN_FAILED.getCode(), res.getCode());
//        assertEquals(null, res.getData());

    }
}