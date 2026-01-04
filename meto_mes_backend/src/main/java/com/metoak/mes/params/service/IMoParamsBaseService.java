package com.metoak.mes.params.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.params.dto.ParamBaseCreateDto;
import com.metoak.mes.params.dto.ParamsQueryDto;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.vo.MoParamsVo;
import com.metoak.mes.params.vo.ParamsPayload;

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

    Result<List<MoParamsVo>> list(ParamsQueryDto paramsQueryDto);

    Result<List<MoParamsBase>> listByType(Integer type);

    Result<ParamsPayload> getBySn(String sn);

    Result<ParamsPayload> getByOrderId(Long OrderId);

    Result<ParamsPayload> getByFlowNo(String flowNo);

    Result<ParamsPayload> getByDetailId(Long id);

    Result<ParamsPayload> getByStepNo(String stepNo);
}
