package com.metoak.mes.mapper;

import com.metoak.mes.entity.MoProcessFlow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 生产流程/工艺流程 Mapper 接口
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
public interface MoProcessFlowMapper extends BaseMapper<MoProcessFlow> {

    void deleteByCode(String processCode);
}
