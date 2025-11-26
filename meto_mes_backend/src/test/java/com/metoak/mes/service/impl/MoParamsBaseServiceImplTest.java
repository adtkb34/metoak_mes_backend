package com.metoak.mes.service.impl;

import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.mapper.MoParamsBaseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoParamsBaseServiceImplTest {

    @Mock
    private MoParamsBaseMapper moParamsBaseMapper;

    @Test
    public void testSaveParamsBase() {
        MoParamsBase paramsBase = new MoParamsBase();
        paramsBase.setName("测试参数集");
        paramsBase.setType(0);
        paramsBase.setCreatedBy("testUser");

        when(moParamsBaseMapper.insert(any(MoParamsBase.class))).thenReturn(1);

        moParamsBaseMapper.insert(paramsBase);

        verify(moParamsBaseMapper, times(1)).insert(any(MoParamsBase.class));
    }
}