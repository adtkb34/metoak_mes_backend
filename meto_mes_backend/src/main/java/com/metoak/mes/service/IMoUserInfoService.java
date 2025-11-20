package com.metoak.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.dto.LoginDto;
import com.metoak.mes.dto.LoginResponseDto;
import com.metoak.mes.dto.UserDto;
import com.metoak.mes.entity.MoUserInfo;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
public interface IMoUserInfoService extends IService<MoUserInfo> {

    LoginResponseDto login(@Valid LoginDto loginDto);

    void add(@Valid UserDto userDto);

    List<UserDto> fetchMulti();

    Boolean edit(@Valid UserDto userDto);

    List<MoUserInfo> fetchByUserName(String username);
}
