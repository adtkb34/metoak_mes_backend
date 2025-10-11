package com.metoak.mes.common.mapping.bak;

public class ProcessEntityBuilder {

//    @SuppressWarnings("unchecked")
//    public static Object buildEntityFromDtos(String processNo, List<AttrKeyValDto> dtoList) {
//        ProcessMappingRegistry.ProcessMapping mapping = ProcessMappingRegistry.get(processNo);
//        if (mapping == null) {
//            throw new IllegalArgumentException("未注册工序编号：" + processNo);
//        }
//
//        Class<?> entityClass = mapping.getEntityClass();
//        Class<?> constantClass = mapping.getConstantClass();
//        Class<?> serviceClass = mapping.getServiceClass();
//
//        try {
//            Object entity = entityClass.getDeclaredConstructor().newInstance();
//            Map<String, String> noToFieldMap = MappingFieldHelper.buildNoToFieldMapFromConstants(constantClass);
//            DtoEntityMapper.applyDtoToEntity(dtoList, entity, noToFieldMap);
////            serviceClass.save(entity);
//            return entity;
//        } catch (Exception e) {
//            throw new RuntimeException("创建实体失败", e);
//        }
//    }
}