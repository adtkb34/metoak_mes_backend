package com.metoak.mes.packing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.packing.dto.PackingWeightRuleCreateDto;
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

    private static final String LIMIT_ONE_CLAUSE = "LIMIT 1";

    @Override
    public Result<List<PackingWeightRuleVO>> listRules() {
        List<PackingWeightRuleVO> rules = baseMapper.selectLatestRules().stream()
                .map(this::convertToVo)
                .collect(Collectors.toList());
        return Result.ok(rules);
    }

    @Override
    public Result<Long> createRule(PackingWeightRuleCreateDto createDto) {
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
                .orderByDesc(PackingWeightRule::getCreatedAt)
                .last(LIMIT_ONE_CLAUSE)
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
                .build();
    }
}
