package com.metoak.mes.packing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metoak.mes.packing.entity.PackingWeightRule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PackingWeightRuleMapper extends BaseMapper<PackingWeightRule> {

    String SELECT_LATEST_RULES_SQL = """
            SELECT rule.*
            FROM mo_packing_weight_rule rule
            INNER JOIN (
                SELECT product_code, MAX(id) AS latest_id
                FROM mo_packing_weight_rule
                GROUP BY product_code
            ) latest_rule
            ON rule.product_code = latest_rule.product_code
            AND rule.id = latest_rule.latest_id
            """;

    @Select(SELECT_LATEST_RULES_SQL)
    List<PackingWeightRule> selectLatestRules();
}
