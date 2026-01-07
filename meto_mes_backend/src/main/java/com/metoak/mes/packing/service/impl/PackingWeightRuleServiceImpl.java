package com.metoak.mes.packing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.packing.dto.PackingWeightRuleCreateDto;
import com.metoak.mes.packing.dto.PackingWeightRuleUpdateDto;
import com.metoak.mes.packing.entity.PackingWeightRule;
import com.metoak.mes.packing.mapper.PackingWeightRuleMapper;
import com.metoak.mes.packing.service.PackingWeightRuleService;
import com.metoak.mes.packing.vo.PackingWeightRuleVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackingWeightRuleServiceImpl extends ServiceImpl<PackingWeightRuleMapper, PackingWeightRule>
        implements PackingWeightRuleService {

    @Override
    public List<PackingWeightRuleVO> listRules() {
        return this.list().stream()
                .map(this::convertToVo)
                .collect(Collectors.toList());
    }

    @Override
    public Long createRule(PackingWeightRuleCreateDto createDto) {
        PackingWeightRule rule = PackingWeightRule.builder()
                .productCode(createDto.getProductCode())
                .specQuantity(createDto.getSpecQuantity())
                .unitWeight(createDto.getUnitWeight())
                .tareWeight(createDto.getTareWeight())
                .weightTolerance(createDto.getWeightTolerance())
                .createBy(createDto.getUsername())
                .build();
        this.save(rule);
        return rule.getId();
    }

    @Override
    public boolean updateRule(PackingWeightRuleUpdateDto updateDto) {
        if (updateDto.getId() == null) {
            return false;
        }
        PackingWeightRule rule = PackingWeightRule.builder()
                .id(updateDto.getId())
                .productCode(updateDto.getProductCode())
                .specQuantity(updateDto.getSpecQuantity())
                .unitWeight(updateDto.getUnitWeight())
                .tareWeight(updateDto.getTareWeight())
                .weightTolerance(updateDto.getWeightTolerance())
                .updateBy(updateDto.getUsername())
                .build();
        return this.updateById(rule);
    }

    @Override
    public PackingWeightRuleVO getRuleByProductCode(String productCode) {
        PackingWeightRule rule = this.lambdaQuery()
                .eq(PackingWeightRule::getProductCode, productCode)
                .one();
        if (rule == null) {
            return null;
        }
        return convertToVo(rule);
    }

    private PackingWeightRuleVO convertToVo(PackingWeightRule rule) {
        return PackingWeightRuleVO.builder()
                .id(rule.getId())
                .productCode(rule.getProductCode())
                .specQuantity(rule.getSpecQuantity())
                .unitWeight(rule.getUnitWeight())
                .tareWeight(rule.getTareWeight())
                .weightTolerance(rule.getWeightTolerance())
                .createdAt(rule.getCreatedAt())
                .updatedAt(rule.getUpdatedAt())
                .build();
    }
}
