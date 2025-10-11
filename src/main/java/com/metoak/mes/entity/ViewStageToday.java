package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@TableName("view_stage_today")
@ApiModel(value = "ViewStageToday对象", description = "VIEW")
public class ViewStageToday implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long num;

    private String stage;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "ViewStageToday{" +
            "num = " + num +
            ", stage = " + stage +
        "}";
    }
}
