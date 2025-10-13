package com.metoak.mes.common.query;

import com.baomidou.mybatisplus.annotation.TableName;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.config.DatabaseConfig;
import com.metoak.mes.common.mapping.CommonAttrMapping;
import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.entity.MoProcessStepProductionResult;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductionRecordQueryService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public <T> ProductionRecordDto queryMethod1(DatabaseConfig config, Class<T> entityClass) {
        Objects.requireNonNull(config, "Database config must not be null");
        Objects.requireNonNull(entityClass, "Entity class must not be null");

        TableName tableName = entityClass.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new IllegalArgumentException("实体缺少@TableName注解: " + entityClass.getName());
        }

        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            List<T> entities = jdbcTemplate.query(
                    "SELECT * FROM " + tableName.value(),
                    new BeanPropertyRowMapper<>(entityClass)
            );

            if (entities.isEmpty()) {
                return null;
            }

            ProductionRecordDto dto = new ProductionRecordDto();

            List<T> relevantEntities = filterEntitiesByResultId(entities);
            T first = relevantEntities.get(0);
            CommonAttrMapping.mapEntityFieldsToDto(first, dto, CommonAttrMapping.FIELD_TO_FIELD);

            Long resultId = extractResultId(first);
            if (resultId != null) {
                List<MoProcessStepProductionResult> processResults = jdbcTemplate.query(
                        "SELECT * FROM mo_process_step_production_result WHERE id = ?",
                        new BeanPropertyRowMapper<>(MoProcessStepProductionResult.class),
                        resultId
                );

                if (!processResults.isEmpty()) {
                    MoProcessStepProductionResult result = processResults.get(0);
                    dto.setProductSn(result.getProductSn());
                    dto.setProductBatchNo(result.getProductBatchNo());
                    dto.setStepType(result.getStepType());
                    dto.setStepTypeNo(result.getStepTypeNo());
                    dto.setErrorNo(result.getErrorCode() == null ? null : String.valueOf(result.getErrorCode()));
                    dto.setError(result.getNgReason());
                    dto.setDeviceNo(result.getStationNum() == null ? null : String.valueOf(result.getStationNum()));
                    dto.setOperator(result.getOperator());
                    dto.setSoftwareTool(result.getSoftwareTool());
                    dto.setSoftwareToolVersion(result.getSoftwareToolVersion());
                    dto.setStartTime(result.getStartTime() == null ? null : result.getStartTime().format(DATE_TIME_FORMATTER));
                    dto.setEndTime(result.getEndTime() == null ? null : result.getEndTime().format(DATE_TIME_FORMATTER));
                }
            }

            List<AttrKeyValDto> attrKeyValDtos = new ArrayList<>();
            for (T entity : relevantEntities) {
                List<AttrKeyValDto> attrs = FieldCodeMapper.extractAttrListFromObject(entity);
                for (AttrKeyValDto attr : attrs) {
                    String originalPosition = attr.getPosition();
                    CommonAttrMapping.mapEntityFieldsToDto(entity, attr, CommonAttrMapping.FIELD_TO_FIELD2);
                    attr.setPosition(originalPosition);
                }
                attrKeyValDtos.addAll(attrs);
            }

            dto.setAttrKeyVals(attrKeyValDtos);
            return dto;
        }
    }

    private <T> List<T> filterEntitiesByResultId(List<T> entities) {
        Long resultId = extractResultId(entities.get(0));
        if (resultId == null) {
            return entities;
        }

        List<T> relevantEntities = new ArrayList<>();
        for (T entity : entities) {
            Long currentId = extractResultId(entity);
            if (Objects.equals(currentId, resultId)) {
                relevantEntities.add(entity);
            }
        }

        return relevantEntities.isEmpty() ? entities : relevantEntities;
    }

    private Long extractResultId(Object entity) {
        try {
            java.lang.reflect.Method method = entity.getClass().getMethod("getMoProcessStepProductionResultId");
            Object value = method.invoke(entity);
            if (value instanceof Number number) {
                return number.longValue();
            }
        } catch (NoSuchMethodException ignored) {
            return null;
        } catch (IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            throw new IllegalStateException("无法获取moProcessStepProductionResultId", e);
        }
        return null;
    }

    private HikariDataSource buildDataSource(DatabaseConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setDriverClassName(config.getDriverClassNameOrDefault());
        hikariConfig.setMaximumPoolSize(2);
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setPoolName("dynamic-production-record-query");
        hikariConfig.setAutoCommit(true);
        hikariConfig.setConnectionTimeout(10_000L);
        return new HikariDataSource(hikariConfig);
    }
}
