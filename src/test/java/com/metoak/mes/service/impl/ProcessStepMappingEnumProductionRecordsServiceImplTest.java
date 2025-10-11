package com.metoak.mes.service.impl;

import com.metoak.mes.dto.*;
import com.metoak.mes.entity.*;
import com.metoak.mes.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProcessStepMappingEnumProductionRecordsServiceImplTest {

//    @Autowired
//    private ProductionController productionController;
//
//    @Autowired
//
//    private IMoDirtyCheckService moDirtyCheckService;
//
//    @Autowired
//    private IUvDispensingService uvDispensingService;
//
//    @Autowired
//    private IMoAutoAdjustSt08Service moAutoAdjustSt08Service;
//
//    @Autowired
//    private IMoAutoAdjustInfoService moAutoAdjustInfoService;
//
//    @Autowired
//    private IMoDeviceStatusService moDeviceStatusService;
//
//    @Autowired
//    private IMoBeamInfoService moBeamInfoService;
//
//    @Autowired
//    private IMoLensAssemblyService moLensAssemblyService;
//
//    @Autowired
//    private IMoLensCapFasteningService moLensCapFasteningService;
//
//    @Autowired
//    private IMoLensDispensingService moLensDispensingService;
//
//    @Autowired
//    private IMoIrPastingService moIrPastingService;
//
//    @Autowired
//    private IMoLensBakingService moLensBakingService;

    @Autowired
    private ProcessStepProductionRecordsServiceImpl processStepProductionRecordsService;

    @Autowired
    private IMoProcessStepProductionResultService moProcessStepProductionResultService;

    @Autowired
    private IMoBeamInfoService moBeamInfoService;

    @Autowired
    private IMoTagInfoService moTagInfoService;

    @Autowired
    private IMoProduceOrderService moProduceOrderService;

    @Autowired
    private IMoProcessFlowService moProcessFlowService;

    @Autowired
    private IMoWorkstageService moWorkstageService;


    @Test
    void test_hasCurrentStepBeenCompleted_1() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String stepTypeNo = String.valueOf(System.currentTimeMillis());
        ExecutableDto executableDto = ExecutableDto.builder().stepTypeNo(stepTypeNo).productSn(productSn).build();
        Boolean res = processStepProductionRecordsService.hasCurrentStepBeenCompleted(executableDto);
        assertEquals(false, res);
    }

    @Test
    void test_hasCurrentStepBeenCompleted_2() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String stepTypeNo = String.valueOf(System.currentTimeMillis());
        moProcessStepProductionResultService.save(MoProcessStepProductionResult.builder().productSn(productSn).stepTypeNo(stepTypeNo).errorCode(1).build());
        ExecutableDto executableDto = ExecutableDto.builder().stepTypeNo(stepTypeNo).productSn(productSn).build();
        Boolean res = processStepProductionRecordsService.hasCurrentStepBeenCompleted(executableDto);
        assertEquals(false, res);
    }

    @Test
    void test_hasCurrentStepBeenCompleted_3() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String stepTypeNo = String.valueOf(System.currentTimeMillis());
        moProcessStepProductionResultService.save(MoProcessStepProductionResult.builder().productSn(productSn).stepTypeNo(stepTypeNo).errorCode(0).build());
        ExecutableDto executableDto = ExecutableDto.builder().stepTypeNo(stepTypeNo).productSn(productSn).build();
        Boolean res = processStepProductionRecordsService.hasCurrentStepBeenCompleted(executableDto);
        assertEquals(true, res);
    }

    @Test
    void test_isPreviousStepPassed_1() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String stepTypeNo = String.valueOf(System.currentTimeMillis());
        ExecutableDto executableDto = ExecutableDto.builder().stepTypeNo(stepTypeNo).productSn(productSn).build();
        Boolean res = processStepProductionRecordsService.isPreviousStepPassed(executableDto);
        assertEquals(true, res);
    }

    @Test
    void test_isPreviousStepPassed_2() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String stepTypeNo = String.valueOf(System.currentTimeMillis());
        String lastStepTypeNo = String.valueOf(System.currentTimeMillis()) + "111";
        String lastStepTypeNo2 = String.valueOf(System.currentTimeMillis()) + "111";
        moProcessStepProductionResultService.save(MoProcessStepProductionResult.builder().productSn(productSn).stepTypeNo(lastStepTypeNo2).errorCode(0).build());
        moProcessStepProductionResultService.save(MoProcessStepProductionResult.builder().productSn(productSn).stepTypeNo(lastStepTypeNo).errorCode(1).build());
        ExecutableDto executableDto = ExecutableDto.builder().stepTypeNo(stepTypeNo).productSn(productSn).build();
        Boolean res = processStepProductionRecordsService.isPreviousStepPassed(executableDto);
        assertEquals(false, res);
    }

    @Test
    void test_isPreviousStepPassed_3() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String stepTypeNo = String.valueOf(System.currentTimeMillis());
        String lastStepTypeNo = String.valueOf(System.currentTimeMillis()) + "111";
        String lastStepTypeNo2 = String.valueOf(System.currentTimeMillis()) + "111";
        moProcessStepProductionResultService.save(MoProcessStepProductionResult.builder().productSn(productSn).stepTypeNo(lastStepTypeNo2).errorCode(1).build());
        moProcessStepProductionResultService.save(MoProcessStepProductionResult.builder().productSn(productSn).stepTypeNo(lastStepTypeNo).errorCode(0).build());
        ExecutableDto executableDto = ExecutableDto.builder().stepTypeNo(stepTypeNo).productSn(productSn).build();
        Boolean res = processStepProductionRecordsService.isPreviousStepPassed(executableDto);
        assertEquals(true, res);
    }

    @Test
    void test_fetchWorkOrderNoByProductSn_1() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String res = processStepProductionRecordsService.fetchWorkOrderNoByProductSn(productSn);
        assertNull(res);
    }

    @Test
    void test_fetchWorkOrderNoByProductSn_2() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String workOrderNo = String.valueOf(System.currentTimeMillis());
        moBeamInfoService.save(MoBeamInfo.builder().beamSn(productSn).workOrderCode(workOrderNo).build());
        String res = processStepProductionRecordsService.fetchWorkOrderNoByProductSn(productSn);
        assertEquals(workOrderNo, res);
    }

    @Test
    void test_fetchWorkOrderNoByProductSn_3() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String workOrderNo = String.valueOf(System.currentTimeMillis());
        moTagInfoService.save(MoTagInfo.builder().tagSn(productSn).workOrderCode(workOrderNo).build());
        String res = processStepProductionRecordsService.fetchWorkOrderNoByProductSn(productSn);
        assertEquals(workOrderNo, res);
    }

    @Test
    void test_fetchProcessFlowNoByWorkOrderNo_1() throws Exception {
        String workOrderNo = null;
        List<MoProcessFlow> res = processStepProductionRecordsService.fetchProcessFlowNoByWorkOrderNo(workOrderNo);
        assertEquals(List.of(), res);
    }

    @Test
    void test_fetchProcessFlowNoByWorkOrderNo_2() throws Exception {
        String workOrderNo = String.valueOf(System.currentTimeMillis());
        List<MoProcessFlow> res = processStepProductionRecordsService.fetchProcessFlowNoByWorkOrderNo(workOrderNo);
        assertEquals(List.of(), res);
    }

    @Test
    void test_fetchProcessFlowNoByWorkOrderNo_3() throws Exception {
        String workOrderNo = String.valueOf(System.currentTimeMillis());
        String flowCode = String.valueOf(System.currentTimeMillis());
        moProduceOrderService.save(MoProduceOrder.builder().workOrderCode(workOrderNo).flowCode(flowCode).build());
        String step = String.valueOf(System.currentTimeMillis()) + '1';
        String step2 = String.valueOf(System.currentTimeMillis()) + '2';
        String step3 = String.valueOf(System.currentTimeMillis()) + '3';
        String step4 = String.valueOf(System.currentTimeMillis()) + '4';
        moWorkstageService.save(MoWorkstage.builder().stageCode(step).stageName("1").build());
        moWorkstageService.save(MoWorkstage.builder().stageCode(step2).stageName("2").build());
        moWorkstageService.save(MoWorkstage.builder().stageCode(step3).stageName("3").build());
        moWorkstageService.save(MoWorkstage.builder().stageCode(step4).stageName("4").build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step).build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step2).build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step3).build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step4).build());

        ArrayList<String> steps = new ArrayList<>();
        steps.add(step);
        steps.add(step2);
        steps.add(step3);
        steps.add(step4);

        List<MoProcessFlow> res = processStepProductionRecordsService.fetchProcessFlowNoByWorkOrderNo(workOrderNo);
        res.forEach(item -> {
            assertEquals(true, steps.contains(item.getStageCode()));
        });
    }

    @Test
    void test_fetchSysStepTypeNos_1() throws Exception {
        String productSn = String.valueOf(System.currentTimeMillis());
        String workOrderNo = String.valueOf(System.currentTimeMillis());
        moBeamInfoService.save(MoBeamInfo.builder().beamSn(productSn).workOrderCode(workOrderNo).build());
        String flowCode = String.valueOf(System.currentTimeMillis());
        moProduceOrderService.save(MoProduceOrder.builder().workOrderCode(workOrderNo).flowCode(flowCode).build());
        String step = String.valueOf(System.currentTimeMillis()) + '1';
        String step2 = String.valueOf(System.currentTimeMillis()) + '2';
        String step3 = String.valueOf(System.currentTimeMillis()) + '3';
        String step4 = String.valueOf(System.currentTimeMillis()) + '4';
        moWorkstageService.save(MoWorkstage.builder().stageCode(step).stageName("1").stepTypeNo("1").build());
        moWorkstageService.save(MoWorkstage.builder().stageCode(step2).stageName("2").build());
        moWorkstageService.save(MoWorkstage.builder().stageCode(step3).stageName("3").stepTypeNo("3").build());
        moWorkstageService.save(MoWorkstage.builder().stageCode(step4).stageName("4").build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step).build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step2).build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step3).build());
        moProcessFlowService.save(MoProcessFlow.builder().processCode(flowCode).stageCode(step4).build());

        ArrayList<String> steps = new ArrayList<>();
        steps.add("1");
        steps.add("3");

        List<String> res = processStepProductionRecordsService.fetchRequiredSysStepTypeNos(productSn);
        res.forEach(item -> {
            assertTrue(steps.contains(item));
        });
    }

    @Test
    void test__1() throws Exception {
        Boolean res = processStepProductionRecordsService.hasStepBeenSkipped("9", List.of("1", "5", "7", "9"), List.of("0", "1", "2", "5"));
        assertFalse(res);
    }

    @Test
    void test__2() throws Exception {
        Boolean res = processStepProductionRecordsService.hasStepBeenSkipped("7", List.of("1", "5", "7", "9"), List.of("0", "1", "2", "5"));
        assertTrue(res);
    }

    @Test
    void test__3() throws Exception {
        Boolean res = processStepProductionRecordsService.hasStepBeenSkipped("10", List.of("1", "5", "7", "9"), List.of("0", "1", "2", "5"));
        assertTrue(res);
    }

    @Test
    void test__4() throws Exception {
        Boolean res = processStepProductionRecordsService.hasStepBeenSkipped("10", List.of("1", "5"), List.of("0", "1", "2", "5"));
        assertTrue(res);
    }
