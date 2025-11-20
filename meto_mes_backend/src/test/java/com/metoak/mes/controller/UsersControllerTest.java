package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.util.MD5Util;
import com.metoak.mes.dto.GetProductSnDto;
import com.metoak.mes.dto.GetProductSnResponseDto;
import com.metoak.mes.dto.LoginDto;
import com.metoak.mes.dto.LoginResponseDto;
import com.metoak.mes.entity.MoBeamInfo;
import com.metoak.mes.entity.MoUserInfo;
import com.metoak.mes.enums.ResultCodeEnum;
import com.metoak.mes.service.IMoUserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {

    @Autowired
    private UsersController usersController;

    @Autowired
    private IMoUserInfoService moUserInfoService;


    @Test
    void login_1() throws Exception {

        String username = "yangshiqin";
        String password = MD5Util.computeMD5("error_password");
        ResultBean<LoginResponseDto> res = usersController.login(LoginDto.builder().username(username).password(password).build());
        assertEquals(ResultCodeEnum.LOGIN_FAILED.getCode(), res.getCode());
        assertEquals(null, res.getData());

    }

    @Test
    void login_2() throws Exception {
        String username = String.valueOf(System.currentTimeMillis());
        System.out.println(username);
        String password = "123456";
        MoUserInfo moUserInfo = MoUserInfo.builder().userName(username).userPassword(password).build();
        moUserInfoService.save(moUserInfo);

        ResultBean<LoginResponseDto> res = usersController.login(LoginDto.builder().username(username).password(MD5Util.computeMD5(password)).build());
        System.out.println(res.getData().getToken());

        assertEquals(0, res.getCode());
        assertNotEquals(null, res.getData().getToken());
        assertEquals(username, res.getData().getUsername());

    }


}