package com.metoak.mes.dto;

import com.metoak.mes.common.config.DatabaseConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductionRecordQueryRequest {

    @Valid
    @NotNull(message = "数据库配置不能为空")
    @Schema(description = "数据库配置", requiredMode = Schema.RequiredMode.REQUIRED)
    private DatabaseConfig databaseConfig;

    @NotBlank(message = "实体类名称不能为空")
    @Schema(description = "实体类全限定名或简单类名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String entityClassName;
}