//

//    @Test
//    void production_get_sn_1() throws Exception {
//
//        String workOrderNo = "MO000471";
//        Result<GetProductSnResponseDto> res = productionController.fetchProductSn(GetProductSnDto.builder().workOrderNo(workOrderNo).build());
//        String sn = res.getData().getSn();
//        LambdaQueryWrapper<MoBeamInfo> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoBeamInfo::getWorkOrderCode, workOrderNo).eq(MoBeamInfo::getBeamSn, sn);
//        List<MoBeamInfo> moBeamInfos = moBeamInfoService.list(wrapper);
//        MoBeamInfo moBeamInfo = moBeamInfos.get(0);
//        assertEquals(moBeamInfo.getIsUsed(), 1);
//
//    }
//
//    @Test
//    void production_get_sn_2() throws Exception {
//
//        String workOrderNo = "xxx";
//        Result<GetProductSnResponseDto> res = productionController.fetchProductSn(GetProductSnDto.builder().workOrderNo(workOrderNo).build());
//
//        assertEquals(res.getCode(), -1);
//
//    }
//
//    @Test
//    void production_dirt_1() throws Exception {
//        String beamSn = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductSn(beamSn);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("000");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("0").key("k001").val("1").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("0").key("k002").val("2").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("0").key("k003").val("3").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("0").key("k004").val("4").build());
//
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("1").key("k001").val("5").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("1").key("k002").val("6").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("1").key("k003").val("7").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("1").key("k004").val("8").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoDirtyCheck> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoDirtyCheck::getBeamSn, beamSn).
//                eq(MoDirtyCheck::getSide, "left").
//                orderByDesc(MoDirtyCheck::getId).
//                last("LIMIT 1");
//        MoDirtyCheck moDirtyCheck = moDirtyCheckService.getOne(wrapper);
//        assertEquals(1, moDirtyCheck.getStainSizeUsl());
//        assertEquals(2, moDirtyCheck.getStainCountUsl());
//        assertEquals("3", moDirtyCheck.getStains());
//        assertEquals("4", moDirtyCheck.getImagePath());
//
//        wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoDirtyCheck::getBeamSn, beamSn).
//                eq(MoDirtyCheck::getSide, "right").
//                orderByDesc(MoDirtyCheck::getId).
//                last("LIMIT 1");
//        moDirtyCheck = moDirtyCheckService.getOne(wrapper);
//        assertEquals(5, moDirtyCheck.getStainSizeUsl());
//        assertEquals(6, moDirtyCheck.getStainCountUsl());
//        assertEquals("7", moDirtyCheck.getStains());
//        assertEquals("8", moDirtyCheck.getImagePath());
//
//    }
//
//    @Test
//    void production_dirt_2() throws Exception {
//        String beamSn = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductSn(beamSn);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("000");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//        ArrayList<MaterialDto> materialDtos = new ArrayList<>();
//
//        MaterialDto materialDto = new MaterialDto();
//        materialDto.setCategoryNo("000");
//        materialDto.setBatchNo("000");
//        materialDto.setSerialNo("SN000");
//        materialDtos.add(materialDto);
//        materialDto = new MaterialDto();
//        materialDto.setCategoryNo("001");
//        materialDto.setBatchNo("001");
//        materialDtos.add(materialDto);
//        materialDto = new MaterialDto();
//        materialDto.setCategoryNo("002");
//        materialDto.setBatchNo("002");
//        materialDtos.add(materialDto);
//        productionRecordDto.setMaterials(materialDtos);
//        productionRecordDto.setAttrKeyVals(List.of());
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoAutoAdjustInfo> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoAutoAdjustInfo::getBeamSn, beamSn).
//                orderByDesc(MoAutoAdjustInfo::getId).
//                last("LIMIT 1");
//        MoAutoAdjustInfo moAutoAdjustInfo = moAutoAdjustInfoService.getOne(wrapper);
//        assertEquals(0, moAutoAdjustInfo.getErrorCode());
//        assertEquals("SUCCESS", moAutoAdjustInfo.getNgReason());
//        assertEquals("SN000", moAutoAdjustInfo.getLensSn());
//        assertEquals("000", moAutoAdjustInfo.getLensBatch());
//        assertEquals("001", moAutoAdjustInfo.getCmosBatch());
//        assertEquals("002", moAutoAdjustInfo.getBeamBatch());
//        assertEquals(8, moAutoAdjustInfo.getStationNum());
//
//    }
//
//    @Test
//    void production_uv_1() throws Exception {
//        String beamSn = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductSn(beamSn);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("001");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("0").key("k001").val("1").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("0").key("k002").val("2").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("0").key("k003").val("3").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("0").key("k004").val("4").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("0").key("k005").val("5").build());
//
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("1").key("k001").val("11").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("1").key("k002").val("12").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("1").key("k003").val("13").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("1").key("k004").val("14").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("1").key("k004").val("15").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<UvDispensing> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(UvDispensing::getBeamSn, beamSn).
//                eq(UvDispensing::getSide, "left").
//                orderByDesc(UvDispensing::getId).
//                last("LIMIT 1");
//        UvDispensing uvDispensing = uvDispensingService.getOne(wrapper);
//        assertEquals(1, uvDispensing.getGlueWidth());
//        assertEquals(2, uvDispensing.getGlueWidthLsl());
//        assertEquals(3, uvDispensing.getCircleCenterOffset());
//        assertEquals(4, uvDispensing.getCircleCenterOffsetUsl());
//        assertEquals("5", uvDispensing.getImagePath());
//
//        wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(UvDispensing::getBeamSn, beamSn).
//                eq(UvDispensing::getSide, "right").
//                orderByDesc(UvDispensing::getId).
//                last("LIMIT 1");
//        uvDispensing = uvDispensingService.getOne(wrapper);
//        assertEquals(11, uvDispensing.getGlueWidth());
//        assertEquals(12, uvDispensing.getGlueWidthLsl());
//        assertEquals(13, uvDispensing.getCircleCenterOffset());
//        assertEquals(14, uvDispensing.getCircleCenterOffsetUsl());
//        assertEquals("15", uvDispensing.getImagePath());
//    }
//
//    @Test
//    void production_lens_assembly_1() throws Exception {
//        String batchNo = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductBatchNo(batchNo);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("005");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//        productionRecordDto.setOperator("test");
//        productionRecordDto.setDeviceNo("1");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("000").key("k000").val("0").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").key("k001").val("1").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").key("k002").val("2").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").key("k003").val("3").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").key("k004").val("4").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").key("k005").val("5").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        ArrayList<MaterialDto> materialDtos = new ArrayList<>();
//        materialDtos.add(new MaterialDto().builder().categoryNo("004").batchNo("004").build());
//        materialDtos.add(new MaterialDto().builder().categoryNo("005").batchNo("005").build());
//        materialDtos.add(new MaterialDto().builder().categoryNo("006").batchNo("006").build());
//        materialDtos.add(new MaterialDto().builder().categoryNo("007").batchNo("007").build());
//
//        productionRecordDto.setMaterials(materialDtos);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoLensAssembly> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoLensAssembly::getBatchNo, batchNo).
//                orderByDesc(MoLensAssembly::getId).
//                last("LIMIT 1");
//        MoLensAssembly moLensAssembly = moLensAssemblyService.getOne(wrapper);
//        assertEquals(0, moLensAssembly.getAssemblyPressure());
//        assertEquals(1, moLensAssembly.getAssemblyPressureUsl());
//        assertEquals(2, moLensAssembly.getAssemblyPressureLsl());
//        assertEquals(3, moLensAssembly.getAssemblyHeight());
//        assertEquals(4, moLensAssembly.getAssemblyHeightUsl());
//        assertEquals(5, moLensAssembly.getAssemblyHeightLsl());
//
////        assertEquals("007", moLensAssembly.getLensBatchNo());
////        assertEquals("004", moLensAssembly.getLensBarrelBatchNo());
////        assertEquals("005", moLensAssembly.getMylarBatchNo());
////        assertEquals("006", moLensAssembly.getIrBatchNo());
//    }
//
//    @Test
//    void production_lens_cap_fastening_1() throws Exception {
//        String batchNo = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductBatchNo(batchNo);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("006");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("000").key("k000").val("0").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").key("k001").val("1").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").key("k002").val("2").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").key("k003").val("3").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").key("k004").val("4").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").key("k005").val("5").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("006").key("k003").val("6").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("007").key("k004").val("7").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("008").key("k005").val("8").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoLensCapFastening> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoLensCapFastening::getBatchNo, batchNo).
//                orderByDesc(MoLensCapFastening::getId).
//                last("LIMIT 1");
//        MoLensCapFastening moLensCapFastening = moLensCapFasteningService.getOne(wrapper);
//        assertEquals(0, moLensCapFastening.getScrewHeight());
//        assertEquals(1, moLensCapFastening.getScrewHeightUsl());
//        assertEquals(2, moLensCapFastening.getScrewHeightLsl());
//        assertEquals(3, moLensCapFastening.getScrewTorque());
//        assertEquals(4, moLensCapFastening.getScrewTorqueUsl());
//        assertEquals(5, moLensCapFastening.getScrewTorqueLsl());
//        assertEquals(6, moLensCapFastening.getScrewPressure());
//        assertEquals(7, moLensCapFastening.getScrewPressureUsl());
//        assertEquals(8, moLensCapFastening.getScrewPressureLsl());
//
//    }
//
//    @Test
//    void production_lens_dispensing_1() throws Exception {
//        String batchNo = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductBatchNo(batchNo);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("008");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("000").key("k000").val("0").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").key("k001").val("1").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").key("k002").val("2").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").key("k003").val("3").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoLensDispensing> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoLensDispensing::getBatchNo, batchNo).
//                orderByDesc(MoLensDispensing::getId).
//                last("LIMIT 1");
//        MoLensDispensing moLensDispensing = moLensDispensingService.getOne(wrapper);
//        assertEquals(0, moLensDispensing.getFrontCoverDispenseTime());
//        assertEquals(1, moLensDispensing.getFrontCoverCureTime());
//        assertEquals(2, moLensDispensing.getRearCoverDispenseTime());
//        assertEquals(3, moLensDispensing.getRearCoverCureTime());
//
//    }
//
//    @Test
//    void production_ir_pasting_1() throws Exception {
//        String batchNo = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductBatchNo(batchNo);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("009");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("000").key("k000").val("0").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").key("k001").val("1").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").key("k002").val("2").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoIrPasting> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoIrPasting::getBatchNo, batchNo).
//                orderByDesc(MoIrPasting::getId).
//                last("LIMIT 1");
//        MoIrPasting moLensDispensing = moIrPastingService.getOne(wrapper);
//        assertEquals("0", moLensDispensing.getDispenseMethod());
//        assertEquals(1, moLensDispensing.getDispenseTime());
//        assertEquals(2, moLensDispensing.getCureTime());
//
//    }
//
//    @Test
//    void production_lens_baking_1() throws Exception {
//        String batchNo = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductBatchNo(batchNo);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("010");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("000").key("k000").val("0").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").key("k001").val("1").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").key("k002").val("2").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoLensBaking> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoLensBaking::getBatchNo, batchNo).
//                orderByDesc(MoLensBaking::getId).
//                last("LIMIT 1");
//        MoLensBaking moLensBaking = moLensBakingService.getOne(wrapper);
//        assertEquals(0, moLensBaking.getCureTime());
//        assertEquals(1, moLensBaking.getCurePower());
//        assertEquals(2, moLensBaking.getCureTemperature());
//
//    }
//
//    @Test
//    void production_aa_1() throws Exception {
//        String beamSn = String.valueOf(System.currentTimeMillis());
//        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
//        productionRecordDto.setProductSn(beamSn);
//        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
//        productionRecordDto.setStepTypeNo("002");
//        productionRecordDto.setError("SUCCESS");
//        productionRecordDto.setErrorNo("0");
//        productionRecordDto.setDeviceNo("0");
//
//        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("0").stage("0").key("k002").val("002").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("0").stage("0").key("k003").val("003").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("0").stage("0").key("k004").val("004").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("0").stage("0").key("k005").val("005").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("006").position("0").stage("0").key("k002").val("006").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("007").position("0").stage("0").key("k003").val("007").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("008").position("0").stage("0").key("k004").val("008").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("009").position("0").stage("0").key("k005").val("009").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("010").position("0").stage("0").key("k002").val("010").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("011").position("0").stage("0").key("k002").val("011").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("012").position("0").stage("0").key("k002").val("012").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("013").position("0").stage("0").key("k003").val("013").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("014").position("0").stage("0").key("k004").val("014").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("015").position("0").stage("0").key("k005").val("015").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("016").position("0").stage("0").key("k002").val("016").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("017").position("0").stage("0").key("k002").val("017").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("018").position("0").stage("0").key("k002").val("018").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("019").position("0").stage("0").key("k002").val("019").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("020").position("0").stage("0").key("k003").val("020").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("021").position("0").stage("0").key("k004").val("021").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("022").position("0").stage("0").key("k005").val("022").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("023").position("0").stage("0").key("k002").val("023").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("024").position("0").stage("0").key("k003").val("024").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("025").position("0").stage("0").key("k004").val("025").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("026").position("0").stage("0").key("k005").val("026").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("027").position("0").stage("0").key("k002").val("027").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("028").position("0").stage("0").key("k002").val("028").build());
//
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("1").stage("0").key("k002").val("102").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("1").stage("0").key("k003").val("103").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("1").stage("0").key("k004").val("104").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("1").stage("0").key("k005").val("105").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("006").position("1").stage("0").key("k002").val("106").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("007").position("1").stage("0").key("k003").val("107").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("008").position("1").stage("0").key("k004").val("108").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("009").position("1").stage("0").key("k005").val("109").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("010").position("1").stage("0").key("k002").val("110").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("011").position("1").stage("0").key("k002").val("111").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("012").position("1").stage("0").key("k002").val("112").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("013").position("1").stage("0").key("k003").val("113").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("014").position("1").stage("0").key("k004").val("114").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("015").position("1").stage("0").key("k005").val("115").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("016").position("1").stage("0").key("k002").val("116").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("017").position("1").stage("0").key("k002").val("117").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("018").position("1").stage("0").key("k002").val("118").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("019").position("1").stage("0").key("k002").val("119").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("020").position("1").stage("0").key("k003").val("120").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("021").position("1").stage("0").key("k004").val("121").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("022").position("1").stage("0").key("k005").val("122").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("023").position("1").stage("0").key("k002").val("123").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("024").position("1").stage("0").key("k003").val("124").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("025").position("1").stage("0").key("k004").val("125").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("026").position("1").stage("0").key("k005").val("126").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("027").position("1").stage("0").key("k002").val("127").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("028").position("1").stage("0").key("k002").val("128").build());
//
//        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);
//
//        productionController.add(productionRecordDto);
//
//        LambdaQueryWrapper<MoAutoAdjustSt08> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoAutoAdjustSt08::getBeamSn, beamSn).
//                eq(MoAutoAdjustSt08::getSide, "left").
//                orderByDesc(MoAutoAdjustSt08::getId).
//                last("LIMIT 1");
//        MoAutoAdjustSt08 moAutoAdjustSt08 = moAutoAdjustSt08Service.getOne(wrapper);
//        assertEquals(2, moAutoAdjustSt08.getOcXOffset());
//        assertEquals(3, moAutoAdjustSt08.getOcXOffsetUsl());
//        assertEquals(4, moAutoAdjustSt08.getOcYOffset());
//        assertEquals(5, moAutoAdjustSt08.getOcYOffsetUsl());
////        assertEquals(6, moAutoAdjustSt08.getCodXOffset());
////        assertEquals(7, moAutoAdjustSt08.getCodXOffsetUsl());
////        assertEquals(8, moAutoAdjustSt08.getCodYOffset());
////        assertEquals(9, moAutoAdjustSt08.getCodYOffsetUsl());
////        assertEquals(10, moAutoAdjustSt08.getMtfCenterValue());
////        assertEquals(11, moAutoAdjustSt08.getMtfCenterLsl());
////        assertEquals(12, moAutoAdjustSt08.getMtfCenterUsl());
////        assertEquals(13, moAutoAdjustSt08.getMtfLeftupValue());
////        assertEquals(14, moAutoAdjustSt08.getMtfLeftupLsl());
////        assertEquals(15, moAutoAdjustSt08.getMtfLeftupUsl());
////        assertEquals(16, moAutoAdjustSt08.getMtfRightupValue());
////        assertEquals(17, moAutoAdjustSt08.getMtfRightupLsl());
////        assertEquals(18, moAutoAdjustSt08.getMtfRightupUsl());
////        assertEquals(19, moAutoAdjustSt08.getMtfLeftdownValue());
////        assertEquals(20, moAutoAdjustSt08.getMtfLeftdownLsl());
////        assertEquals(21, moAutoAdjustSt08.getMtfLeftdownUsl());
////
////        assertEquals(22, moAutoAdjustSt08.getMtfRightdownValue());
////        assertEquals(23, moAutoAdjustSt08.getMtfRightdownLsl());
////        assertEquals(24, moAutoAdjustSt08.getMtfRightdownUsl());
////
////        assertEquals(25, moAutoAdjustSt08.getMtfRangeOffset());
////        assertEquals(26, moAutoAdjustSt08.getMtfRangeOffsetLsl());
//        assertEquals(27, moAutoAdjustSt08.getMtfRangeOffsetUsl());
//        assertEquals("028", moAutoAdjustSt08.getImagePath());
//
//        wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoAutoAdjustSt08::getBeamSn, beamSn).
//                eq(MoAutoAdjustSt08::getSide, "right").
//                orderByDesc(MoAutoAdjustSt08::getId).
//                last("LIMIT 1");
//        moAutoAdjustSt08 = moAutoAdjustSt08Service.getOne(wrapper);
//        assertEquals(102, moAutoAdjustSt08.getOcXOffset());
//        assertEquals(103, moAutoAdjustSt08.getOcXOffsetUsl());
//        assertEquals(104, moAutoAdjustSt08.getOcYOffset());
//        assertEquals(105, moAutoAdjustSt08.getOcYOffsetUsl());
//        assertEquals(106, moAutoAdjustSt08.getCodXOffset());
//        assertEquals(107, moAutoAdjustSt08.getCodXOffsetUsl());
//        assertEquals(108, moAutoAdjustSt08.getCodYOffset());
//        assertEquals(109, moAutoAdjustSt08.getCodYOffsetUsl());
//        assertEquals(110, moAutoAdjustSt08.getMtfCenterValue());
//        assertEquals(111, moAutoAdjustSt08.getMtfCenterLsl());
//        assertEquals(112, moAutoAdjustSt08.getMtfCenterUsl());
//        assertEquals(113, moAutoAdjustSt08.getMtfLeftupValue());
//        assertEquals(114, moAutoAdjustSt08.getMtfLeftupLsl());
////        assertEquals(115, moAutoAdjustSt08.getMtfLeftupUsl());
////        assertEquals(116, moAutoAdjustSt08.getMtfRightupValue());
////        assertEquals(117, moAutoAdjustSt08.getMtfRightupLsl());
////        assertEquals(118, moAutoAdjustSt08.getMtfRightupUsl());
////        assertEquals(119, moAutoAdjustSt08.getMtfLeftdownValue());
////        assertEquals(120, moAutoAdjustSt08.getMtfLeftdownLsl());
////        assertEquals(121, moAutoAdjustSt08.getMtfLeftdownUsl());
////
////        assertEquals(122, moAutoAdjustSt08.getMtfRightdownValue());
////        assertEquals(123, moAutoAdjustSt08.getMtfRightdownLsl());
////        assertEquals(124, moAutoAdjustSt08.getMtfRightdownUsl());
////
////        assertEquals(125, moAutoAdjustSt08.getMtfRangeOffset());
////        assertEquals(126, moAutoAdjustSt08.getMtfRangeOffsetLsl());
////        assertEquals(127, moAutoAdjustSt08.getMtfRangeOffsetUsl());
//        assertEquals("128", moAutoAdjustSt08.getImagePath());
//    }
//
//    @Test
//    void device_status() throws Exception {
//        String beamSn = String.valueOf(System.currentTimeMillis());
//        DeviceStatusDto deviceStatusDto = new DeviceStatusDto();
//        deviceStatusDto.setDeviceNo("00");
//        deviceStatusDto.setProductMaterialNo("123456");
//        ArrayList<AttrKeyValDto> attrKeyValDtos = new ArrayList<>();
//        attrKeyValDtos.add(new AttrKeyValDto().builder().no("000").key("k000").val("000").build());
//        attrKeyValDtos.add(new AttrKeyValDto().builder().no("001").key("k001").val("001").build());
//        attrKeyValDtos.add(new AttrKeyValDto().builder().no("002").key("k002").val("002").build());
//        deviceStatusDto.setAttrKeyVals(attrKeyValDtos);
//        productionController.device(deviceStatusDto);
//        LambdaQueryWrapper<MoDeviceStatus> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoDeviceStatus::getProductMaterialNo, "123456").
//                eq(MoDeviceStatus::getDeviceNo, "00").
//                orderByDesc(MoDeviceStatus::getId).
//                last("LIMIT 1");
//        MoDeviceStatus moDeviceStatus = moDeviceStatusService.getOne(wrapper);
//        assertEquals("123456", moDeviceStatus.getProductMaterialNo());
//        assertEquals("00", moDeviceStatus.getDeviceNo());
////        assertEquals("[{\"no\": \"000\", \"key\": \"k000\", \"val\": \"000\"}, {\"no\": \"001\", \"key\": \"k001\", \"val\": \"001\"}, {\"no\": \"002\", \"key\": \"k002\", \"val\": \"002\"}]", moDeviceStatus.getName());
//
//    }
//
}