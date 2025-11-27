package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.*;
import com.metoak.mes.entity.*;
import com.metoak.mes.enums.ResultCodeEnum;
import com.metoak.mes.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductionController productionController;

    @Autowired
    private IMoDirtyCheckService moDirtyCheckService;

    @Autowired
    private IUvDispensingService uvDispensingService;

    @Autowired
    private IMoAutoAdjustSt08Service moAutoAdjustSt08Service;

    @Autowired
    private IMoBeamInfoService moBeamInfoService;

    @Autowired
    private IMoCalibrationService moCalibrationService;

    @Autowired
    private IMoAutoAdjustInfoService moAutoAdjustInfoService;
//    @Test
//    void test_product_sn_1() throws Exception {
//        String json = "{}";
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVU0VSX0lORk8iLCJleHAiOjE3NTI4NDA0NzUsInVzZXJuYW1lIjoiMTc1MjQ4MDQ3NDk2OCJ9.2532R9v4zsTSWRYpE44sK9Avw2K5hSXE9vXL8OusY9w"; // 替换为实际的 token
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/mes/v1/production/workOrder/sn/fetch")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json)
//                        .header("Authorization", "Bearer " + token)) // 添加 token
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ResultCodeEnum.FORM_VERIFICATION_FAILED.getCode()));
//    }

    @Test
    void test_product_sn_2() throws Exception {
        GetProductSnDto getProductSnDto = new GetProductSnDto();
        getProductSnDto.setWorkOrderNo(String.valueOf(System.currentTimeMillis()));

        Result<GetProductSnResponseDto> result = productionController.fetchProductSn(getProductSnDto);
        assertEquals(ResultCodeEnum.PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO.getCode(), result.getCode());
    }

    @Test
    void test_product_sn_3() throws Exception {
        String beamSn = String.valueOf(System.currentTimeMillis());
        String workOrderNo = String.valueOf(System.currentTimeMillis());
        MoBeamInfo moBeamInfo = new MoBeamInfo();
        moBeamInfo.setBeamSn(beamSn);
        moBeamInfo.setWorkOrderCode(workOrderNo);
        moBeamInfoService.save(moBeamInfo);

        GetProductSnDto getProductSnDto = new GetProductSnDto();
        getProductSnDto.setWorkOrderNo(workOrderNo);

        Result<GetProductSnResponseDto> result = productionController.fetchProductSn(getProductSnDto);


        assertEquals(ResultCodeEnum.SUCCESS.getCode(), result.getCode());
        assertEquals(beamSn, result.getData().getSn());
        LambdaQueryWrapper<MoBeamInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoBeamInfo::getBeamSn, beamSn);
        assertEquals(1, moBeamInfoService.list(wrapper).get(0).getIsUsed());

    }

    @Test
    void test_hasCurrentStepBeenCompleted_1() throws Exception {
        GetProductSnDto getProductSnDto = new GetProductSnDto();
        getProductSnDto.setWorkOrderNo(String.valueOf(System.currentTimeMillis()));

        Result<GetProductSnResponseDto> result = productionController.fetchProductSn(getProductSnDto);
        assertEquals(ResultCodeEnum.PRO_PRODUCT_SN_NOT_FOUND_BY_WORKORDER_NO.getCode(), result.getCode());
    }

    @Test
    void production_lens_mtf_1() throws Exception {

        // build

        // ex
//        productionController.add()
        // verify

    }

    @Test
    void production_material_binding_1() throws Exception {
        String beamSn = String.valueOf(System.currentTimeMillis());
        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
        productionRecordDto.setProductSn(beamSn);
        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setStepTypeNo("004");
        productionRecordDto.setError("SUCCESS");
        productionRecordDto.setErrorNo("0");
        productionRecordDto.setDeviceNo("0");
        productionRecordDto.setEngineeringParamsId(1L);
        productionRecordDto.setFlowParamsId(2L);
        ArrayList<MaterialDto> materialDtos = new ArrayList<>();

        MaterialDto materialDto = new MaterialDto();
        materialDto.setCategoryNo("000");
        materialDto.setBatchNo("000");
        materialDto.setSerialNo("SN000");
        materialDto.setPosition(0);
        materialDtos.add(materialDto);
        materialDto = new MaterialDto();
        materialDto.setCategoryNo("000");
        materialDto.setBatchNo("000");
        materialDto.setSerialNo("SN001");
        materialDto.setPosition(1);
        materialDtos.add(materialDto);
        materialDto = new MaterialDto();
        materialDto.setCategoryNo("001");
        materialDto.setBatchNo("001");
        materialDtos.add(materialDto);
        materialDto = new MaterialDto();
        materialDto.setCategoryNo("002");
        materialDto.setBatchNo("002");
        materialDtos.add(materialDto);
        productionRecordDto.setMaterials(materialDtos);

        productionController.add(productionRecordDto);

//        LambdaQueryWrapper<MoMaterialBinding> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoMaterialBinding::getBeamSn, beamSn).
//                orderByDesc(MoMaterialBinding::getId).
//                last("LIMIT 1");
//        MoAutoAdjustInfo moAutoAdjustInfo = moAutoAdjustInfoService.getOne(wrapper);
//        assertEquals(0, moAutoAdjustInfo.getErrorCode());
//        assertEquals("SUCCESS", moAutoAdjustInfo.getNgReason());
//        assertEquals("SN000", moAutoAdjustInfo.getLensSn());
//        assertEquals("000", moAutoAdjustInfo.getLensBatch());
//        assertEquals("001", moAutoAdjustInfo.getCmosBatch());
//        assertEquals("002", moAutoAdjustInfo.getBeamBatch());
//        assertEquals(8, moAutoAdjustInfo.getStationNum());

    }

    @Test
    void production_plasma_1() throws Exception {
        String beamSn = String.valueOf(System.currentTimeMillis());
        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
        productionRecordDto.setProductSn(beamSn);
        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setStepTypeNo("003");
        productionRecordDto.setError("SUCCESS");
        productionRecordDto.setErrorNo("0");
        productionRecordDto.setDeviceNo("0");


        productionController.add(productionRecordDto);

//        LambdaQueryWrapper<MoMaterialBinding> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(MoMaterialBinding::getBeamSn, beamSn).
//                orderByDesc(MoMaterialBinding::getId).
//                last("LIMIT 1");
//        MoAutoAdjustInfo moAutoAdjustInfo = moAutoAdjustInfoService.getOne(wrapper);
//        assertEquals(0, moAutoAdjustInfo.getErrorCode());
//        assertEquals("SUCCESS", moAutoAdjustInfo.getNgReason());
//        assertEquals("SN000", moAutoAdjustInfo.getLensSn());
//        assertEquals("000", moAutoAdjustInfo.getLensBatch());
//        assertEquals("001", moAutoAdjustInfo.getCmosBatch());
//        assertEquals("002", moAutoAdjustInfo.getBeamBatch());
//        assertEquals(8, moAutoAdjustInfo.getStationNum());

    }
    @Test
    void production_dirt_1() throws Exception {
        String beamSn = String.valueOf(System.currentTimeMillis());
        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
        productionRecordDto.setProductSn(beamSn);
        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setStepTypeNo("000_02");
        productionRecordDto.setError("SUCCESS");
        productionRecordDto.setErrorNo("0");
        productionRecordDto.setDeviceNo("0");

        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("0").key("k001").val("1").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("0").key("k002").val("2").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("0").key("k003").val("3").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("0").key("k004").val("4").build());

        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("1").key("k001").val("5").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("1").key("k002").val("6").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("1").key("k003").val("7").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("1").key("k004").val("8").build());

        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);

        productionController.add(productionRecordDto);

        LambdaQueryWrapper<MoDirtyCheck> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoDirtyCheck::getBeamSn, beamSn).
                eq(MoDirtyCheck::getPosition, 0).
                orderByDesc(MoDirtyCheck::getId).
                last("LIMIT 1");
        MoDirtyCheck moDirtyCheck = moDirtyCheckService.getOne(wrapper);
        assertEquals(1, moDirtyCheck.getStainSizeUsl());
        assertEquals(2, moDirtyCheck.getStainCountUsl());
        assertEquals("3", moDirtyCheck.getStains());
        assertEquals("4", moDirtyCheck.getImagePath());

        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoDirtyCheck::getBeamSn, beamSn).
                eq(MoDirtyCheck::getPosition, 1).
                orderByDesc(MoDirtyCheck::getId).
                last("LIMIT 1");
        moDirtyCheck = moDirtyCheckService.getOne(wrapper);
        assertEquals(5, moDirtyCheck.getStainSizeUsl());
        assertEquals(6, moDirtyCheck.getStainCountUsl());
        assertEquals("7", moDirtyCheck.getStains());
        assertEquals("8", moDirtyCheck.getImagePath());

    }


    @Test
    void production_uv_1() throws Exception {
        String beamSn = String.valueOf(System.currentTimeMillis());
        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
        productionRecordDto.setProductSn(beamSn);
        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setStepTypeNo("001");
        productionRecordDto.setError("SUCCESS");
        productionRecordDto.setErrorNo("0");
        productionRecordDto.setDeviceNo("0");

        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("0").key("k001").val("1").usl("5").lsl("2").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("0").key("k002").val("2").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("0").key("k003").val("3").usl("4").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("0").key("k004").val("4").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("0").key("k005").val("5").build());

        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("001").position("1").key("k001").val("11").usl("15").lsl("12").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("1").key("k002").val("12").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("1").key("k003").val("13").usl("14").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("1").key("k004").val("14").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("1").key("k004").val("15").build());

        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);

        productionController.add(productionRecordDto);

        LambdaQueryWrapper<UvDispensing> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UvDispensing::getBeamSn, beamSn).
                eq(UvDispensing::getPosition, 0).
                orderByDesc(UvDispensing::getId).
                last("LIMIT 1");
        UvDispensing uvDispensing = uvDispensingService.getOne(wrapper);
        assertEquals(1, uvDispensing.getGlueWidth());
        assertEquals(2, uvDispensing.getGlueWidthLsl());
        assertEquals(5, uvDispensing.getGlueWidthUsl());
        assertEquals(3, uvDispensing.getCircleCenterOffset());
        assertEquals(4, uvDispensing.getCircleCenterOffsetUsl());
        assertEquals("5", uvDispensing.getImagePath());

        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UvDispensing::getBeamSn, beamSn).
                eq(UvDispensing::getPosition, 1).
                orderByDesc(UvDispensing::getId).
                last("LIMIT 1");
        uvDispensing = uvDispensingService.getOne(wrapper);
        assertEquals(11, uvDispensing.getGlueWidth());
        assertEquals(12, uvDispensing.getGlueWidthLsl());
        assertEquals(15, uvDispensing.getGlueWidthUsl());
        assertEquals(13, uvDispensing.getCircleCenterOffset());
        assertEquals(14, uvDispensing.getCircleCenterOffsetUsl());
        assertEquals("15", uvDispensing.getImagePath());
    }


    @Test
    void production_aa_1() throws Exception {
        String beamSn = String.valueOf(System.currentTimeMillis());
        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
        productionRecordDto.setProductSn(beamSn);
        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setStepTypeNo("002");
        productionRecordDto.setError("SUCCESS");
        productionRecordDto.setErrorNo("0");
        productionRecordDto.setDeviceNo("0");

        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("0").stage("0").key("k002").val("002").usl("003").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("0").stage("0").key("k003").val("003").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("0").stage("0").key("k004").val("004").usl("005").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("0").stage("0").key("k005").val("005").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("006").position("0").stage("0").key("k002").val("006").usl("007").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("007").position("0").stage("0").key("k003").val("007").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("008").position("0").stage("0").key("k004").val("008").usl("009").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("009").position("0").stage("0").key("k005").val("009").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("010").position("0").stage("0").key("k002").val("010").lsl("011").usl("012").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("011").position("0").stage("0").key("k002").val("011").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("012").position("0").stage("0").key("k002").val("012").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("013").position("0").stage("0").key("k003").val("013").lsl("014").usl("015").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("016").position("0").stage("0").key("k002").val("016").lsl("017").usl("018").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("019").position("0").stage("0").key("k002").val("019").lsl("020").usl("021").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("022").position("0").stage("0").key("k005").val("022").lsl("023").usl("024").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("025").position("0").stage("0").key("k004").val("025").lsl("026").usl("027").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("028").position("0").stage("0").key("k002").val("028").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("029").position("0").stage("0").key("k002").val("029").spec("030").build());

        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("031").position("0").stage("0").key("k003").val("013").lsl("014").usl("031").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("032").position("0").stage("0").key("k002").val("016").lsl("017").usl("032").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("033").position("0").stage("0").key("k002").val("019").lsl("020").usl("033").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("034").position("0").stage("0").key("k005").val("022").lsl("023").usl("034").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("035").position("0").stage("0").key("k004").val("025").lsl("026").usl("035").build());

