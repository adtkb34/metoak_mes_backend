package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.dto.CalibresultLeftCenterOffsetOfXAndYDto;
import com.metoak.mes.entity.Calibresult;
import com.metoak.mes.mapper.CalibresultMapper;
import com.metoak.mes.service.ICalibresultService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Data
@Service
public class CalibresultServiceImpl extends ServiceImpl<CalibresultMapper, Calibresult> implements ICalibresultService {

//    @Autowired
//    private CalibresultMapper calibresultMapper;

    private String csvFilePath = "/home/kevin/Downloads/CalibResult.csv";

    @Autowired
    private CalibresultMapper mapper;

    @Override
    public CalibresultLeftCenterOffsetOfXAndYDto listCalibresultVo(String cameraSNPrefix, Integer stationNumber, String startDateStr, String endDateStr) {
        LambdaUpdateWrapper<Calibresult> wrapper = new LambdaUpdateWrapper<Calibresult>().likeRight(Calibresult::getCameraSN, cameraSNPrefix).eq(Calibresult::getStation, stationNumber).
                ge(Calibresult::getTimeStamp, startDateStr).le(endDateStr != null, Calibresult::getTimeStamp, endDateStr);

        List<Calibresult> list = list(wrapper);
        List<Integer> leftCenterOffsetXs = new ArrayList<>();
        List<Integer> leftCenterOffsetYs = new ArrayList<>();
        list.forEach(item -> {
            leftCenterOffsetXs.add(item.getLeftCenterOffsetX());
            leftCenterOffsetYs.add(item.getLeftCenterOffsetY());
            leftCenterOffsetYs.add(item.getRightCenterOffsetY());
        });
        Collections.sort(leftCenterOffsetXs);

//        // 2. 计算要筛掉的元素数量
//        int size = leftCenterOffsetXs.size();
//        int removeCount = (int) (size * 0.01); // 1% 的元素数量

//        // 3. 筛掉元素
//
//        // 筛掉最大的 1%
//        for (int i = 0; i < removeCount; i++) {
//            leftCenterOffsetXs.remove(size - 1 - i);
//        }
//        // 筛掉最小的 1%
//        for (int i = 0; i < removeCount; i++) {
//            leftCenterOffsetXs.remove(0);
//        }
//        System.out.println(leftCenterOffsetXs);
//        Collections.sort(leftCenterOffsetYs);
//
//        // 2. 计算要筛掉的元素数量
//        size = leftCenterOffsetYs.size();
//        removeCount = (int) (size * 0.01); // 1% 的元素数量

//        // 3. 筛掉元素
//
//        // 筛掉最大的 1%
//        for (int i = 0; i < removeCount; i++) {
//            leftCenterOffsetYs.remove(size - 1 - i);
//        }
//        // 筛掉最小的 1%
//        for (int i = 0; i < removeCount; i++) {
//            leftCenterOffsetYs.remove(0);
//        }
        CalibresultLeftCenterOffsetOfXAndYDto calibresultVo = new CalibresultLeftCenterOffsetOfXAndYDto(leftCenterOffsetXs, leftCenterOffsetYs);




        return calibresultVo;
    }

    @Override
    public CalibresultLeftCenterOffsetOfXAndYDto listCalibresultVo2(String cameraSNPrefix, Integer stationNumber, String startDateStr, String endDateStr) {
        List<String> selectedColumnNames = Arrays.asList("CameraSN", "TimeStamp", "Station", "LeftCenterOffsetX", "LeftCenterOffsetY", "Simor-max-right-reprojection-error");
        Map<String, Integer> selectedColumnNameIdx = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            String[] columnNames = line.split(",");
            for (int i = 0; i < columnNames.length; i++) {
                String columnName = columnNames[i];
                if (selectedColumnNames.contains(columnName)) {
                    selectedColumnNameIdx.put(columnName, i);
                }
            }
            List<Integer> leftCenterOffsetXs = new ArrayList<>();
            List<Integer> leftCenterOffsetYs = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                // 使用逗号分隔符分割字符串
                try {

                    String[] columnValues = line.split(",");

                    System.out.println(selectedColumnNameIdx.get("CameraSN") + selectedColumnNameIdx.get("TimeStamp"));
                    Float SimorMaxRightReprojectionError = Float.parseFloat(columnValues[selectedColumnNameIdx.get("Simor-max-right-reprojection-error")]);
//                    if (SimorMaxRightReprojectionError.equals(0f)) {
//                        continue;
//                    }
                    String timeStamp = columnValues[selectedColumnNameIdx.get("TimeStamp")];
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
                    LocalDate date = LocalDateTime.parse(timeStamp, dateTimeFormatter).toLocalDate();

                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
                    LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

                    if (startDate.isAfter(date)) {
                        continue;
                    }
                    if (endDate.isBefore(date)) {
                        continue;
                    }
                    String cameraSN = columnValues[selectedColumnNameIdx.get("CameraSN")];
                    Integer stationNumberFromCsv = Integer.parseInt(columnValues[selectedColumnNameIdx.get("Station")]);
                    if (!cameraSN.startsWith(cameraSNPrefix) || !stationNumberFromCsv.equals(stationNumber)) {
                        continue;
                    }
                    Integer LeftCenterOffsetX = Integer.parseInt(columnValues[selectedColumnNameIdx.get("LeftCenterOffsetX")]);
//                    if (LeftCenterOffsetX < leftCenterOffsetXMin || LeftCenterOffsetX > leftCenterOffsetXMax) {
//                        continue;
//                    }
                    Integer LeftCenterOffsetY = Integer.parseInt(columnValues[selectedColumnNameIdx.get("LeftCenterOffsetY")]);
//                    if (LeftCenterOffsetY < leftCenterOffsetYMin || LeftCenterOffsetY > leftCenterOffsetYMax) {
//                        continue;
//                    }
                    leftCenterOffsetXs.add(LeftCenterOffsetX);
                    leftCenterOffsetYs.add(LeftCenterOffsetY);

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            CalibresultLeftCenterOffsetOfXAndYDto calibresultVo = new CalibresultLeftCenterOffsetOfXAndYDto(leftCenterOffsetXs, leftCenterOffsetYs);
            return calibresultVo;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return calibresultVo;
        return null;
    }

    @Override
    public Calibresult fetchByCameraSn(String cameraSn) {
        LambdaQueryWrapper<Calibresult> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Calibresult::getCameraSN, cameraSn);
        List<Calibresult> calibresults = list(wrapper);

        return calibresults.get(calibresults.size()-1);
    }

}   
