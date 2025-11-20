package com.metoak.mes.service;

import com.metoak.mes.entity.MoProduceOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 生产工单信息表 服务类
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
public interface IMoProduceOrderService extends IService<MoProduceOrder> {

    MoProduceOrder updateOrAddByCode(MoProduceOrder moProduceOrder);

    Boolean updateByCode(MoProduceOrder moProduceOrder);
}