//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("1").stage("0").key("k002").val("102").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("1").stage("0").key("k003").val("103").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("1").stage("0").key("k004").val("104").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("1").stage("0").key("k005").val("105").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("006").position("1").stage("0").key("k002").val("106").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("007").position("1").stage("0").key("k003").val("107").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("008").position("1").stage("0").key("k004").val("108").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("009").position("1").stage("0").key("k005").val("109").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("010").position("1").stage("0").key("k002").val("110").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("011").position("1").stage("0").key("k002").val("111").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("012").position("1").stage("0").key("k002").val("112").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("013").position("1").stage("0").key("k003").val("113").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("014").position("1").stage("0").key("k004").val("114").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("015").position("1").stage("0").key("k005").val("115").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("016").position("1").stage("0").key("k002").val("116").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("017").position("1").stage("0").key("k002").val("117").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("018").position("1").stage("0").key("k002").val("118").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("019").position("1").stage("0").key("k002").val("119").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("020").position("1").stage("0").key("k003").val("120").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("021").position("1").stage("0").key("k004").val("121").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("022").position("1").stage("0").key("k005").val("122").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("023").position("1").stage("0").key("k002").val("123").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("024").position("1").stage("0").key("k003").val("124").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("025").position("1").stage("0").key("k004").val("125").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("026").position("1").stage("0").key("k005").val("126").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("027").position("1").stage("0").key("k002").val("127").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("028").position("1").stage("0").key("k002").val("128").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("029").position("1").stage("0").key("k002").val("128").build());

        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);

        productionController.add(productionRecordDto);

        LambdaQueryWrapper<MoAutoAdjustInfo> moAutoAdjustInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        moAutoAdjustInfoLambdaQueryWrapper.eq(MoAutoAdjustInfo::getBeamSn, beamSn);

        System.out.println(moAutoAdjustInfoService.list(moAutoAdjustInfoLambdaQueryWrapper));
        LambdaQueryWrapper<MoAutoAdjustSt08> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoAutoAdjustSt08::getBeamSn, beamSn).
                eq(MoAutoAdjustSt08::getPosition, 0).
                orderByDesc(MoAutoAdjustSt08::getId).
                last("LIMIT 1");
        MoAutoAdjustSt08 moAutoAdjustSt08 = moAutoAdjustSt08Service.getOne(wrapper);
        assertEquals(2, moAutoAdjustSt08.getOcXOffset());
        assertEquals(3, moAutoAdjustSt08.getOcXOffsetUsl());
        assertEquals(4, moAutoAdjustSt08.getOcYOffset());
        assertEquals(5, moAutoAdjustSt08.getOcYOffsetUsl());
        assertEquals(6, moAutoAdjustSt08.getCodXOffset());
        assertEquals(7, moAutoAdjustSt08.getCodXOffsetUsl());
        assertEquals(8, moAutoAdjustSt08.getCodYOffset());
        assertEquals(9, moAutoAdjustSt08.getCodYOffsetUsl());
        assertEquals(10, moAutoAdjustSt08.getMtfCenterValue());
        assertEquals(11, moAutoAdjustSt08.getMtfCenterLsl());
