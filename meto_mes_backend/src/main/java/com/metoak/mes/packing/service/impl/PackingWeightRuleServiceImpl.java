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
                .productModel(createDto.getProductModel())
                .fullBoxQuantity(createDto.getFullBoxQuantity())
                .singleProductWeight(createDto.getSingleProductWeight())
                .fullBoxPackageWeight(createDto.getFullBoxPackageWeight())
                .allowedDeviation(createDto.getAllowedDeviation())
                .unit(createDto.getUnit())
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
                .productModel(updateDto.getProductModel())
                .fullBoxQuantity(updateDto.getFullBoxQuantity())
                .singleProductWeight(updateDto.getSingleProductWeight())
                .fullBoxPackageWeight(updateDto.getFullBoxPackageWeight())
                .allowedDeviation(updateDto.getAllowedDeviation())
                .unit(updateDto.getUnit())
                .build();
        return this.updateById(rule);
    }

    private PackingWeightRuleVO convertToVo(PackingWeightRule rule) {
        return PackingWeightRuleVO.builder()
                .id(rule.getId())
                .productModel(rule.getProductModel())
                .fullBoxQuantity(rule.getFullBoxQuantity())
                .singleProductWeight(rule.getSingleProductWeight())
                .fullBoxPackageWeight(rule.getFullBoxPackageWeight())
                .allowedDeviation(rule.getAllowedDeviation())
                .unit(rule.getUnit())
                .createdAt(rule.getCreatedAt())
                .updatedAt(rule.getUpdatedAt())
                .build();
    }
}
