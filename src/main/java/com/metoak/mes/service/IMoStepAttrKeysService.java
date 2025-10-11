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
}
