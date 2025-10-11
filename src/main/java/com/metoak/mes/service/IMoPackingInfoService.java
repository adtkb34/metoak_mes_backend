package com.metoak.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.entity.MoPackingInfo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IMoPackingInfoService extends IService<MoPackingInfo> {
    Object updateByPackingCode(String packingCode, LocalDate returnRepairDate);

    Object updateByCameraSns(List<String> cameraSns, LocalDate returnRepairDate);

    Object deleteByPackingCode(String packingCode);

    Object deleteByCameraSns(List<String> cameraSns);
}