//        assertEquals(12, moAutoAdjustSt08.getMtfCenterUsl());
        assertEquals(13, moAutoAdjustSt08.getMtfLeftupValue());
        assertEquals(14, moAutoAdjustSt08.getMtfLeftupLsl());
        assertEquals(16, moAutoAdjustSt08.getMtfRightupValue());
        assertEquals(17, moAutoAdjustSt08.getMtfRightupLsl());
        assertEquals(19, moAutoAdjustSt08.getMtfLeftdownValue());
        assertEquals(20, moAutoAdjustSt08.getMtfLeftdownLsl());

        assertEquals(22, moAutoAdjustSt08.getMtfRightdownValue());
        assertEquals(23, moAutoAdjustSt08.getMtfRightdownLsl());

        assertEquals(25, moAutoAdjustSt08.getMtfRangeOffset());
        assertEquals(27, moAutoAdjustSt08.getMtfRangeOffsetUsl());
        assertEquals("028", moAutoAdjustSt08.getImagePath());
        assertEquals(29, moAutoAdjustSt08.getAaClawGripPressure());
        assertEquals(30, moAutoAdjustSt08.getAaClawGripPressureSpec());

        assertEquals(31, moAutoAdjustSt08.getReleaseClawMinusUvCuredMtfCenterDiffUsl());
        assertEquals(32, moAutoAdjustSt08.getReleaseClawMinusUvCuredMtfTlDiffUsl());
        assertEquals(33, moAutoAdjustSt08.getReleaseClawMinusUvCuredMtfTrDiffUsl());
        assertEquals(34, moAutoAdjustSt08.getReleaseClawMinusUvCuredMtfBlDiffUsl());
        assertEquals(35, moAutoAdjustSt08.getReleaseClawMinusUvCuredMtfBrDiffUsl());

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
    }

    @Test
    void production_calib_1() throws Exception {
        String beamSn = String.valueOf(System.currentTimeMillis());
        ProductionRecordDto productionRecordDto = new ProductionRecordDto();
        productionRecordDto.setProductSn(beamSn);
        productionRecordDto.setStartTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setEndTime(String.valueOf(System.currentTimeMillis()));
        productionRecordDto.setStepTypeNo("020");
        productionRecordDto.setError("SUCCESS");
        productionRecordDto.setErrorNo("0");
        productionRecordDto.setDeviceNo("0");
        productionRecordDto.setOperator("metoak");

        ArrayList<AttrKeyValDto> attrKeyValDtoLefts = new ArrayList<>();
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("351").position("0").stage("0").key("k002").val("002").usl("003").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("0").stage("0").key("k003").val("003").build());
        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("354").position("0").stage("0").key("k004").val("004").usl("005").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("0").stage("0").key("k005").val("005").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("006").position("0").stage("0").key("k002").val("006").usl("007").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("007").position("0").stage("0").key("k003").val("007").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("008").position("0").stage("0").key("k004").val("008").usl("009").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("009").position("0").stage("0").key("k005").val("009").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("010").position("0").stage("0").key("k002").val("010").lsl("011").usl("012").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("011").position("0").stage("0").key("k002").val("011").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("012").position("0").stage("0").key("k002").val("012").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("013").position("0").stage("0").key("k003").val("013").lsl("014").usl("015").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("016").position("0").stage("0").key("k002").val("016").lsl("017").usl("018").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("019").position("0").stage("0").key("k002").val("019").lsl("020").usl("021").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("022").position("0").stage("0").key("k005").val("022").lsl("023").usl("024").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("025").position("0").stage("0").key("k004").val("025").lsl("026").usl("027").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("028").position("0").stage("0").key("k002").val("028").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("029").position("0").stage("0").key("k002").val("029").spec("030").build());
