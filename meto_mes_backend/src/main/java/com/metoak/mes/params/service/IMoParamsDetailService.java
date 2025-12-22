package com.metoak.mes.params.service;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.params.dto.ParamsUploadRequest;

/**
 * <p>
 * 参数集详情表 服务类
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
public interface IMoParamsDetailService extends IService<MoParamsDetail> {

    Result<Long> uploadParams(ParamsUploadRequest request);

}