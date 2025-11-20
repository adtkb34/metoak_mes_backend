package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.entity.MoPackingInfo;
import com.metoak.mes.mapper.MoPackingInfoMapper;
import com.metoak.mes.service.IMoPackingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class MoPackingInfoServiceImpl extends ServiceImpl<MoPackingInfoMapper, MoPackingInfo> implements IMoPackingInfoService {

    @Autowired
    private MoPackingInfoMapper moPackingInfoMapper;

    @Override
    public Boolean updateByPackingCode(String packingCode, LocalDate returnRepairDate) {
        LambdaUpdateWrapper<MoPackingInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(MoPackingInfo::getPackingCode, packingCode).
                set(MoPackingInfo::getReturnRepairDate, returnRepairDate);

        return update(wrapper);
    }

    @Override
    public Boolean updateByCameraSns(List<String> cameraSns, LocalDate returnRepairDate) {
        cameraSns.forEach(item -> {
            LambdaUpdateWrapper<MoPackingInfo> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(MoPackingInfo::getCameraSn, item).
                    set(MoPackingInfo::getReturnRepairDate, returnRepairDate);

            update(wrapper);
        });
        return true;
    }

    @Override
    public Boolean deleteByPackingCode(String packingCode) {
        return moPackingInfoMapper.deleteByPackingCode(packingCode);
    }

    @Override
    public Object deleteByCameraSns(List<String> cameraSns) {
        cameraSns.forEach(item -> {
            moPackingInfoMapper.deleteByCameraSn(item);
        });
        return null;
    }
}
