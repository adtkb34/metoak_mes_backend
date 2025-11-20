package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.util.JwtUtil;
import com.metoak.mes.common.util.MD5Util;
import com.metoak.mes.dto.LoginDto;
import com.metoak.mes.dto.LoginResponseDto;
import com.metoak.mes.dto.UserDto;
import com.metoak.mes.entity.MoUserInfo;
import com.metoak.mes.mapper.MoUserInfoMapper;
import com.metoak.mes.service.IMoUserInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Service
public class MoUserInfoServiceImpl extends ServiceImpl<MoUserInfoMapper, MoUserInfo> implements IMoUserInfoService {

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        LambdaQueryWrapper<MoUserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoUserInfo::getUserName, loginDto.getUsername());

        List<MoUserInfo> moUserInfos = list(wrapper);
        for (MoUserInfo item : moUserInfos) {
//            if (item.getUserPassword().equals(loginDto.getPassword())) {
            if (MD5Util.computeMD5(item.getUserPassword()).equals(loginDto.getPassword())) {
                String userName = item.getUserName();
                return LoginResponseDto.builder().username(userName).token(JwtUtil.createToken(null, userName)).build();
            }
        };

        return null;
    }

    @Override
    public void add(UserDto userDto) {

        MoUserInfo moUserInfo = MoUserInfo.builder().
                userName(userDto.getUsername()).
                userPassword(userDto.getPassword()).
                workCode(userDto.getJobNo()).
                realName(userDto.getRealName()).
                userLevel(userDto.getSysRole()).
                build();

        save(moUserInfo);

    }

    public List<MoUserInfo> fetchByUserName(String username) {
        LambdaQueryWrapper<MoUserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoUserInfo::getUserName, username);

        return list(wrapper);
    }

    @Override
    public List<UserDto> fetchMulti() {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        list().forEach(item -> {
            userDtos.add(UserDto.builder().
                    username(item.getUserName()).
                    jobNo(item.getWorkCode()).
                    realName(item.getRealName()).
                    sysRole(item.getUserLevel()).
                    build());
        });

        return userDtos;
    }

    @Override
    public Boolean edit(UserDto userDto) {
        LambdaUpdateWrapper<MoUserInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(MoUserInfo::getUserName, userDto.getUsername()).
                set(MoUserInfo::getWorkCode, userDto.getJobNo()).
                set(MoUserInfo::getUserLevel, userDto.getSysRole()).
                set(MoUserInfo::getRealName, userDto.getRealName()).
                set(userDto.getPassword() != null && !userDto.getPassword().isEmpty(), MoUserInfo::getUserPassword, userDto.getPassword());
        update(wrapper);

        return true;
    }
}
