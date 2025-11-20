package com.metoak.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorStatisticsDto {
    List<Integer> counts;
    List<LocalDateTime> times;
    String error;
    Integer errorNum;
    Integer sumCount;

    @Override
    public String toString() {
        return "ErrorStatisticsDto{" +
                "counts=" + counts +
                ", times=" + times +
                ", error='" + error + '\'' +
                ", errorNum=" + errorNum +
                ", sumCount=" + sumCount +
                '}';
    }
}
