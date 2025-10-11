package com.metoak.mes.service;

import com.metoak.mes.dto.ProcessFlowDto;
import com.metoak.mes.entity.MoProcessFlow;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生产流程/工艺流程 服务类
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
public interface IMoProcessFlowService extends IService<MoProcessFlow> {

    List<MoProcessFlow> fetchByCode(String processCode);

    Boolean editByCode(@Valid ProcessFlowDto processFlowDto);

    void add(@Valid ProcessFlowDto processFlowDto);
}
