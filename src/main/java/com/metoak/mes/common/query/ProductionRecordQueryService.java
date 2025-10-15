package com.metoak.mes.common.query;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.common.annotate.FieldCode;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.config.DatabaseConfig;
import com.metoak.mes.common.mapping.CommonAttrMapping;
import com.metoak.mes.common.mapping.ProcessMappingRegistry;
import com.metoak.mes.dto.AttrKeyValDto;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.entity.Calibresult;
import com.metoak.mes.entity.MoAutoAdjustInfo;
import com.metoak.mes.entity.MoAutoAdjustSt07;
import com.metoak.mes.entity.MoProcessStepProductionResult;
import com.metoak.mes.enums.DeviceEnum;
import com.metoak.mes.enums.OriginEnum;
import com.metoak.mes.enums.StepMappingEnum;
import com.metoak.mes.service.ICalibresultService;
import com.metoak.mes.service.IMoAutoAdjustSt08Service;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
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
                                                 Integer count,
                                                 String stepTypeNo) {
        OriginEnum originEnum = OriginEnum.fromCode(Objects.requireNonNull(origin, "origin must not be null"));
        DeviceEnum deviceEnum = device != null ? DeviceEnum.fromCode(device) : null;

        DatabaseConfig databaseConfig = buildDatabaseConfig(originEnum);

        int positionOffset = originEnum == OriginEnum.SUZHOU ? 1 : 0;

        if (deviceEnum == DeviceEnum.GUANGHAOJIE) {
            return queryMethod2(
                    databaseConfig,
                    positionOffset,
                    attrKeys,
                    String.valueOf(position),
                    stage,
                    startTime,
                    endTime,
                    count * 4
            );
        } else {
            Class<?> serviceClass = determineServiceClass(stepTypeNo);
            if (serviceClass == null) return Collections.emptyList();
            if (serviceClass == ICalibresultService.class) {
                return queryMethod3(
                        databaseConfig,
                        positionOffset,
                        attrKeys,
                        station,
                        position,
                        stage,
                        startTime,
                        endTime,
                        count,
                        stepTypeNo
                );
            }
            return queryMethod1(
                    databaseConfig,
                    serviceClass,
                    positionOffset,
                    attrKeys,
                    position == null ? null : String.valueOf(position - positionOffset),
                    stage,
                    "add_time",
                    startTime,
                    endTime,
                    count,
                    stepTypeNo
            );
        }

//        return Collections.emptyList();
    }

    private Class<?> determineServiceClass(String stepTypeNo) {
        if (StringUtils.hasText(stepTypeNo)) {
            ProcessMappingRegistry.ProcessMapping mapping = ProcessMappingRegistry.get(stepTypeNo);
            if (mapping != null && mapping.getServiceClass() != null) {
                return mapping.getServiceClass();
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> resolveEntityClass(Class<?> serviceClass) {
        Class<?> entityClass = resolveEntityClassInternal(serviceClass);
        if (entityClass == null) {
            return null;
        }
        return (Class<T>) entityClass;
    }

    private Class<?> resolveEntityClassInternal(Class<?> serviceClass) {
        if (serviceClass == null) {
            return null;
        }

        for (Type genericInterface : serviceClass.getGenericInterfaces()) {
            Class<?> entityClass = resolveEntityClassFromType(genericInterface);
            if (entityClass != null) {
                return entityClass;
            }
        }

        Type genericSuperclass = serviceClass.getGenericSuperclass();
        Class<?> entityClass = resolveEntityClassFromType(genericSuperclass);
        if (entityClass != null) {
            return entityClass;
        }

        Class<?> superclass = serviceClass.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            entityClass = resolveEntityClassInternal(superclass);
            if (entityClass != null) {
                return entityClass;
            }
        }

        for (Class<?> interfaceClass : serviceClass.getInterfaces()) {
            entityClass = resolveEntityClassInternal(interfaceClass);
            if (entityClass != null) {
                return entityClass;
            }
        }

        return null;
    }

    private Class<?> resolveEntityClassFromType(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (rawType instanceof Class && IService.class.isAssignableFrom((Class<?>) rawType)) {
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments.length > 0) {
                    Type entityType = actualTypeArguments[0];
                    if (entityType instanceof Class) {
                        return (Class<?>) entityType;
                    }
                }
            }

            if (rawType instanceof Class) {
                Class<?> entityClass = resolveEntityClassInternal((Class<?>) rawType);
                if (entityClass != null) {
                    return entityClass;
                }
            }

            for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                Class<?> entityClass = resolveEntityClassFromType(actualTypeArgument);
                if (entityClass != null) {
                    return entityClass;
                }
            }
        } else if (type instanceof Class) {
            return resolveEntityClassInternal((Class<?>) type);
        }

        return null;
    }

    public <T> List<ProductionRecordDto> queryMethod1(DatabaseConfig config,
                                                     Class<?> serviceClass,
                                                     Integer positionOffset,
                                                     String[] attrKeys,
                                                     String position,
                                                     Integer stage,
                                                     String timeField,
                                                     String startTime,
                                                     String endTime,
                                                     Integer count,
                                                     String stepTypeNo) {
        Objects.requireNonNull(config, "Database config must not be null");
        Objects.requireNonNull(serviceClass, "Service class must not be null");
        Objects.requireNonNull(stepTypeNo, "stepTypeNo must not be null");

        Class<T> entityClass = resolveEntityClass(serviceClass);
        if (entityClass == null) {
            throw new IllegalArgumentException("无法从服务类解析实体类型: " + serviceClass.getName());
        }

        TableName tableName = entityClass.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new IllegalArgumentException("实体缺少@TableName注解: " + entityClass.getName());
        }

        Set<String> attrKeyFilter = buildAttrKeyFilter(attrKeys);
        Map<String, Set<String>> attrNoToColumns = buildAttrNoToColumnMap(entityClass);

        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String baseAlias = "t";
            String resultAlias = "r";
            String selectColumns = qualifyColumns(buildSelectColumns(entityClass, attrKeyFilter), baseAlias);
            StringBuilder sql = new StringBuilder("SELECT ")
                    .append(selectColumns)
                    .append(" FROM ")
                    .append(tableName.value())
                    .append(" ")
                    .append(baseAlias)
                    .append(" LEFT JOIN mo_process_step_production_result ")
                    .append(resultAlias)
                    .append(" ON ")
                    .append(baseAlias)
                    .append(".mo_process_step_production_result_id = ")
                    .append(resultAlias)
                    .append(".id");
            List<Object> params = new ArrayList<>();
            appendConditions(sql, Collections.singletonList(resultAlias + ".step_type_no = ?"));
            params.add(stepTypeNo);
            String normalizedTimeField = normalizeTimeField(timeField);
            String qualifiedTimeField = normalizedTimeField == null ? null : baseAlias + "." + normalizedTimeField;
            boolean hasTimeFilter = appendTimeFilter(sql, params, qualifiedTimeField, startTime, endTime);
            appendStagePositionFilter(sql, params, baseAlias + ".position", position, baseAlias + ".stage", stage != null ? stage.toString() : null);
            if (!hasTimeFilter && normalizedTimeField != null) {
                sql.append(" ORDER BY ").append(qualifiedTimeField).append(" DESC LIMIT ?");
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
            appendStagePositionFilter(sql, params, "side", Objects.equals(position, "1") ? "left" : "right", null, null);
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

    public List<ProductionRecordDto> queryMethod3(DatabaseConfig config,
                                                  Integer positionOffset,
                                                  String[] attrKeys,
                                                  Integer station,
                                                  Integer position,
                                                  Integer stage,
                                                  String startTime,
                                                  String endTime,
                                                  Integer count,
                                                  String stepTypeNo) {
        Objects.requireNonNull(config, "Database config must not be null");
        Objects.requireNonNull(stepTypeNo, "stepTypeNo must not be null");

        Set<String> attrKeyFilter = buildAttrKeyFilter(attrKeys);
        Map<String, Set<String>> attrNoToColumns = buildAttrNoToColumnMap(Calibresult.class);

        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String baseAlias = "t";
            String resultAlias = "r";
            String selectColumns = qualifyColumns(buildSelectColumns(Calibresult.class, attrKeyFilter), baseAlias);
            StringBuilder sql = new StringBuilder("SELECT ")
                    .append(selectColumns)
                    .append(" FROM calibresult ")
                    .append(baseAlias)
                    .append(" LEFT JOIN mo_process_step_production_result ")
                    .append(resultAlias)
                    .append(" ON ")
                    .append(baseAlias)
                    .append(".mo_process_step_production_result_id = ")
                    .append(resultAlias)
                    .append(".id");
            List<Object> params = new ArrayList<>();
            appendConditions(sql, Collections.singletonList(resultAlias + ".step_type_no = ?"));
            params.add(stepTypeNo);

            if (station != null) {
                appendConditions(sql, Collections.singletonList(baseAlias + ".Station = ?"));
                params.add(station);
            }

            String normalizedTimeField = normalizeTimeField("TimeStamp");
            String qualifiedTimeField = normalizedTimeField == null ? null : baseAlias + "." + normalizedTimeField;
            boolean hasTimeFilter = appendTimeFilter(sql, params, qualifiedTimeField, startTime, endTime);

            if (!hasTimeFilter && qualifiedTimeField != null) {
                sql.append(" ORDER BY ").append(qualifiedTimeField).append(" DESC LIMIT ?");
                params.add(count);
            }

            List<Calibresult> entities = jdbcTemplate.query(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(Calibresult.class),
                    params.toArray()
            );

            if (entities.isEmpty()) {
                return Collections.emptyList();
            }

            Map<String, CalibrationInfo> calibrationInfoMap = fetchCalibrationInfo(jdbcTemplate, entities);

            Map<Long, List<Calibresult>> groupedByResultId = new LinkedHashMap<>();
            List<Calibresult> nullResultGroup = new ArrayList<>();
            List<Long> order = new ArrayList<>();

            for (Calibresult entity : entities) {
                Long resultId = entity.getMoProcessStepProductionResultId();
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
                List<Calibresult> group = resultId == null ? nullResultGroup : groupedByResultId.get(resultId);
                if (group == null || group.isEmpty()) {
                    continue;
                }
                ProductionRecordDto dto = buildDtoFromCalibresultEntities(
                        group,
                        jdbcTemplate,
                        resultId,
                        positionOffset,
                        attrKeyFilter,
                        attrNoToColumns,
                        calibrationInfoMap,
                        position
                );
                results.add(dto);
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

        List<String> conditions = new ArrayList<>();
        if (hasStart) {
            conditions.add(timeField + " >= ?");
            params.add(startTime);
        }
        if (hasEnd) {
            conditions.add(timeField + " <= ?");
            params.add(endTime);
        }
        appendConditions(sql, conditions);
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

        MoAutoAdjustInfo autoAdjustInfo = findClosestAutoAdjustInfo(jdbcTemplate, entities);
        if (autoAdjustInfo != null) {
            dto.setProductSn(autoAdjustInfo.getBeamSn());
            dto.setErrorNo(String.valueOf(autoAdjustInfo.getErrorCode()));
            dto.setError(autoAdjustInfo.getNgReason());
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

    private ProductionRecordDto buildDtoFromCalibresultEntities(List<Calibresult> entities,
                                                                JdbcTemplate jdbcTemplate,
                                                                Long resultId,
                                                                int positionOffset,
                                                                Set<String> attrKeyFilter,
                                                                Map<String, Set<String>> attrNoToColumns,
                                                                Map<String, CalibrationInfo> calibrationInfoMap,
                                                                Integer requestedPosition) {
        ProductionRecordDto dto = buildDtoFromEntities(entities, jdbcTemplate, resultId, positionOffset, attrKeyFilter, attrNoToColumns);

        Calibresult first = entities.get(0);
        String key = buildCalibrationKey(first.getCameraSN(), normalizeTimestampString(first.getTimeStamp()));
        if (key != null) {
            CalibrationInfo info = calibrationInfoMap.get(key);
            if (info != null) {
                if (info.errorCode != null) {
                    dto.setErrorNo(String.valueOf(info.errorCode));
                }
                if (StringUtils.hasText(info.operator) && !StringUtils.hasText(dto.getOperator())) {
                    dto.setOperator(info.operator);
                }
            }
        }

        if (requestedPosition != null && dto.getAttrKeyVals() != null) {
            String requestedPositionStr = String.valueOf(requestedPosition);
            List<AttrKeyValDto> filtered = dto.getAttrKeyVals().stream()
                    .filter(attr -> requestedPositionStr.equals(attr.getPosition()))
                    .collect(Collectors.toList());
            dto.setAttrKeyVals(filtered);
        }

        return dto;
    }

    private MoAutoAdjustInfo findClosestAutoAdjustInfo(JdbcTemplate jdbcTemplate, List<MoAutoAdjustSt07> entities) {
        if (entities == null || entities.isEmpty()) {
            return null;
        }

        String beamSn = entities.stream()
                .map(MoAutoAdjustSt07::getBeamSn)
                .filter(StringUtils::hasText)
                .findFirst()
                .orElse(null);

        if (!StringUtils.hasText(beamSn)) {
            return null;
        }

        List<MoAutoAdjustInfo> infos = jdbcTemplate.query(
                "SELECT * FROM mo_auto_adjust_info WHERE beam_sn = ?",
                new BeanPropertyRowMapper<>(MoAutoAdjustInfo.class),
                beamSn
        );

        if (infos.isEmpty()) {
            return null;
        }

        List<LocalDateTime> referenceTimes = entities.stream()
                .map(MoAutoAdjustSt07::getAddTime)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<MoAutoAdjustInfo> infosWithTime = infos.stream()
                .filter(info -> resolveInfoTime(info) != null)
                .collect(Collectors.toList());

        if (referenceTimes.isEmpty()) {
            if (!infosWithTime.isEmpty()) {
                return infosWithTime.stream()
                        .max(Comparator.comparing(this::resolveInfoTime))
                        .orElse(infos.get(0));
            }
            return infos.get(0);
        }

        if (!infosWithTime.isEmpty()) {
            return infosWithTime.stream()
                    .min(Comparator.comparingLong(info -> calculateMinimumTimeDifferenceMillis(info, referenceTimes)))
                    .orElse(infos.get(0));
        }

        return infos.get(0);
    }

    private long calculateMinimumTimeDifferenceMillis(MoAutoAdjustInfo info, List<LocalDateTime> referenceTimes) {
        LocalDateTime infoTime = resolveInfoTime(info);
        if (infoTime == null || referenceTimes == null || referenceTimes.isEmpty()) {
            return Long.MAX_VALUE;
        }

        long minDiff = Long.MAX_VALUE;
        for (LocalDateTime referenceTime : referenceTimes) {
            if (referenceTime == null) {
                continue;
            }
            long diff = Duration.between(referenceTime, infoTime).abs().toMillis();
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }

    private LocalDateTime resolveInfoTime(MoAutoAdjustInfo info) {
        if (info == null) {
            return null;
        }
        if (info.getOperationTime() != null) {
            return info.getOperationTime();
        }
        return info.getAddTime();
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

    private Map<String, CalibrationInfo> fetchCalibrationInfo(JdbcTemplate jdbcTemplate, List<Calibresult> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Set<String>> timestampsByCamera = new LinkedHashMap<>();
        for (Calibresult entity : entities) {
            String cameraSn = entity.getCameraSN();
            String normalizedTimestamp = normalizeTimestampString(entity.getTimeStamp());
            if (!StringUtils.hasText(cameraSn) || !StringUtils.hasText(normalizedTimestamp)) {
                continue;
            }
            timestampsByCamera.computeIfAbsent(cameraSn.trim(), key -> new LinkedHashSet<>()).add(normalizedTimestamp);
        }

        if (timestampsByCamera.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, CalibrationInfo> result = new HashMap<>();
        String formattedColumn = "DATE_FORMAT(start_time, '%Y-%m-%d %H:%i:%s')";

        for (Map.Entry<String, Set<String>> entry : timestampsByCamera.entrySet()) {
            String cameraSn = entry.getKey();
            Set<String> timestamps = entry.getValue();
            if (timestamps.isEmpty()) {
                continue;
            }

            String placeholders = String.join(", ", Collections.nCopies(timestamps.size(), "?"));
            String sql = "SELECT camera_sn, " + formattedColumn + " AS start_time_str, error_code, operator " +
                    "FROM mo_calibration WHERE camera_sn = ? AND " + formattedColumn + " IN (" + placeholders + ")";
            List<Object> params = new ArrayList<>();
            params.add(cameraSn);
            params.addAll(timestamps);

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params.toArray());
            for (Map<String, Object> row : rows) {
                String startTimeStr = normalizeTimestampString((String) row.get("start_time_str"));
                String key = buildCalibrationKey((String) row.get("camera_sn"), startTimeStr);
                if (key == null) {
                    continue;
                }
                Integer errorCode = null;
                Object errorVal = row.get("error_code");
                if (errorVal instanceof Number number) {
                    errorCode = number.intValue();
                }
                String operator = (String) row.get("operator");
                result.put(key, new CalibrationInfo(errorCode, operator));
            }
        }

        return result;
    }

    private String normalizeTimestampString(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }

        String normalized = value.trim().replace('T', ' ');
        int dotIndex = normalized.indexOf('.');
        if (dotIndex > 0) {
            normalized = normalized.substring(0, dotIndex);
        }
        if (normalized.length() > 19) {
            normalized = normalized.substring(0, 19);
        }
        return normalized;
    }

    private String buildCalibrationKey(String cameraSn, String timestamp) {
        if (!StringUtils.hasText(cameraSn) || !StringUtils.hasText(timestamp)) {
            return null;
        }
        return cameraSn.trim() + "|" + timestamp.trim();
    }

    private static final class CalibrationInfo {
        private final Integer errorCode;
        private final String operator;

        private CalibrationInfo(Integer errorCode, String operator) {
            this.errorCode = errorCode;
            this.operator = operator;
        }
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
                                              String positionColumn,
                                              String positionValue,
                                              String stageColumn,
                                              String stageValue) {
        boolean hasPosition = StringUtils.hasText(positionValue);
        boolean hasStage = StringUtils.hasText(stageValue);
        if (!hasPosition && !hasStage) {
            return false;
        }

        List<String> conditions = new ArrayList<>();
        if (hasPosition) {
            conditions.add(positionColumn + " = ?");
            params.add(positionValue);
        }
        if (hasStage && StringUtils.hasText(stageColumn)) {
            conditions.add(stageColumn + " = ?");
            params.add(stageValue);
        }

        appendConditions(sql, conditions);
        return true;
    }

    private void appendConditions(StringBuilder sql, List<String> conditions) {
        if (conditions == null) {
            return;
        }
        List<String> filtered = conditions.stream()
                .filter(StringUtils::hasText)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            return;
        }

        if (!containsWhere(sql)) {
            sql.append(" WHERE ");
        } else {
            sql.append(" AND ");
        }
        sql.append(String.join(" AND ", filtered));
    }

    private boolean containsWhere(StringBuilder sql) {
        String sqlStr = sql.toString().toLowerCase(Locale.ROOT);
        return sqlStr.contains(" where ");
    }

    private String qualifyColumns(String columns, String tableAlias) {
        if (!StringUtils.hasText(columns)) {
            return columns;
        }
        if (!StringUtils.hasText(tableAlias)) {
            return columns;
        }

        String trimmedColumns = columns.trim();
        if ("*".equals(trimmedColumns)) {
            return tableAlias + ".*";
        }

        String[] parts = trimmedColumns.split(",");
        List<String> qualified = new ArrayList<>(parts.length);
        for (String part : parts) {
            String item = part.trim();
            if (item.isEmpty()) {
                continue;
            }

            int asIndex = indexOfIgnoreCase(item, " as ");
            if (asIndex >= 0) {
                String column = item.substring(0, asIndex).trim();
                String aliasPart = item.substring(asIndex);
                if (!column.contains(".")) {
                    column = tableAlias + "." + column;
                }
                qualified.add(column + aliasPart);
            } else {
                if (!item.contains(".")) {
                    qualified.add(tableAlias + "." + item);
                } else {
                    qualified.add(item);
                }
            }
        }

        if (qualified.isEmpty()) {
            return tableAlias + ".*";
        }

        return String.join(", ", qualified);
    }

    private int indexOfIgnoreCase(String value, String search) {
        if (value == null || search == null) {
            return -1;
        }
        return value.toLowerCase(Locale.ROOT).indexOf(search.toLowerCase(Locale.ROOT));
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
