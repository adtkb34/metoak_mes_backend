package com.metoak.mes.packing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metoak.mes.packing.dto.PackingWeightRuleCreateDto;
import com.metoak.mes.packing.dto.PackingWeightRuleUpdateDto;
import com.metoak.mes.packing.entity.PackingWeightRule;
import com.metoak.mes.packing.vo.PackingWeightRuleVO;

import java.util.List;

public interface PackingWeightRuleService extends IService<PackingWeightRule> {

    List<PackingWeightRuleVO> listRules();

    Long createRule(PackingWeightRuleCreateDto createDto);

    boolean updateRule(PackingWeightRuleUpdateDto updateDto);
}
