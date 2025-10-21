package com.metoak.mes.service.impl;

import com.metoak.mes.service.IMoPackingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class ProcessStepLatestResultTest {


//    @Test
//    void test_hasCurrentStepBeenCompleted_1() throws Exception {
//        String productSn = String.valueOf(System.currentTimeMillis()) + "1";
//        String productSn2 = String.valueOf(System.currentTimeMillis()) + "2";
//        String productSn3 = String.valueOf(System.currentTimeMillis()) + "3";
//        String packingCode = String.valueOf(System.currentTimeMillis());
//        String packingCode2 = String.valueOf(System.currentTimeMillis()) + "2";
//        moPackingInfoService.save(MoPackingInfo.builder().packingCode(packingCode).cameraSn(productSn).operator("szdev").startTime(LocalDateTime.now().toString()).stationNumber(1).build());
//        moPackingInfoService.save(MoPackingInfo.builder().packingCode(packingCode).cameraSn(productSn2).operator("szdev").startTime(LocalDateTime.now().toString()).stationNumber(1).build());
//        moPackingInfoService.save(MoPackingInfo.builder().packingCode(packingCode2).cameraSn(productSn3).operator("szdev").startTime(LocalDateTime.now().toString()).stationNumber(1).build());
//
//        moPackingInfoService.updateByPackingCode()
//    }
}