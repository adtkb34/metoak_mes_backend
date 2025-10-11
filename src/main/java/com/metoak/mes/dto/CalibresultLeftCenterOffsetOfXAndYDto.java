package com.metoak.mes.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalibresultLeftCenterOffsetOfXAndYDto {
    private List<Integer> leftCenterOffsetXs;
    private List<Integer> leftCenterOffsetYs;

    public CalibresultLeftCenterOffsetOfXAndYDto() {
        this.leftCenterOffsetXs = new ArrayList<>();
        this.leftCenterOffsetYs = new ArrayList<>();
    }

    public CalibresultLeftCenterOffsetOfXAndYDto(List<Integer> leftCenterOffsetXs, List<Integer> leftCenterOffsetYs) {
        this.leftCenterOffsetXs = leftCenterOffsetXs;
        this.leftCenterOffsetYs = leftCenterOffsetYs;
    }

    public List<Integer> getLeftCenterOffsetXs() {
        return leftCenterOffsetXs;
    }

    public void setLeftCenterOffsetXs(List<Integer> leftCenterOffsetXs) {
        this.leftCenterOffsetXs = leftCenterOffsetXs;
    }

    public List<Integer> getLeftCenterOffsetYs() {
        return leftCenterOffsetYs;
    }

    public void setLeftCenterOffsetYs(List<Integer> leftCenterOffsetYs) {
        this.leftCenterOffsetYs = leftCenterOffsetYs;
    }
}
