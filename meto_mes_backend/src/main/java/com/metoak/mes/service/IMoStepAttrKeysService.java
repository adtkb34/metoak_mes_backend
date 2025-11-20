package com.metoak.mes.service;

import com.metoak.mes.entity.MoStepAttrKeys;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2025-09-15 17:42:42
 */
public interface IMoStepAttrKeysService extends IService<MoStepAttrKeys> {

    Map<String, String> getByStepTypeNo(String stepTypeNo);

    /**
     * 根据工序编号与属性编号获取对应的展示标签。
     *
     * @param stepTypeNo 工序编号
     * @param attrNo     属性编号
     * @return 对应的标签，若未找到则返回 {@code null}
     */
    String getLabel(String stepTypeNo, String attrNo);
}
