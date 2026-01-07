package com.metoak.mes.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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
    void shouldReturnNowWhenStartTimeIsNull() {
        // Given
        String postTime = String.valueOf(System.currentTimeMillis());

        // When
        LocalDateTime result =
                processStepProductionRecordsService.updateTimeByPostTime(null, postTime);

        // Then
        LocalDateTime now = LocalDateTime.now().withNano(0);
        assertEquals(now, result.withNano(0));
    }

    @Test
    void shouldReturnStartTimeWhenPostTimeIsNull() {
        // Given
        String startTime = String.valueOf(1017427200000L); // 固定时间

        // When
        LocalDateTime result =
                processStepProductionRecordsService.updateTimeByPostTime(startTime, null);

        // Then
        LocalDateTime expected = convertToDateTime(startTime);
        assertEquals(expected, result);
    }

    @Test
    void shouldShiftStartTimeForwardWhenPostTimeIsInPast() {
        // Given
        LocalDateTime now = LocalDateTime.now().withNano(0);
        LocalDateTime post = now.minusMinutes(10);
        LocalDateTime start = now.minusHours(1);

        String postTime = String.valueOf(post.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String startTime = String.valueOf(start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        // When
        LocalDateTime result =
                processStepProductionRecordsService.updateTimeByPostTime(startTime, postTime);

        // Then
        LocalDateTime expected = start.plusMinutes(10).withNano(0);
        assertEquals(expected, result.withNano(0));
    }

    @Test
    void shouldShiftStartTimeBackwardWhenPostTimeIsInFuture() {
        // Given
        LocalDateTime now = LocalDateTime.now().withNano(0);
        LocalDateTime post = now.plusMinutes(5);
        LocalDateTime start = now.minusHours(1);

        String postTime = String.valueOf(post.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String startTime = String.valueOf(start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        // When
        LocalDateTime result =
                processStepProductionRecordsService.updateTimeByPostTime(startTime, postTime);

        // Then
        LocalDateTime expected = start.minusMinutes(5).withNano(0);
        assertEquals(expected, result.withNano(0));
    }

    @Test
    void updateStartTimeWhenPostTimeIsSame() {
        // Given
        String startTime = String.valueOf(1017427200000L);

        String postTime = String.valueOf(1017427200000L);
        LocalDateTime updatedStartTime = processStepProductionRecordsService.updateTimeByPostTime(startTime, postTime);
        LocalDateTime now = LocalDateTime.now().withNano(0);
        assertEquals(updatedStartTime.withNano(0), now);

    }

}