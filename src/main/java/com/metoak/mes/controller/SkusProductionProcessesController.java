package com.metoak.mes.controller;

import com.metoak.mes.common.ResultBean;
import com.metoak.mes.dto.AttrKeyValueDto;
import com.metoak.mes.dto.SkuProductionProcessDto2;
import com.metoak.mes.dto.SkuProductionProcessTaskDto;
import com.metoak.mes.entity.MoImuResult;
import com.metoak.mes.service.ICalibresultService;
import com.metoak.mes.service.IMoImuResultsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/production")
@Tag(name = "产品生产")
public class SkusProductionProcessesController {

    @Autowired
    private IMoImuResultsService moImuResultsService;

    @Autowired
    private ICalibresultService calibresultService;


    @PostMapping("results")
    @Operation(summary = "添加生产结果")
    public ResultBean<Long> addResult(@RequestBody SkuProductionProcessDto2 skuProductionProcessDto) {
        if (skuProductionProcessDto.getProductionProcessNo().equals("Step 28")) {
            // TODO. error code
            List<SkuProductionProcessTaskDto> taskResults = skuProductionProcessDto.getTaskResults();
            List<AttrKeyValueDto> attrKeyValues = taskResults.get(0).getAttrKeyValues();
            String productSn = skuProductionProcessDto.getProductSn();
            Map<Integer, MoImuResult> stringMoImuResultsMap = new HashMap<>();
            attrKeyValues.forEach(item -> {
                String errorNo = taskResults.get(0).getProductionProcessErrorNo();
                Integer status = -1;
                if ("0".equals(errorNo)) {
                    status = 0;
                }
                Integer position = item.getPosition();
                if (!stringMoImuResultsMap.containsKey(position)) {
                    MoImuResult moImuResult = MoImuResult.builder().
                                                                cameraSn(productSn).
                                                                position(position).
                                                                calibresultId(calibresultService.fetchByCameraSn(productSn).getId()).
                                                                status(status).
                                                                errorNo(errorNo).
                                                                build();
                    stringMoImuResultsMap.put(position, moImuResult);
                }
                MoImuResult moImuResult = stringMoImuResultsMap.get(position);
                moImuResult.setPosition(item.getPosition());
                System.out.println(item);
                if (item.getNo().equals("calib001")) {
                    moImuResult.setRRoll(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib002")) {
                    moImuResult.setRPitch(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib003")) {
                    moImuResult.setRYaw(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib004")) {
                    moImuResult.setTX(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib005")) {
                    moImuResult.setTY(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib006")) {
                    moImuResult.setTZ(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib007")) {
                    moImuResult.setAccelerometerErrorMean(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib008")) {
                    moImuResult.setAccelerometerErrorStd(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib009")) {
                    moImuResult.setGyroscopeErrorMean(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib010")) {
                    moImuResult.setGyroscopeErrorStd(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib011")) {
                    moImuResult.setReprojectionErrorMean(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib012")) {
                    moImuResult.setReprojectionErrorStd(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib013")) {
                    moImuResult.setAccelerometerNoiseDensity(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib014")) {
                    moImuResult.setAccelerometerRandomWalk(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib015")) {
                    moImuResult.setGyroscopeNoiseDensity(Double.parseDouble(item.getVal()));
                } else if (item.getNo().equals("calib016")) {
                    moImuResult.setGyroscopeRandomWalk(Double.parseDouble(item.getVal()));
                }
                });
                stringMoImuResultsMap.forEach((k, v) -> {
                    moImuResultsService.save(v);
                });
        } else {
            return ResultBean.fail();
        }
        return ResultBean.ok();
    }


}
