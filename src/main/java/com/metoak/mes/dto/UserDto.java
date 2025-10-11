package com.metoak.mes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "用户名不能为空")
    private String username;
    private String jobNo;
    private Integer sysRole;
    private String realName;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String phoneNo;
}