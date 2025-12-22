package com.metoak.mes.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.metoak.mes.common.util.TimestampUtils.convertToDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProcessStepProductionRecordsServiceImplTest {

    @Autowired
    private ProcessStepProductionRecordsServiceImpl processStepProductionRecordsService;


    @Test
    void should_return_start_time_when_post_time_is_null() {
        // Given
        String startTime = String.valueOf(ZonedDateTime
                .now(ZoneId.of("Asia/Shanghai"))
                .toInstant()
                .toEpochMilli()+1000000L);

        String postTime = String.valueOf(ZonedDateTime
                .now(ZoneId.of("Asia/Shanghai"))
                .toInstant()
                .toEpochMilli()+1000000L);
        System.out.println(convertToDateTime(startTime));
        System.out.println(processStepProductionRecordsService.updateTimeByPostTime(startTime, postTime));
    }

}