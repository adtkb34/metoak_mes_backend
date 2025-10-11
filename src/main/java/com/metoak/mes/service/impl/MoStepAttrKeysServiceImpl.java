package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.entity.MoStepAttrKeys;
import com.metoak.mes.mapper.MoStepAttrKeysMapper;
import com.metoak.mes.service.IMoStepAttrKeysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kevin
 * @since 2025-09-15 17:42:42
 */
@Service
public class MoStepAttrKeysServiceImpl extends ServiceImpl<MoStepAttrKeysMapper, MoStepAttrKeys> implements IMoStepAttrKeysService {

    @Override
    public Map<String, String> getByStepTypeNo(String stepTypeNo) {
        LambdaQueryWrapper<MoStepAttrKeys> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoStepAttrKeys::getStepTypeNo, stepTypeNo);
        return list(wrapper).stream().collect(Collectors.toMap(
                moStepAttrKeys -> moStepAttrKeys.getAttrKey().toLowerCase(), // 转换为小写
                MoStepAttrKeys::getAttrNo,
                (existingValue, newValue) -> existingValue // 合并函数，取旧值
        ));
    }
}
