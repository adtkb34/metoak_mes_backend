package com.metoak.mes.service.impl;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.entity.MoImuCalibResults;
import com.metoak.mes.mapper.MoCalibrationMapper;
import com.metoak.mes.mapper.MoImuCalibResultsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class MoImuCalibServiceImplIntegratedTest {

    @Autowired
    private MoImuCalibResultsServiceImpl service;

    @Autowired
    private MoCalibrationMapper moCalibrationMapper;

    @Autowired
    private MoImuCalibResultsMapper mapper;


    @Test
    void should_return_latest_record_by_id_desc() {
        String cameraSN = String.valueOf(System.currentTimeMillis());
        MoCalibration moCalibration = MoCalibration.builder()
                .cameraSn(cameraSN)
                .ymlFilename("ymlFilename")
                .errorCode(0)
                .startTime(LocalDateTime.now())
                .operator("szdev")
                .stationNumber(1)
                .build();
        moCalibrationMapper.insert(moCalibration);


        // 插入旧记录
        mapper.insert(MoImuCalibResults.builder()
                .sn(cameraSN)
                .caseName("old")
                .errorCode(0)
                .root("root_old")
                .relativePathYmlImu("relativePathYmlImu_old")
                .relativePathYmlImucam("relativePathYmlImucam_old")
                .moCalibrationId(moCalibration.getId())
                .build());

        // 插入新记录
        mapper.insert(MoImuCalibResults.builder()
                .sn(cameraSN)
                .caseName("new")
                .errorCode(0)
                .root("root_new")
                .relativePathYmlImu("relativePathYmlImu_new")
                .relativePathYmlImucam("relativePathYmlImucam_new")
                .moCalibrationId(moCalibration.getId())
                .build());

        Result result = service.getLatest(cameraSN);
        Map<String, List<String>> data = (Map<String, List<String>>) result.getData();

        assertEquals("relativePathYmlImu_new", data.get("relative_path_yml_imu"));
        assertEquals("relativePathYmlImucam_new", data.get("relative_path_yml_imucam"));
        assertEquals("root_new", data.get("root"));
        assertEquals("ymlFilename", data.get("yml_filename"));
    }


}