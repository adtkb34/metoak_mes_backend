package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.mapping.*;
import com.metoak.mes.common.util.GroupingUtil;
import com.metoak.mes.dto.*;
import com.metoak.mes.entity.*;
import com.metoak.mes.enums.StepMappingEnum;
import com.metoak.mes.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.metoak.mes.common.util.TimestampUtils.convertToDateTime;


@Service
public class ProcessStepProductionRecords2ServiceImpl implements IProcessStepProductionRecords2Service {

    @Autowired
    private IMoAutoAdjustInfoService moAutoAdjustInfoService;

    @Autowired
    private IMoMaterialBindingService moMaterialBindingService;

    @Autowired
    private IMoProcessStepProductionResultService moProcessStepProductionResultService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ITransferBoxService transferBoxService;

    @Override
    public Long add(ProductionRecordDto productionRecordDto) {
        System.out.print(productionRecordDto);
        String startTime = productionRecordDto.getStartTime();
        String endTime = productionRecordDto.getEndTime();
        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();
        if (startTime == null || startTime.isBlank()) startTime = Long.toString(timestamp);
        if (endTime == null || endTime.isBlank()) endTime = Long.toString(timestamp);
        saveProBatchNo(productionRecordDto.getBoxNumber(), productionRecordDto.getProductCode());
        productionRecordDto.setProductBatchNo(productionRecordDto.getBoxNumber());
        MoProcessStepProductionResult moProcessStepProductionResult = MoProcessStepProductionResult.builder().
                stepType(productionRecordDto.getStepType()).
                stepTypeNo(productionRecordDto.getStepTypeNo()).
                productSn(productionRecordDto.getProductSn()).
                productBatchNo(productionRecordDto.getProductBatchNo()).
                startTime(convertToDateTime(startTime)).
                endTime(convertToDateTime(endTime)).
                errorCode(Integer.parseInt(productionRecordDto.getErrorNo())).
                ngReason(productionRecordDto.getError()).
                softwareTool(productionRecordDto.getSoftwareTool()).
                softwareToolVersion(productionRecordDto.getSoftwareToolVersion()).
                stationNum(Integer.parseInt(productionRecordDto.getDeviceNo())).
                operator(productionRecordDto.getOperator() == null ? productionRecordDto.getOperator_() : productionRecordDto.getOperator()).
                build();
        moProcessStepProductionResultService.save(moProcessStepProductionResult);
        Long moProcessStepProductionResultId = moProcessStepProductionResult.getId();
        add_material_binding(productionRecordDto, moProcessStepProductionResultId);

        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {

            ProcessMappingRegistry.ProcessMapping processMapping = ProcessMappingRegistry.get(productionRecordDto.getStepTypeNo());
            Object entity = null;
            try {
                entity = processMapping.getEntityClass().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new MOException("", -1);
            }
            // 设置静态字段（每个 group 相同）
            ReflectionMapper.setField(entity, "moProcessStepProductionResultId", moProcessStepProductionResultId.toString());
            CommonAttrMapping.mapDtoFields(entity, productionRecordDto, CommonAttrMapping.FIELD_TO_FIELD);
            CommonAttrMapping.mapDtoFields(entity, items.get(0), CommonAttrMapping.FIELD_TO_FIELD2);
            FieldCodeMapper.applyAttrListToObject(entity, items);
            // 保存
            Object service = applicationContext.getBean(processMapping.getServiceClass());
            Method saveMethod = null;
            try {
                saveMethod = service.getClass().getMethod("save", Object.class);
                saveMethod.invoke(service, entity);
            } catch (Exception e) {
                e.printStackTrace(); // 打印错误信息到控制台
                throw new MOException("", -1);
            }
        });

        return 0L;
    }

    @Override
    public Boolean executable(ExecutableDto executableDto) {
        return false;
    }


    public Boolean add_material_binding(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
        if (productionRecordDto.getMaterials() != null) {
            productionRecordDto.getMaterials().forEach(item -> {
                LambdaQueryWrapper<MoMaterialBinding> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(MoMaterialBinding::getCameraBatch, productionRecordDto.getProductBatchNo()).
                        eq(MoMaterialBinding::getCategoryNo, item.getCategoryNo()).
                        eq(MoMaterialBinding::getMaterialBatchNo, item.getBatchNo());
                if (moMaterialBindingService.list(wrapper).isEmpty()) {
                    moMaterialBindingService.save(MoMaterialBinding.builder().
                            cameraSn(productionRecordDto.getProductSn()).
                            cameraBatch(productionRecordDto.getProductBatchNo()).
                            category(item.getCategory()).
                            categoryNo(item.getCategoryNo()).
                            materialBatchNo(item.getBatchNo()).
                            materialSerialNo(item.getSerialNo()).
                            moProcessStepProductionResultId(moProcessStepProductionResultId).
                            position(item.getPosition()).
                            build());
                }
            });
        }
        return true;
    }

    public void saveProBatchNo(String boxNumber, String productCode) {
        LambdaQueryWrapper<TransferBox> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TransferBox::getBoxNumber, boxNumber);
        List<TransferBox> list = transferBoxService.list(wrapper);
        if (list.isEmpty()) {
            transferBoxService.save(TransferBox.builder().boxNumber(boxNumber).productCode(productCode).build());
        }
    }

}