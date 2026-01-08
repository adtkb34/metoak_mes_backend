package com.metoak.mes.packing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
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
    public Result<List<PackingWeightRuleVO>> listRules() {
        List<PackingWeightRuleVO> rules = this.list().stream()
                .map(this::convertToVo)
                .collect(Collectors.toList());
        return Result.ok(rules);
    }

    @Override
    public Result<Long> createRule(PackingWeightRuleCreateDto createDto) {
        PackingWeightRuleVO existingRule = findRuleByProductCode(createDto.getProductCode());
        if (existingRule != null) {
            return Result.fail(ResultCodeEnum.PRODUCT_ALREADY_EXISTS);
        }
        PackingWeightRule rule = PackingWeightRule.builder()
                .productCode(createDto.getProductCode())
                .specQuantity(createDto.getSpecQuantity())
                .unitWeight(createDto.getUnitWeight())
                .tareWeight(createDto.getTareWeight())
                .weightTolerance(createDto.getWeightTolerance())
                .createBy(createDto.getUsername())
                .build();
        this.save(rule);
        return Result.ok(rule.getId());
    }

    @Override
    public Result<Boolean> updateRule(PackingWeightRuleUpdateDto updateDto) {
        if (updateDto.getId() == null) {
            return Result.fail(ResultCodeEnum.RECORD_NOT_FOUND);
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
        boolean updated = this.updateById(rule);
        if (!updated) {
            return Result.fail(ResultCodeEnum.RECORD_NOT_FOUND);
        }
        return Result.ok(true);
    }

    @Override
    public Result<PackingWeightRuleVO> getRuleByProductCode(String productCode) {
        PackingWeightRuleVO rule = findRuleByProductCode(productCode);
        if (rule == null) {
            return Result.fail(ResultCodeEnum.RECORD_NOT_FOUND);
        }
        return Result.ok(rule);
    }

    public PackingWeightRuleVO findRuleByProductCode(String productCode) {
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
