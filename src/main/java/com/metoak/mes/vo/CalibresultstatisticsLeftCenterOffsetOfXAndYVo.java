package com.metoak.mes.vo;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class CalibresultstatisticsLeftCenterOffsetOfXAndYVo {
    private double xAverage;
    private double yAverage;
    private double xMedian;
    private double yMedian;
    private TreeMap<Integer, Integer> xcountOccurrences;
    private TreeMap<Integer, Integer> ycountOccurrences;
    private double xRange;
    private double yRange;

    public CalibresultstatisticsLeftCenterOffsetOfXAndYVo(double xAverage,
                                                          double yAverage,
                                                          double xMedian,
                                                          double yMedian,
                                                          TreeMap<Integer, Integer> xcountOccurrences,
                                                          TreeMap<Integer, Integer> ycountOccurrences,
                                                          double xRange,
                                                          double yRange) {
        this.xAverage = xAverage;
        this.yAverage = yAverage;
        this.xMedian = xMedian;
        this.yMedian = yMedian;
        this.xcountOccurrences = xcountOccurrences;
        this.ycountOccurrences = ycountOccurrences;
    }

    public double getxAverage() {
        return xAverage;
    }

    public void setxAverage(double xAverage) {
        this.xAverage = xAverage;
    }

    public double getyAverage() {
        return yAverage;
    }

    public void setyAverage(double yAverage) {
        this.yAverage = yAverage;
    }

    public double getxMedian() {
        return xMedian;
    }

    public void setxMedian(double xMedian) {
        this.xMedian = xMedian;
    }

    public double getyMedian() {
        return yMedian;
    }

    public void setyMedian(double yMedian) {
        this.yMedian = yMedian;
    }

    public Map<Integer, Integer> getXcountOccurrences() {
        return xcountOccurrences;
    }

    public void setXcountOccurrences(TreeMap<Integer, Integer> xcountOccurrences) {
        this.xcountOccurrences = xcountOccurrences;
    }

    public Map<Integer, Integer> getYcountOccurrences() {
        return ycountOccurrences;
    }

    public void setYcountOccurrences(TreeMap<Integer, Integer> ycountOccurrences) {
        this.ycountOccurrences = ycountOccurrences;
    }

    public double getxRange() {
        return xRange;
    }

    public void setxRange(double xRange) {
        this.xRange = xRange;
    }

    public double getyRange() {
        return yRange;
    }

    public void setyRange(double yRange) {
        this.yRange = yRange;
    }
}
