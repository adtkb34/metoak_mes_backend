package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.dto.SnSequence;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.dto.MaterialDto;
import com.metoak.mes.dto.ProductionRecordDto;
import com.metoak.mes.entity.MoMaterialBinding;
import com.metoak.mes.service.IMoMaterialBindingService;
import com.metoak.mes.service.IProcessStepProductionRecordsService;
import com.metoak.mes.traceability.service.impl.MaterialServiceImpl;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.metoak.mes.common.util.SnParseUtil.parseAndValidateSnSequence;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialServiceTest {

    @Autowired
    @InjectMocks
    private ProcessStepProductionRecordsServiceImpl processStepProductionRecordsService;

//    @Autowired
//    private IMoMaterialBindingService moMaterialBindingService;

    @Mock
    private MoMaterialBindingServiceImpl moMaterialBindingService;

    @InjectMocks
    private MaterialServiceImpl service;

//
//    @InjectMocks
//    private MaterialServiceImpl service;

    @Test
    void should_parse_valid_sn_sequence() {
        // given
        String originSn = "ABC0007";
        int count = 3;

        // when
        SnSequence seq = parseAndValidateSnSequence(originSn, count);

        // then
        assertEquals("ABC", seq.getPrefix());
        assertEquals(7, seq.getStart());
        assertEquals(4, seq.getLength());
    }


    @Test
    void should_throw_when_sn_sequence_exceeds_max_length() {
        // given: 0099, count=2 → 0100（溢出）
        String originSn = "ABC99";
        int count = 2;

        // when
        MOException ex = assertThrows(
                MOException.class,
                () -> parseAndValidateSnSequence(originSn, count)
        );

        // then
        assertEquals(
                ResultCodeEnum.SN_SEQUENCE_EXCEEDS_MAX_LENGTH.getCode(),
                ex.getCode()
        );
    }


    @Test
    void should_throw_when_sn_has_no_numeric_suffix() {
        // given
        String originSn = "ABC";
        int count = 1;

        // when
        MOException ex = assertThrows(
                MOException.class,
                () -> parseAndValidateSnSequence(originSn, count)
        );

        // then
        assertEquals(
                ResultCodeEnum.INVALID_SN_FORMAT.getCode(),
                ex.getCode()
        );
    }

    @Test
    void should_add_material_binding_successfully() {
        // given
        ProductionRecordDto dto = new ProductionRecordDto();
        dto.setProductSn("ABC0007");
        dto.setProductBatchNo("BATCH001");
        dto.setCount(3);

        MaterialDto material = new MaterialDto();
        material.setCategory("LENS");
        material.setCategoryNo("001");
        material.setBatchNo("MB001");
        material.setSerialNo("MS001");
        material.setPosition(1);

        dto.setMaterials(List.of(material));

        Long stepResultId = 100L;

        AtomicLong idGen = new AtomicLong(1);
        doAnswer(invocation -> {
            MoMaterialBinding m = (MoMaterialBinding) invocation.getArguments()[0];
            m.setId(idGen.getAndIncrement());
            return true;
        }).when(moMaterialBindingService).save(any(MoMaterialBinding.class));


        // when
        List<Long> ids = processStepProductionRecordsService.add_material_binding(dto, stepResultId);

        // then
        assertEquals(3, ids.size());
        assertEquals(List.of(1L, 2L, 3L), ids);

        ArgumentCaptor<MoMaterialBinding> captor =
                ArgumentCaptor.forClass(MoMaterialBinding.class);
        verify(moMaterialBindingService, times(3)).save(captor.capture());

        List<MoMaterialBinding> saved = captor.getAllValues();

        assertEquals("ABC0007", saved.get(0).getCameraSn());
        assertEquals("ABC0008", saved.get(1).getCameraSn());
        assertEquals("ABC0009", saved.get(2).getCameraSn());

        saved.forEach(m -> {
            assertEquals("BATCH001", m.getCameraBatch());
            assertEquals(stepResultId, m.getMoProcessStepProductionResultId());
            assertEquals((Integer) 1, (Integer) m.getPosition());
        });
    }

    @Test
    void should_delete_by_sn_and_material_code_successfully() throws Exception {
        // given
        List<String> cameraSNs = List.of("ABC0007", "ABC0008", "ABC0009");
        String materialCode = "001";

        when(moMaterialBindingService.remove(any(LambdaQueryWrapper.class)))
                .thenReturn(true);

        // when
        boolean result = service.deleteBySNAndMaterialCode(cameraSNs, materialCode);

        // then
        assertTrue(result);

        ArgumentCaptor<LambdaQueryWrapper<MoMaterialBinding>> captor =
                ArgumentCaptor.forClass(LambdaQueryWrapper.class);
        verify(moMaterialBindingService, times(1)).remove(captor.capture());
    }

    @Test
    void should_delete_binding_info_successfully() throws Exception {
        // given
        MaterialServiceImpl spyService = Mockito.spy(service);

        String productSn = "ABC0007";
        String materialCode = "001";
        int count = 3;

        doReturn(true)
                .when(spyService)
                .deleteBySNAndMaterialCode(
                        eq(List.of("ABC0007", "ABC0008", "ABC0009")),
                        eq(materialCode)
                );

        // when
        boolean result = spyService.deleteBindingInfo(productSn, materialCode, count);

        // then
        assertTrue(result);

        verify(spyService, times(1))
                .deleteBySNAndMaterialCode(
                        eq(List.of("ABC0007", "ABC0008", "ABC0009")),
                        eq(materialCode)
                );
    }

}
