package com.metoak.mes.common.query;

import com.baomidou.mybatisplus.annotation.TableName;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.config.DatabaseConfig;
import com.metoak.mes.common.mapping.CommonAttrMapping;
import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.entity.MoAutoAdjustSt07;
import com.metoak.mes.entity.MoProcessStepProductionResult;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductionRecordQueryService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public <T> List<ProductionRecordDto> queryMethod1(DatabaseConfig config, Class<T> entityClass, int positionOffset, String[] attrNos) {
        Objects.requireNonNull(config, "Database config must not be null");
        Objects.requireNonNull(entityClass, "Entity class must not be null");

        TableName tableName = entityClass.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new IllegalArgumentException("实体缺少@TableName注解: " + entityClass.getName());
        }

        Set<String> attrNoFilter = buildAttrNoFilter(attrNos);

        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            List<T> entities = jdbcTemplate.query(
                    "SELECT * FROM " + tableName.value() + " where id > 686731 and id < 686740",
                    new BeanPropertyRowMapper<>(entityClass)
            );

            if (entities.isEmpty()) {
                return Collections.emptyList();
            }

            Map<Long, List<T>> groupedByResultId = new LinkedHashMap<>();
            List<T> nullResultGroup = new ArrayList<>();
            List<Long> order = new ArrayList<>();

            for (T entity : entities) {
                Long resultId = extractResultId(entity);
                if (resultId == null) {
                    if (nullResultGroup.isEmpty()) {
                        order.add(null);
                    }
                    nullResultGroup.add(entity);
                } else {
                    if (!groupedByResultId.containsKey(resultId)) {
                        groupedByResultId.put(resultId, new ArrayList<>());
                        order.add(resultId);
                    }
                    groupedByResultId.get(resultId).add(entity);
                }
            }

            List<ProductionRecordDto> results = new ArrayList<>();
            for (Long resultId : order) {
                if (resultId == null) {
                    results.add(buildDtoFromEntities(nullResultGroup, jdbcTemplate, null, positionOffset, attrNoFilter));
                } else {
                    results.add(buildDtoFromEntities(groupedByResultId.get(resultId), jdbcTemplate, resultId, positionOffset, attrNoFilter));
                }
            }

            return results;
        }
    }

    public List<ProductionRecordDto> queryMethod2(DatabaseConfig config, int positionOffset, String[] attrNos) {
        Objects.requireNonNull(config, "Database config must not be null");

        TableName tableName = MoAutoAdjustSt07.class.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new IllegalArgumentException("MoAutoAdjustSt07实体缺少@TableName注解");
        }

        Set<String> attrNoFilter = buildAttrNoFilter(attrNos);

        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            List<MoAutoAdjustSt07> entities = jdbcTemplate.query(
                    "SELECT * FROM " + tableName.value() + " where id > 686731 and id < 686740",
                    new BeanPropertyRowMapper<>(MoAutoAdjustSt07.class)
            );

            if (entities.isEmpty()) {
                return Collections.emptyList();
            }

            Map<Long, List<MoAutoAdjustSt07>> groupedByTaskId = new LinkedHashMap<>();
            List<MoAutoAdjustSt07> nullTaskGroup = new ArrayList<>();
            List<Long> order = new ArrayList<>();

            for (MoAutoAdjustSt07 entity : entities) {
                entity.setPosition(resolvePositionFromSide(entity.getSide()));

                Long taskId = entity.getTaskid();
                if (taskId == null) {
                    if (nullTaskGroup.isEmpty()) {
                        order.add(null);
                    }
                    nullTaskGroup.add(entity);
                } else {
                    if (!groupedByTaskId.containsKey(taskId)) {
                        groupedByTaskId.put(taskId, new ArrayList<>());
                        order.add(taskId);
                    }
                    groupedByTaskId.get(taskId).add(entity);
                }
            }

            List<ProductionRecordDto> results = new ArrayList<>();
            for (Long taskId : order) {
                if (taskId == null) {
                    results.add(buildDtoFromSt07Entities(nullTaskGroup, jdbcTemplate, null, positionOffset, attrNoFilter));
                } else {
                    results.add(buildDtoFromSt07Entities(groupedByTaskId.get(taskId), jdbcTemplate, taskId, positionOffset, attrNoFilter));
                }
            }

            return results;
        }
    }

    private Set<String> buildAttrNoFilter(String[] attrNos) {
        if (attrNos == null) {
            return Collections.emptySet();
        }
        return Arrays.stream(attrNos)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

    private <T> ProductionRecordDto buildDtoFromEntities(List<T> entities, JdbcTemplate jdbcTemplate, Long resultId, int positionOffset, Set<String> attrNoFilter) {
        ProductionRecordDto dto = new ProductionRecordDto();
        T first = entities.get(0);
        CommonAttrMapping.mapEntityFieldsToDto(first, dto, CommonAttrMapping.FIELD_TO_FIELD);

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
        for (T entity : entities) {
            List<AttrKeyValDto> attrs = FieldCodeMapper.extractAttrListFromObject(entity);
            for (AttrKeyValDto attr : attrs) {
                String originalPosition = attr.getPosition();
                CommonAttrMapping.mapEntityFieldsToDto(entity, attr, CommonAttrMapping.FIELD_TO_FIELD2);
                attr.setPosition(applyPositionOffset(originalPosition, positionOffset));
            }
            attrKeyValDtos.addAll(filterAttrKeyVals(attrs, attrNoFilter));
        }

        dto.setAttrKeyVals(attrKeyValDtos);
        System.out.println(dto);
        return dto;
    }

    private ProductionRecordDto buildDtoFromSt07Entities(List<MoAutoAdjustSt07> entities, JdbcTemplate jdbcTemplate, Long taskId, int positionOffset, Set<String> attrNoFilter) {
        ProductionRecordDto dto = new ProductionRecordDto();
        MoAutoAdjustSt07 first = entities.get(0);
        CommonAttrMapping.mapEntityFieldsToDto(first, dto, CommonAttrMapping.FIELD_TO_FIELD);

        if (taskId != null) {
            List<MoProcessStepProductionResult> processResults = jdbcTemplate.query(
                    "SELECT * FROM mo_process_step_production_result WHERE id = ?",
                    new BeanPropertyRowMapper<>(MoProcessStepProductionResult.class),
                    taskId
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
        for (MoAutoAdjustSt07 entity : entities) {
            if (entity.getPosition() == null) {
                entity.setPosition(resolvePositionFromSide(entity.getSide()));
            }

            List<AttrKeyValDto> attrs = FieldCodeMapper.extractAttrListFromObject(entity);
            for (AttrKeyValDto attr : attrs) {
                String originalPosition = attr.getPosition();
                CommonAttrMapping.mapEntityFieldsToDto(entity, attr, CommonAttrMapping.FIELD_TO_FIELD2);
                attr.setPosition(applyPositionOffset(originalPosition, positionOffset));
            }
            attrKeyValDtos.addAll(filterAttrKeyVals(attrs, attrNoFilter));
        }

        dto.setAttrKeyVals(attrKeyValDtos);
        return dto;
    }

    private List<AttrKeyValDto> filterAttrKeyVals(List<AttrKeyValDto> attrs, Set<String> attrNoFilter) {
        if (attrNoFilter == null || attrNoFilter.isEmpty()) {
            return attrs;
        }
        return attrs.stream()
                .filter(attr -> attrNoFilter.contains(attr.getNo()))
                .collect(Collectors.toList());
    }

    private String applyPositionOffset(String position, int positionOffset) {
        if (position == null || position.isEmpty()) {
            return position;
        }

        try {
            int numericPosition = Integer.parseInt(position);
            return String.valueOf(numericPosition + positionOffset);
        } catch (NumberFormatException ex) {
            return position;
        }
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

    private String resolvePositionFromSide(String side) {
        if (side == null) {
            return null;
        }

        String normalized = side.trim().toLowerCase();
        return switch (normalized) {
            case "left", "l" -> "1";
            case "right", "r" -> "右";
            default -> side;
        };
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
