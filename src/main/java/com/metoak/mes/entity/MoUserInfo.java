package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_user_info")
@ApiModel(value = "MoUserInfo对象", description = "用户信息表")
public class MoUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    @TableId("user_name")
    private String userName;

    @ApiModelProperty("密码")
    private String userPassword;

    @ApiModelProperty("用户级别")
    private Integer userLevel;

    @ApiModelProperty("员工号")
        private String workCode;

    @ApiModelProperty("真实姓名")
    private String realName;

    private LocalDateTime createTime;

    private Integer userState;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    @Override
    public String toString() {
        return "MoUserInfo{" +
            "userName = " + userName +
            ", userPassword = " + userPassword +
            ", userLevel = " + userLevel +
            ", workCode = " + workCode +
            ", realName = " + realName +
            ", createTime = " + createTime +
            ", userState = " + userState +
        "}";
    }
}
