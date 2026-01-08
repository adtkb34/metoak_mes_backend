package com.metoak.mes.traceability.contorller;

import com.metoak.mes.MesApplication;
import com.metoak.mes.k3Cloud.service.IK3MaterialService;
import com.metoak.mes.traceability.vo.MaterialBindVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
        MesApplication.class,
        IK3MaterialService.class}
)
@AutoConfigureMockMvc
public class MaterialControllerTest {

    @Autowired
    private IK3MaterialService materialService;


//    @Test
//    public void getBindingsTest() {
//        try {
//            List<MaterialBindVo> materialVos = new ArrayList<>();
//
//            materialVos = materialService.getBindings(null, "SC50SZ02062", null, null);
//            assertNotNull("数据不为NULL", materialVos);
//
//            materialVos = materialService.getBindings(null, "asjdkla", null, null);
//            assertNotNull("即使查询不到数据，返回值也不应为null", materialVos);
//            assertTrue("查询不存在的数据应返回空列表", materialVos.isEmpty());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
