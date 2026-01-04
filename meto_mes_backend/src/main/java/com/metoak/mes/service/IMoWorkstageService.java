package com.metoak.mes.service;

import com.metoak.mes.entity.MoWorkstage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工序表 服务类
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
public interface IMoWorkstageService extends IService<MoWorkstage> {

    String getNameByCode(String stageCode);

    MoWorkstage getByCode(String stageCode);

    MoWorkstage getByNo(String stepNo);
}
