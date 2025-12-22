package com.metoak.mes.params.service;

import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.ParamsDetailDto;
import com.metoak.mes.params.entity.ParamsUploadRequest;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParamsDetailServiceTest {

    @Autowired
    private IMoParamsDetailService paramsDetailService;

    @Autowired
    private IMoParamsBaseService paramsBaseService;

//    @Test
//    void uploadParamsShouldPersistData() {
//        ParamsUploadRequest request = new ParamsUploadRequest();
//        request.setName("TestParams");
//        request.setType(1);
//        request.setDescription("first version");
//        request.setParams("{\"key\": \"value1\"}");
//        request.setUsername("tester");
//
//        // 修改：方法现在返回 Result<Long>
//        Result<Long> result = paramsDetailService.uploadParams(request);
//
//        // 验证返回结果
//        assertEquals(0, result.getCode());
//        assertNotNull(result.getData());
//
//        // 修改：获取返回的ID而不是整个DTO
//        Long detailId = result.getData();
//        assertNotNull(detailId);
//
//        // 通过ID查询详细记录
//        MoParamsDetail savedDetail = paramsDetailService.getById(detailId);
//        assertNotNull(savedDetail, "Detail entry should be created");
//        assertEquals(request.getDescription(), savedDetail.getDescription());
//        assertEquals(request.getParams(), savedDetail.getParams());
//        assertEquals(1, savedDetail.getVersionMajor());
//        assertEquals(0, savedDetail.getVersionMinor());
//        assertEquals(0, savedDetail.getVersionPatch());
//
//        // 修改：通过 savedDetail 获取 baseId 而不是 detailDto
//        MoParamsBase savedBase = paramsBaseService.getById(savedDetail.getBaseId());
//        assertNotNull(savedBase, "Base entry should be created");
//        assertEquals(request.getName(), savedBase.getName());
//    }

}
