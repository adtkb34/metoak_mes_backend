package com.metoak.mes.packing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.packing.dto.PackingWeightRuleCreateDto;
import com.metoak.mes.packing.entity.PackingWeightRule;
import com.metoak.mes.packing.vo.PackingWeightRuleVO;

import java.util.List;

public interface PackingWeightRuleService extends IService<PackingWeightRule> {

    Result<List<PackingWeightRuleVO>> listRules();

    Result<Long> createRule(PackingWeightRuleCreateDto createDto);

    Result<PackingWeightRuleVO> getRuleByProductCode(String productCode);
}
