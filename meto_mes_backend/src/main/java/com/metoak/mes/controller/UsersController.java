package com.metoak.mes.controller;

import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.LoginDto;
import com.metoak.mes.dto.LoginResponseDto;
import com.metoak.mes.dto.LogoutDto;
import com.metoak.mes.dto.UserDto;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.enums.UserRoleLevel;
import com.metoak.mes.service.IMoUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/user")
@Tag(name = "用户")
public class UsersController {

    @Autowired
    private IMoUserInfoService moUserInfoService;

    @PostMapping("login")
    @Operation(summary = "登录")
    public ResultBean<LoginResponseDto> login(@RequestBody @Valid LoginDto loginDto) {
        LoginResponseDto loginResponseDto = moUserInfoService.login(loginDto);
        if (loginResponseDto == null) {
            return ResultBean.fail(ResultCodeEnum.LOGIN_FAILED.getCode(), "登录失败，用户或密码输出错误");
        } else {
            return ResultBean.ok(loginResponseDto);
        }
    }

    @PostMapping("logout")
    @Operation(summary = "登出")
    public ResultBean<Boolean> logout(@RequestBody @Valid LogoutDto LogoutDto) {
        return ResultBean.ok();
    }

    @PostMapping
    @Operation(summary = "添加用户")
    public ResultBean add(@RequestBody @Valid UserDto userDto) {
        if (!moUserInfoService.fetchByUserName(userDto.getUsername()).isEmpty()) {
            return ResultBean.fail(-1, "添加失败，用户已存在");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            return ResultBean.fail(-1, "添加失败，用户密码不能为空");
        }
        moUserInfoService.add(userDto);
        return ResultBean.ok();
    }

    @GetMapping
    @Operation(summary = "获取所有用户")
    public ResultBean<List<UserDto>> fetchMulti() {
        return ResultBean.ok(moUserInfoService.fetchMulti());
    }

    @PutMapping
    @Operation(summary = "更新用户")
    public ResultBean edit(@RequestBody @Valid UserDto userDto) {
        moUserInfoService.edit(userDto);
        return ResultBean.ok();
    }

    @GetMapping("roles/sys")
    @Operation(summary = "获取用户角色")
    public ResultBean roles() {
        return ResultBean.ok(UserRoleLevel.values());
    }

}