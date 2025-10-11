package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.metoak.mes.dto.WorkOrderDto;
import com.metoak.mes.entity.MoProduceOrder;
import com.metoak.mes.mapper.MoProduceOrderMapper;
import com.metoak.mes.service.IMoProduceOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 生产工单信息表 服务实现类
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Service
public class MoProduceOrderServiceImpl extends ServiceImpl<MoProduceOrderMapper, MoProduceOrder> implements IMoProduceOrderService {

    @Override
    public MoProduceOrder updateOrAddByCode(MoProduceOrder moProduceOrder) {
        List<MoProduceOrder> moProduceOrders = fetchByCode(moProduceOrder.getWorkOrderCode());
        if (!moProduceOrders.isEmpty()) {
            moProduceOrder.setFlowCode(moProduceOrders.get(0).getFlowCode());
            updateByCode(moProduceOrder);
            return moProduceOrder;
        }

        save(moProduceOrder);
        return moProduceOrder;
    }

    public List<MoProduceOrder> fetchByCode(String code) {
        LambdaQueryWrapper<MoProduceOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoProduceOrder::getWorkOrderCode, code);

        return list(wrapper);
    }

    @Override
    public Boolean updateByCode(MoProduceOrder moProduceOrder) {
        LambdaUpdateWrapper<MoProduceOrder> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(MoProduceOrder::getWorkOrderCode, moProduceOrder.getWorkOrderCode());

        return update(moProduceOrder, wrapper);
    }

}
