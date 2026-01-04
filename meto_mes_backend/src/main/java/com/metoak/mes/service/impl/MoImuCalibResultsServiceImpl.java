package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.entity.MoImuCalibResults;
import com.metoak.mes.mapper.MoImuCalibResultsMapper;
import com.metoak.mes.service.IMoCalibrationService;
import com.metoak.mes.service.IMoImuCalibResultsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.metoak.mes.common.result.ResultCodeEnum.*;
import static com.metoak.mes.common.result.ResultCodeEnum.STEREO_CALIBRATION_RECORD_NOT_FOUND;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kevin
 * @since 2025-07-01 19:21:55
 */
@Service
public class MoImuCalibResultsServiceImpl extends ServiceImpl<MoImuCalibResultsMapper, MoImuCalibResults> implements IMoImuCalibResultsService {

    @Autowired
    private IMoCalibrationService moCalibrationService;

    @Override
    public Result getLatest(String productSn) {
        LambdaQueryWrapper<MoImuCalibResults> wrapper = new LambdaQueryWrapper<MoImuCalibResults>()
                .eq(MoImuCalibResults::getSn, productSn)
                .orderByDesc(MoImuCalibResults::getId)
                .last("LIMIT 1");
        MoImuCalibResults lastRecord = getOne(wrapper);
        if (lastRecord == null) {
            return Result.fail(NO_IMU_CALCULATION_RECORD);
        }
        Integer errorCode = lastRecord.getErrorCode();
        if (!errorCode.equals(0)) {
            return Result.fail(errorCode, LATEST_IMU_CALCULATION_FAILED.getName());
        }

        if (lastRecord.getMoCalibrationId() == null) {
            return Result.fail(IMU_CALCULATION_RECORD_NOT_LINKED_TO_STEREO_CALIBRATION);
        }

        LambdaQueryWrapper<MoCalibration> wrapper2 = new LambdaQueryWrapper<MoCalibration>()
                .eq(MoCalibration::getId, lastRecord.getMoCalibrationId());
        MoCalibration calibLastRecord = moCalibrationService.getOne(wrapper2);
        if (calibLastRecord == null) {
            return Result.fail(STEREO_CALIBRATION_RECORD_NOT_FOUND);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("root", lastRecord.getRoot());
        result.put("relative_path_yml_imu", lastRecord.getRelativePathYmlImu());
        result.put("relative_path_yml_imucam", lastRecord.getRelativePathYmlImucam());
        result.put("yml_filename", calibLastRecord.getYmlFilename());

        if (result.values().stream().anyMatch(Objects::isNull)) {
            return Result.fail(HAS_NULL_VALUE);
        }

        return Result.ok(result);
    }
}
