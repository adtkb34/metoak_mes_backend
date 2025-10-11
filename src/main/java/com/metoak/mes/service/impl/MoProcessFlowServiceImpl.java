package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.dto.ProcessFlowDto;
import com.metoak.mes.entity.MoProcessFlow;
import com.metoak.mes.mapper.MoPackingInfoMapper;
import com.metoak.mes.mapper.MoProcessFlowMapper;
import com.metoak.mes.service.IMoProcessFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生产流程/工艺流程 服务实现类
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Service
public class MoProcessFlowServiceImpl extends ServiceImpl<MoProcessFlowMapper, MoProcessFlow> implements IMoProcessFlowService {

    @Autowired
    private MoProcessFlowMapper moProcessFlowMapper;

    @Override
    public List<MoProcessFlow> fetchByCode(String processCode) {
        LambdaQueryWrapper<MoProcessFlow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoProcessFlow::getProcessCode, processCode);

        return list(wrapper);
    }

    @Override
    public Boolean editByCode(ProcessFlowDto processFlowDto) {
        moProcessFlowMapper.deleteByCode(processFlowDto.getProcessCode());
        add(processFlowDto);

        return null;
    }

    @Override
    public void add(ProcessFlowDto processFlowDto) {

        processFlowDto.getStageCodes().forEach(item -> {
            save(MoProcessFlow.builder().
                    processCode(processFlowDto.getProcessCode()).
                    processName(processFlowDto.getProcessName()).
                    processDesc(processFlowDto.getProcessDesc()).
                    stageCode(item).
                    build());
        });
    }
}
