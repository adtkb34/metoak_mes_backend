package com.metoak.mes.mapper;

import com.metoak.mes.entity.MoMaterialBinding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metoak.mes.traceability.vo.MaterialBindVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kevin
 * @since 2025-07-01 19:21:55
 */
public interface MoMaterialBindingMapper extends BaseMapper<MoMaterialBinding> {

    List<MaterialBindVo> getBindings(String materialCode, String cameraSn, LocalDateTime startTime, LocalDateTime endTime);
}
