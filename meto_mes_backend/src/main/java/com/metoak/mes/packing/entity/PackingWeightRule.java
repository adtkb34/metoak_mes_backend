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
@TableName("packing_weight_rule")
public class PackingWeightRule {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_code")
    private String productCode;

    @TableField("full_box_quantity")
    private Integer fullBoxQuantity;

    @TableField("single_product_weight")
    private BigDecimal singleProductWeight;

    @TableField("full_box_package_weight")
    private BigDecimal fullBoxPackageWeight;

    @TableField("allowed_deviation")
    private BigDecimal allowedDeviation;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
