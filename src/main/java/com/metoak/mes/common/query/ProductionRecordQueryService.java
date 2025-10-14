package com.metoak.mes.common.query;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.metoak.mes.common.annotate.FieldCode;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.config.DatabaseConfig;
import com.metoak.mes.common.mapping.CommonAttrMapping;
import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.entity.MoAutoAdjustSt07;
import com.metoak.mes.entity.MoAutoAdjustSt08;
import com.metoak.mes.entity.MoProcessStepProductionResult;
import com.metoak.mes.enums.DeviceEnum;
import com.metoak.mes.enums.OriginEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductionRecordQueryService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public List<ProductionRecordDto> queryMethod(String[] attrKeys,
                                                 Integer origin,
                                                 Integer device,
                                                 Integer station,
                                                 Integer position,
                                                 Integer stage,
                                                String startTime,
                                                String endTime,
                                                 Integer count) {
        OriginEnum originEnum = OriginEnum.fromCode(Objects.requireNonNull(origin, "origin must not be null"));
        DeviceEnum deviceEnum = DeviceEnum.fromCode(Objects.requireNonNull(device, "device must not be null"));

        DatabaseConfig databaseConfig = buildDatabaseConfig(originEnum);

        int positionOffset = deviceEnum.requiresPositionOffset() ? 1 : 0;

        if (deviceEnum.usesMoAutoAdjustSt08()) {
            return queryMethod1(
                    databaseConfig,
                    MoAutoAdjustSt08.class,
                    positionOffset,
                    attrKeys,
                    String.valueOf(position - positionOffset),
                    stage,
                    "add_time",
                    startTime,
                    endTime,
                    count
            );
        } else if (deviceEnum == DeviceEnum.GUANGHAOJIE) {
            return queryMethod2(
                    databaseConfig,
                    positionOffset,
                    attrKeys,
                    String.valueOf(position),
                    stage,
                    startTime,
                    endTime,
                    count
            );
        }

        return Collections.emptyList();
    }

    public <T> List<ProductionRecordDto> queryMethod1(DatabaseConfig config,
                                                     Class<T> entityClass,
                                                     Integer positionOffset,
                                                     String[] attrKeys,
                                                     String position,
                                                      Integer stage,
                                                     String timeField,
                                                     String startTime,
                                                     String endTime,
                                                      Integer count) {
        Objects.requireNonNull(config, "Database config must not be null");
        Objects.requireNonNull(entityClass, "Entity class must not be null");

        TableName tableName = entityClass.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new IllegalArgumentException("实体缺少@TableName注解: " + entityClass.getName());
        }

        Set<String> attrKeyFilter = buildAttrKeyFilter(attrKeys);
        Map<String, Set<String>> attrNoToColumns = buildAttrNoToColumnMap(entityClass);

        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String selectColumns = buildSelectColumns(entityClass, attrKeyFilter);
            StringBuilder sql = new StringBuilder("SELECT ").append(selectColumns).append(" FROM ").append(tableName.value());
            List<Object> params = new ArrayList<>();
            String normalizedTimeField = normalizeTimeField(timeField);
            boolean hasTimeFilter = appendTimeFilter(sql, params, normalizedTimeField, startTime, endTime);
            appendStagePositionFilter(sql, params, hasTimeFilter, "position", position, "stage", stage.toString());
            if (!hasTimeFilter && normalizedTimeField != null) {
                sql.append(" ORDER BY ").append(normalizedTimeField).append(" DESC LIMIT ?");
                params.add(count);
            }

            List<T> entities = jdbcTemplate.query(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(entityClass),
                    params.toArray()
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
                    results.add(buildDtoFromEntities(nullResultGroup, jdbcTemplate, null, positionOffset, attrKeyFilter, attrNoToColumns));
                } else {
                    results.add(buildDtoFromEntities(groupedByResultId.get(resultId), jdbcTemplate, resultId, positionOffset, attrKeyFilter, attrNoToColumns));
                }
            }

            return results;
        }
    }

    public List<ProductionRecordDto> queryMethod2(DatabaseConfig config,
                                                  Integer positionOffset,
                                                 String[] attrKeys,
                                                 String position,
                                                  Integer stage,
                                                 String startTime,
                                                 String endTime,
                                                  Integer count) {
        Objects.requireNonNull(config, "Database config must not be null");

        TableName tableName = MoAutoAdjustSt07.class.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new IllegalArgumentException("MoAutoAdjustSt07实体缺少@TableName注解");
        }

        Set<String> attrKeyFilter = buildAttrKeyFilter(attrKeys);
        Map<String, Set<String>> attrNoToColumns = buildAttrNoToColumnMap(MoAutoAdjustSt07.class);

        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String selectColumns = buildSelectColumns(MoAutoAdjustSt07.class, attrKeyFilter);
            StringBuilder sql = new StringBuilder("SELECT ").append(selectColumns).append(" FROM ").append(tableName.value());
            List<Object> params = new ArrayList<>();
            String normalizedTimeField = normalizeTimeField("add_time");
            boolean hasTimeFilter = appendTimeFilter(sql, params, normalizedTimeField, startTime, endTime);
            appendStagePositionFilter(sql, params, hasTimeFilter, "side", Objects.equals(position, "1") ? "left" : "right", null, null);
            if (!hasTimeFilter && normalizedTimeField != null) {
                sql.append(" ORDER BY ").append(normalizedTimeField).append(" DESC LIMIT ?");
                params.add(count);
            }

            List<MoAutoAdjustSt07> entities = jdbcTemplate.query(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(MoAutoAdjustSt07.class),
                    params.toArray()
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
                    results.add(buildDtoFromSt07Entities(nullTaskGroup, jdbcTemplate, null, positionOffset, attrKeyFilter, attrNoToColumns));
                } else {
                    List<MoAutoAdjustSt07> objects = new ArrayList<>();

                    List<MoAutoAdjustSt07> list = groupedByTaskId.get(taskId);
                    list.sort(Comparator.comparing(MoAutoAdjustSt07::getId).reversed());
                    int size = list.size();
                    if (stage != null) {
                        if (position == null) {
                            int idx1 = 6 - stage * 2;
                            int idx2 = 7 - stage * 2;
                            if (idx1 >= 0 && idx1 < size) {
                                objects.add(list.get(idx1));
                            }
                            if (idx2 >= 0 && idx2 < size) {
                                objects.add(list.get(idx2));
                            }
                        } else {
                            int idx = 3 - stage;
                            if (idx >= 0 && idx < size) {
                                objects.add(list.get(idx));
                            }
                        }
                    } else {
                        objects = list;
                    }

                    if (!objects.isEmpty()) {
                        results.add(buildDtoFromSt07Entities(objects, jdbcTemplate, taskId, positionOffset, attrKeyFilter, attrNoToColumns));
                    }

                }
            }

            return results;
        }
    }

    private boolean appendTimeFilter(StringBuilder sql, List<Object> params, String timeField, String startTime, String endTime) {
        if (timeField == null) {
            return false;
        }

        boolean hasStart = StringUtils.hasText(startTime);
        boolean hasEnd = StringUtils.hasText(endTime);
        if (!hasStart && !hasEnd) {
            return false;
        }

        sql.append(" WHERE ");
        List<String> conditions = new ArrayList<>();
        if (hasStart) {
            conditions.add(timeField + " >= ?");
            params.add(startTime);
        }
        if (hasEnd) {
            conditions.add(timeField + " <= ?");
            params.add(endTime);
        }
        sql.append(String.join(" AND ", conditions));
        return true;
    }

    private String normalizeTimeField(String timeField) {
        if (!StringUtils.hasText(timeField)) {
            return null;
        }

        String normalizedField = timeField.trim();
        if (!StringUtils.hasText(normalizedField)) {
            return null;
        }
        if (!normalizedField.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("非法时间字段: " + timeField);
        }
        return normalizedField;
    }

    private Set<String> buildAttrKeyFilter(String[] attrKeys) {
        if (attrKeys == null) {
            return Collections.emptySet();
        }
        return Arrays.stream(attrKeys)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    private <T> String buildSelectColumns(Class<T> entityClass, Set<String> attrKeyFilter) {
        if (attrKeyFilter == null || attrKeyFilter.isEmpty()) {
            return "*";
        }

        List<String> columns = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null && !tableField.exist()) {
                continue;
            }

            String columnName = resolveColumnName(field);
            boolean includeField;
            if (field.isAnnotationPresent(FieldCode.class)) {
                includeField = attrKeyFilter.contains(columnName.toLowerCase());
            } else {
                includeField = true;
            }

            if (!includeField) {
                continue;
            }

            columns.add(buildColumnWithAlias(columnName, field.getName()));
        }

        if (columns.isEmpty()) {
            return "*";
        }

        return String.join(", ", columns);
    }

    private String buildColumnWithAlias(String columnName, String fieldName) {
        if (columnName.equalsIgnoreCase(fieldName)) {
            return columnName;
        }
        return columnName + " AS " + fieldName;
    }

    private String resolveColumnName(Field field) {
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField != null && StringUtils.hasText(tableField.value())) {
            return tableField.value();
        }

        TableId tableId = field.getAnnotation(TableId.class);
        if (tableId != null && StringUtils.hasText(tableId.value())) {
            return tableId.value();
        }

        return camelToSnake(field.getName());
    }

    private String camelToSnake(String value) {
        if (!StringUtils.hasText(value)) {
            return value;
        }

        StringBuilder builder = new StringBuilder(value.length() + 5);
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (Character.isUpperCase(ch)) {
                boolean hasPrev = i > 0;
                boolean hasNext = i + 1 < value.length();
                boolean shouldAppendUnderscore = false;
                if (hasPrev) {
                    char prev = value.charAt(i - 1);
                    shouldAppendUnderscore = Character.isLowerCase(prev) || Character.isDigit(prev);
                }
                if (!shouldAppendUnderscore && hasNext) {
                    char next = value.charAt(i + 1);
                    shouldAppendUnderscore = Character.isLowerCase(next);
                }
                if (shouldAppendUnderscore) {
                    builder.append('_');
                }
                builder.append(Character.toLowerCase(ch));
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    private <T> ProductionRecordDto buildDtoFromEntities(List<T> entities,
                                                        JdbcTemplate jdbcTemplate,
                                                        Long resultId,
                                                        int positionOffset,
                                                        Set<String> attrKeyFilter,
                                                        Map<String, Set<String>> attrNoToColumns) {
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
            attrKeyValDtos.addAll(filterAttrKeyVals(attrs, attrKeyFilter, attrNoToColumns));
        }

        dto.setAttrKeyVals(attrKeyValDtos);
        return dto;
    }

    private ProductionRecordDto buildDtoFromSt07Entities(List<MoAutoAdjustSt07> entities,
                                                        JdbcTemplate jdbcTemplate,
                                                        Long taskId,
                                                        int positionOffset,
                                                        Set<String> attrKeyFilter,
                                                        Map<String, Set<String>> attrNoToColumns) {
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
            attrKeyValDtos.addAll(filterAttrKeyVals(attrs, attrKeyFilter, attrNoToColumns));
        }

        dto.setAttrKeyVals(attrKeyValDtos);
        return dto;
    }

    private List<AttrKeyValDto> filterAttrKeyVals(List<AttrKeyValDto> attrs,
                                                  Set<String> attrKeyFilter,
                                                  Map<String, Set<String>> attrNoToColumns) {
        if (attrKeyFilter == null || attrKeyFilter.isEmpty()) {
            return attrs;
        }
        if (attrNoToColumns == null || attrNoToColumns.isEmpty()) {
            return attrs;
        }
        return attrs.stream()
                .filter(attr -> {
                    Set<String> columns = attrNoToColumns.get(attr.getNo());
                    if (columns == null || columns.isEmpty()) {
                        return false;
                    }
                    for (String column : columns) {
                        if (attrKeyFilter.contains(column.toLowerCase())) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private <T> Map<String, Set<String>> buildAttrNoToColumnMap(Class<T> entityClass) {
        Map<String, Set<String>> mapping = new LinkedHashMap<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            FieldCode fieldCode = field.getAnnotation(FieldCode.class);
            if (fieldCode == null) {
                continue;
            }

            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null && !tableField.exist()) {
                continue;
            }

            String columnName = resolveColumnName(field).toLowerCase();
            mapping.computeIfAbsent(fieldCode.no(), key -> new LinkedHashSet<>()).add(columnName);
        }
        return mapping;
    }

    private boolean appendStagePositionFilter(StringBuilder sql,
                                              List<Object> params,
                                              boolean hasExistingCondition,
                                              String positionColumn,
                                              String positionValue,
                                              String stageColumn,
                                              String stageValue) {
        boolean hasPosition = StringUtils.hasText(positionValue);
        boolean hasStage = StringUtils.hasText(stageValue);
        if (!hasPosition && !hasStage) {
            return false;
        }

        if (!hasExistingCondition) {
            sql.append(" WHERE ");
        } else {
            sql.append(" AND ");
        }

        List<String> conditions = new ArrayList<>();
        if (hasPosition) {
            conditions.add(positionColumn + " = ?");
            params.add(positionValue);
        }
        if (hasStage) {
            conditions.add(stageColumn + " = ?");
            params.add(stageValue);
        }

        sql.append(String.join(" AND ", conditions));
        return true;
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
            case "left", "l" -> "0";
            case "right", "r" -> "1";
            default -> side;
        };
    }

    private DatabaseConfig buildDatabaseConfig(OriginEnum origin) {
        if (origin == OriginEnum.SUZHOU) {
            return DatabaseConfig.builder()
                    .url("jdbc:mysql://11.11.11.13:3306/mo_mes_db")
                    .username("root")
                    .password("momeshou")
                    .build();
        }

        return DatabaseConfig.builder()
                .url("jdbc:mysql://192.168.188.11:3306/mo_mes_db")
                .username("root")
                .password("momeshou")
                .build();
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
