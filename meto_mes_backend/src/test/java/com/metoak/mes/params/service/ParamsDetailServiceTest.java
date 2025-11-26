package com.metoak.mes.params.service;

import com.metoak.mes.common.ResultBean;
import com.metoak.mes.dto.ParamsDetailDto;
import com.metoak.mes.dto.ParamsUploadRequest;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParamsDetailServiceTest {

    @Autowired
    private IMoParamsDetailService paramsDetailService;

    @Autowired
    private IMoParamsBaseService paramsBaseService;

    @Test
    void uploadParamsShouldPersistData() {
        ParamsUploadRequest request = new ParamsUploadRequest();
        request.setName("TestParams");
        request.setType(1);
        request.setDescription("first version");
        request.setParams("{\"key\": \"value\"}");
        request.setUsername("tester");

        ResultBean<ParamsDetailDto> result = paramsDetailService.uploadParams(request);

        assertEquals(0, result.getCode());
        assertNotNull(result.getData());
        ParamsDetailDto detailDto = result.getData();
        assertNotNull(detailDto.getId());

        MoParamsBase savedBase = paramsBaseService.getById(detailDto.getBaseId());
        assertNotNull(savedBase, "Base entry should be created");
        assertEquals(request.getName(), savedBase.getName());

        MoParamsDetail savedDetail = paramsDetailService.getById(detailDto.getId());
        assertNotNull(savedDetail, "Detail entry should be created");
        assertEquals(request.getDescription(), savedDetail.getDescription());
        assertEquals(request.getParams(), savedDetail.getParams());
        assertEquals(1, savedDetail.getVersionMajor());
        assertEquals(0, savedDetail.getVersionMinor());
        assertEquals(0, savedDetail.getVersionPatch());
    }
}
