package com.metoak.mes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metoak.mes.entity.MoPackingInfo;

public interface MoPackingInfoMapper extends BaseMapper<MoPackingInfo> {
    Boolean deleteByPackingCode(String packingCode);

    void deleteByCameraSn(String cameraSn);
}
