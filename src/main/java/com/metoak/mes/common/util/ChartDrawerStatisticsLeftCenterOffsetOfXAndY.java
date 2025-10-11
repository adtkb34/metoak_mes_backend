package com.metoak.mes.common.util;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.controller.CalibresultController;
import com.metoak.mes.mapper.CalibresultMapper;
import com.metoak.mes.service.ICalibresultService;
import com.metoak.mes.service.impl.CalibresultServiceImpl;
import com.metoak.mes.vo.CalibresultstatisticsLeftCenterOffsetOfXAndYVo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
//import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


//@SpringBootApplication
@ComponentScan(basePackages = {"com.metoak.mes"})
public class ChartDrawerStatisticsLeftCenterOffsetOfXAndY {

    static private Integer stationNumber;

    public static void main(String[] args) {
//        CalibresultController calibresultController = new CalibresultController();
//        calibresultController.setService(new CalibresultServiceImpl());
        ApplicationContext context = SpringApplication.run(ChartDrawerStatisticsLeftCenterOffsetOfXAndY.class, args);
        CalibresultController calibresultController = context.getBean(CalibresultController.class);
        ChartDrawerStatisticsLeftCenterOffsetOfXAndY.stationNumber = 3;
        String startDate = "2025-01-06";
        String endDate = "2025-01-10";
        Result result = calibresultController.statisticsLeftCenterOffsetOfXAndY("S315", 3, startDate, endDate);
        CalibresultstatisticsLeftCenterOffsetOfXAndYVo data = (CalibresultstatisticsLeftCenterOffsetOfXAndYVo) result.getData();
        ChartDrawerStatisticsLeftCenterOffsetOfXAndY.drawStatisticsChart(data);
    }

    public static void drawStatisticsChart(CalibresultstatisticsLeftCenterOffsetOfXAndYVo result) {
        // 创建数据集
        CategoryDataset xDataset = createDataset(result.getXcountOccurrences());
        CategoryDataset yDataset = createDataset(result.getYcountOccurrences());

//        // 创建图表
//        JFreeChart xChart = ChartFactory.createBarChart(
//                String.format("Station %d, X Offset Occurrences", ChartDrawerStatisticsLeftCenterOffsetOfXAndY.stationNumber)
//                , // 图表标题
//                "X Offset Values (平均值：" + result.getxAverage() + " 中值：" + result.getxMedian()+")",      // X 轴标签
//                "Count",                // Y 轴标签
//                xDataset
//        );
//        xChart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
//
//        JFreeChart yChart = ChartFactory.createBarChart(
//                "Y Offset Occurrences", // 图表标题
//                "Y Offset Values (平均值：" + result.getyAverage() + " 中值：" + result.getyMedian()+")",      // Y 轴标签
//                "Count",                // Y 轴标签
//                yDataset
//        );
//        yChart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
//
//        // 创建面板并添加图表
//        JPanel panel = new JPanel(new GridLayout(2, 1));
//        panel.add(new ChartPanel(xChart));
//        panel.add(new ChartPanel(yChart));
//
//        // 创建 JFrame
//        JFrame frame = new JFrame("Statistics Chart");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(panel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
        JFreeChart xChart = ChartFactory.createBarChart(
                String.format("Station %d, left X Offset Occurrences", ChartDrawerStatisticsLeftCenterOffsetOfXAndY.stationNumber),
                "X Offset Values (平均值：" + result.getxAverage() + " 中值：" + result.getxMedian() + " 极差：" + result.getxRange() + ")",
                "Count",
                xDataset
        );
        xChart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        JFreeChart yChart = ChartFactory.createBarChart(
                String.format("Station %d, left + right Y Offset Occurrences", ChartDrawerStatisticsLeftCenterOffsetOfXAndY.stationNumber),
                "Y Offset Values (平均值：" + result.getyAverage() + " 中值：" + result.getyMedian() + " 极差：" + result.getyRange() + ")",
                "Count",
                yDataset
        );
        yChart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // 保存图表为 PNG 文件
        try {
            ChartUtils.saveChartAsPNG(new File("xChart.png"), xChart, 800, 600);
            ChartUtils.saveChartAsPNG(new File("yChart.png"), yChart, 800, 600);
            System.out.println("Charts saved as PNG files.");
        } catch (Exception e) {
            System.err.println("Error saving charts as PNG files.");
            e.printStackTrace();
        }
    }

    private static CategoryDataset createDataset(Map<Integer, Integer> occurrences) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            dataset.addValue(entry.getValue(), "Count", entry.getKey());
        }
        return dataset;
    }
}