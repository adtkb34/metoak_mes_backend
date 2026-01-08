package com.metoak.mes.service.impl;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.packing.dto.PackingWeightRuleCreateDto;
import com.metoak.mes.packing.dto.PackingWeightRuleUpdateDto;
import com.metoak.mes.packing.entity.PackingWeightRule;
import com.metoak.mes.packing.mapper.PackingWeightRuleMapper;
import com.metoak.mes.packing.service.impl.PackingWeightRuleServiceImpl;
import com.metoak.mes.packing.vo.PackingWeightRuleVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PackingWeightRuleServiceTest {

    @Spy
    @InjectMocks
    private PackingWeightRuleServiceImpl service;

    @Mock
    private PackingWeightRuleMapper baseMapper;

    @Test
    void should_list_rules_successfully() {
        PackingWeightRule rule = PackingWeightRule.builder()
                .id(1L)
                .productCode("P001")
                .specQuantity(10)
                .unitWeight(new BigDecimal("1.2"))
                .tareWeight(new BigDecimal("0.3"))
                .weightTolerance(new BigDecimal("0.1"))
                .build();

        doReturn(List.of(rule)).when(service).list();

        Result<List<PackingWeightRuleVO>> result = service.listRules();

        assertEquals(1, result.getData().size());
        assertEquals("P001", result.getData().get(0).getProductCode());
    }

    @Test
    void should_create_rule_successfully() {
        PackingWeightRuleCreateDto dto = new PackingWeightRuleCreateDto();
        dto.setProductCode("P001");
        dto.setSpecQuantity(10);
        dto.setUnitWeight(new BigDecimal("1.2"));
        dto.setTareWeight(new BigDecimal("0.3"));
        dto.setWeightTolerance(new BigDecimal("0.1"));
        dto.setUsername("tester");

        doReturn(null).when(service).findRuleByProductCode("P001");

        doAnswer(invocation -> {
            PackingWeightRule r = invocation.getArgument(0);
            r.setId(1L);
            return true;
        }).when(service).save(any(PackingWeightRule.class));

        Result<Long> result = service.createRule(dto);

        assertEquals(1L, (long) result.getData());
    }

    @Test
    void should_fail_when_product_already_exists() {
        doReturn(PackingWeightRuleVO.builder().id(1L).build())
                .when(service).findRuleByProductCode("P001");

        PackingWeightRuleCreateDto dto = new PackingWeightRuleCreateDto();
        dto.setProductCode("P001");

        Result<Long> result = service.createRule(dto);

        assertEquals(ResultCodeEnum.PRODUCT_ALREADY_EXISTS.getCode(), result.getCode());
    }

    @Test
    void should_update_rule_successfully() {
        PackingWeightRuleUpdateDto dto = new PackingWeightRuleUpdateDto();
        dto.setId(1L);
        dto.setProductCode("P001");
        dto.setUsername("tester");

        doReturn(true).when(service).updateById(any(PackingWeightRule.class));

        Result<Boolean> result = service.updateRule(dto);

        assertTrue(result.getData());
    }

    @Test
    void should_fail_when_update_id_is_null() {
        PackingWeightRuleUpdateDto dto = new PackingWeightRuleUpdateDto();

        Result<Boolean> result = service.updateRule(dto);

        assertEquals(ResultCodeEnum.RECORD_NOT_FOUND.getCode(), result.getCode());
    }

    @Test
    void should_get_rule_by_product_code_successfully() {
        PackingWeightRuleVO vo = PackingWeightRuleVO.builder()
                .id(1L)
                .productCode("P001")
                .build();

        doReturn(vo).when(service).findRuleByProductCode("P001");

        Result<PackingWeightRuleVO> result = service.getRuleByProductCode("P001");

        assertEquals("P001", result.getData().getProductCode());
    }

    @Test
    void should_fail_when_rule_not_found() {
        doReturn(null).when(service).findRuleByProductCode("P001");

        Result<PackingWeightRuleVO> result = service.getRuleByProductCode("P001");

        assertEquals(ResultCodeEnum.RECORD_NOT_FOUND.getCode(), result.getCode());
    }

}
