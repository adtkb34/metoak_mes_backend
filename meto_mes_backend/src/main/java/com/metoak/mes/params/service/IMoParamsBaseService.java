package com.metoak.mes.params.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.params.dto.ParamBaseCreateDto;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.vo.MoParamsVO;

import java.util.List;

/**
 * <p>
 * 参数集基础信息表 服务类
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
public interface IMoParamsBaseService extends IService<MoParamsBase> {

    Result<Long> saveBase(ParamBaseCreateDto createDto);

    Result<List<MoParamsVO>> listByType(Integer type);
}
