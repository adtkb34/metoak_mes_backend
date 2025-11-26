package com.metoak.mes.service.impl;

import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.mapper.MoParamsDetailMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoParamsDetailServiceImplTest {

    @Mock
    private MoParamsDetailMapper moParamsDetailMapper;

    @Test
    public void testSaveParamsDetail() {
        MoParamsDetail paramsDetail = new MoParamsDetail();
        paramsDetail.setBaseId(1L);
        paramsDetail.setDescription("测试版本说明");
        paramsDetail.setVersionMajor(1);
        paramsDetail.setVersionMinor(0);
        paramsDetail.setVersionPatch(0);
        paramsDetail.setParams("{\"param1\": \"value1\"}");
        paramsDetail.setIsActive(1);
        paramsDetail.setCreatedBy("testUser");

        when(moParamsDetailMapper.insert(any(MoParamsDetail.class))).thenReturn(1);

        moParamsDetailMapper.insert(paramsDetail);

        verify(moParamsDetailMapper, times(1)).insert(any(MoParamsDetail.class));
    }
}