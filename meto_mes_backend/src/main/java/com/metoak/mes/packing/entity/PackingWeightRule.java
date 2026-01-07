package com.metoak.mes.packing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mo_packing_weight_rule")
public class PackingWeightRule {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_code")
    private String productCode;

    @TableField("spec_quantity")
    private Integer specQuantity;

    @TableField("unit_weight")
    private BigDecimal unitWeight;

    @TableField("tare_weight")
    private BigDecimal tareWeight;

    @TableField("weight_tolerance")
    private BigDecimal weightTolerance;

    @TableField("create_by")
    private String createBy;

    @TableField("update_by")
    private String updateBy;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