//
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("031").position("0").stage("0").key("k003").val("013").lsl("014").usl("031").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("032").position("0").stage("0").key("k002").val("016").lsl("017").usl("032").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("033").position("0").stage("0").key("k002").val("019").lsl("020").usl("033").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("034").position("0").stage("0").key("k005").val("022").lsl("023").usl("034").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("035").position("0").stage("0").key("k004").val("025").lsl("026").usl("035").build());

//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("002").position("1").stage("0").key("k002").val("102").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("003").position("1").stage("0").key("k003").val("103").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("004").position("1").stage("0").key("k004").val("104").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("005").position("1").stage("0").key("k005").val("105").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("006").position("1").stage("0").key("k002").val("106").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("007").position("1").stage("0").key("k003").val("107").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("008").position("1").stage("0").key("k004").val("108").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("009").position("1").stage("0").key("k005").val("109").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("010").position("1").stage("0").key("k002").val("110").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("011").position("1").stage("0").key("k002").val("111").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("012").position("1").stage("0").key("k002").val("112").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("013").position("1").stage("0").key("k003").val("113").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("014").position("1").stage("0").key("k004").val("114").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("015").position("1").stage("0").key("k005").val("115").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("016").position("1").stage("0").key("k002").val("116").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("017").position("1").stage("0").key("k002").val("117").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("018").position("1").stage("0").key("k002").val("118").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("019").position("1").stage("0").key("k002").val("119").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("020").position("1").stage("0").key("k003").val("120").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("021").position("1").stage("0").key("k004").val("121").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("022").position("1").stage("0").key("k005").val("122").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("023").position("1").stage("0").key("k002").val("123").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("024").position("1").stage("0").key("k003").val("124").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("025").position("1").stage("0").key("k004").val("125").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("026").position("1").stage("0").key("k005").val("126").build());
////        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("027").position("1").stage("0").key("k002").val("127").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("028").position("1").stage("0").key("k002").val("128").build());
//        attrKeyValDtoLefts.add(new AttrKeyValDto().builder().no("029").position("1").stage("0").key("k002").val("128").build());

        productionRecordDto.setAttrKeyVals(attrKeyValDtoLefts);

        productionController.add(productionRecordDto);

        LambdaQueryWrapper<MoAutoAdjustInfo> moAutoAdjustInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        moAutoAdjustInfoLambdaQueryWrapper.eq(MoAutoAdjustInfo::getBeamSn, beamSn);

        System.out.println(moAutoAdjustInfoService.list(moAutoAdjustInfoLambdaQueryWrapper));
        LambdaQueryWrapper<MoCalibration> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoCalibration::getCameraSn, beamSn).
                orderByDesc(MoCalibration::getId).
                last("LIMIT 1");
        MoCalibration moCalibration = moCalibrationService.getOne(wrapper);
        assertEquals("002", moCalibration.getYmlFilename());
        assertEquals("004", moCalibration.getToolVersion());

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
    }

}