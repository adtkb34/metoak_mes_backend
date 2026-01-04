package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.entity.MoWorkstage;
import com.metoak.mes.mapper.MoWorkstageMapper;
import com.metoak.mes.service.IMoWorkstageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工序表 服务实现类
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Service
public class MoWorkstageServiceImpl extends ServiceImpl<MoWorkstageMapper, MoWorkstage> implements IMoWorkstageService {

    @Override
    public String getNameByCode(String stageCode) {
        return getByCode(stageCode).getStageName();
    }

    @Override
    public MoWorkstage getByCode(String stageCode) {
        LambdaQueryWrapper<MoWorkstage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoWorkstage::getStageCode, stageCode);

        return list(wrapper).get(0);
    }

    @Override
    public MoWorkstage getByNo(String stepNo) {
        LambdaQueryWrapper<MoWorkstage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoWorkstage::getStepTypeNo, stepNo);

        return list(wrapper).get(0);
    }
}
