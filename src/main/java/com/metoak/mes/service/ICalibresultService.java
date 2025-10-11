package com.metoak.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.dto.CalibresultLeftCenterOffsetOfXAndYDto;
import com.metoak.mes.entity.Calibresult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
public interface ICalibresultService extends IService<Calibresult> {

    CalibresultLeftCenterOffsetOfXAndYDto listCalibresultVo(String cameraSNPrefix, Integer stationNumber, String startDateStr, String endDateStr);

    CalibresultLeftCenterOffsetOfXAndYDto listCalibresultVo2(String cameraSNPrefix, Integer stationNumber, String startDateStr, String endDateStr);

    Calibresult fetchByCameraSn(String cameraSn);
}
