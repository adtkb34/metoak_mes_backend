package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mo_process_step_yield")
public class MoProcessStepYield {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; // 主键

    @TableField("mo_process_step_production_result_id")
    private Long moProcessStepProductionResultId; // 过站记录关联ID

    @TableField("input_num")
    private Integer inputNum; // 检测数量

    @TableField("good_num")
    private Integer goodNum; // 良品数量
}
