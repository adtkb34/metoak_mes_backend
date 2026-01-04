package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.entity.*;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.mapper.MoParamsBaseMapper;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.params.service.impl.MoParamsBaseServiceImpl;
import com.metoak.mes.params.vo.ParamsContainer;
import com.metoak.mes.params.vo.ParamsNode;
import com.metoak.mes.params.vo.ParamsPayload;
import com.metoak.mes.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MoParamsBaseServiceImplTest {

    @Mock
    private MoParamsBaseMapper moParamsBaseMapper;

    @Mock
    private MoTagInfoServiceImpl moTagInfoService;

    @Mock
    private MoBeamInfoServiceImpl moBeamInfoService;

    @InjectMocks
    private MoParamsBaseServiceImpl moParamsBaseService;

    @Mock
    private IMoProduceOrderService moProduceOrderService;

    @Mock
    private IMoParamsDetailService paramsDetailService;
//
//    @InjectMocks
//    private IMoParamsDetailService paramsDetailService;

    @Mock
    private IMoProcessFlowService moProcessFlowService;

    @Mock
    private IMoWorkstageService moWorkstageService;

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




    @Test
    void should_throw_SN_NOT_FOUND_when_no_beam_and_no_tag() {
        doReturn(Collections.emptyList())
                .when(moBeamInfoService)
                .list(Mockito.any(Wrapper.class));

        doReturn(Collections.emptyList())
                .when(moTagInfoService)
                .list(Mockito.any(Wrapper.class));

        MOException ex = assertThrows(
                MOException.class,
                () -> moParamsBaseService.getOrderId("SN001")
        );

        assertEquals(ResultCodeEnum.SN_NOT_FOUND.getCode(), ex.getCode());
    }

    @Test
    void should_throw_SN_DUPLICATED_when_multiple_records_found() {
        when(moBeamInfoService.list(Mockito.any(Wrapper.class)))
                .thenReturn(List.of(new MoBeamInfo()));
        when(moTagInfoService.list(Mockito.any(Wrapper.class)))
                .thenReturn(List.of(new MoTagInfo()));

        MOException ex = assertThrows(
                MOException.class,
                () -> moParamsBaseService.getOrderId("SN001")
        );

        assertEquals(ResultCodeEnum.SN_DUPLICATED.getCode(), ex.getCode());
    }


    @Test
    void should_throw_WORK_ORDER_NOT_FOUND_when_beam_found_but_no_order() {
        MoBeamInfo beam = new MoBeamInfo();
        beam.setWorkOrderCode("WO001");
        beam.setMaterialCode("MAT001");

        when(moBeamInfoService.list(any(Wrapper.class))).thenReturn(List.of(beam));
        when(moTagInfoService.list(any(Wrapper.class))).thenReturn(Collections.emptyList());
        when(moProduceOrderService.list(any(Wrapper.class))).thenReturn(Collections.emptyList());

        MOException ex = assertThrows(
                MOException.class,
                () -> moParamsBaseService.getOrderId("SN001")
        );

        assertEquals(ResultCodeEnum.WORK_ORDER_NOT_FOUND.getCode(), ex.getCode());
    }

    @Test
    void should_throw_MULTIPLE_WORK_ORDERS_FOUND_when_more_than_one_order() {
        MoBeamInfo beam = new MoBeamInfo();
        beam.setWorkOrderCode("WO001");
        beam.setMaterialCode("MAT001");

        when(moBeamInfoService.list(any(Wrapper.class))).thenReturn(List.of(beam));
        when(moTagInfoService.list(any(Wrapper.class))).thenReturn(Collections.emptyList());
        when(moProduceOrderService.list(any(Wrapper.class)))
                .thenReturn(List.of(new MoProduceOrder(), new MoProduceOrder()));

        MOException ex = assertThrows(
                MOException.class,
                () -> moParamsBaseService.getOrderId("SN001")
        );

        assertEquals(ResultCodeEnum.MULTIPLE_WORK_ORDERS_FOUND.getCode(), ex.getCode());
    }

    @Test
    void should_return_order_id_when_single_order_found() {
        MoBeamInfo beam = new MoBeamInfo();
        beam.setWorkOrderCode("WO001");
        beam.setMaterialCode("MAT001");

        MoProduceOrder order = new MoProduceOrder();
        order.setId(100L);

        when(moBeamInfoService.list(any(Wrapper.class))).thenReturn(List.of(beam));
        when(moTagInfoService.list(any(Wrapper.class))).thenReturn(Collections.emptyList());
        when(moProduceOrderService.list(any(Wrapper.class))).thenReturn(List.of(order));

        Long orderId = moParamsBaseService.getOrderId("SN001");

        assertEquals((long) orderId, 100L);
    }

    @Test
    void should_throw_PARAM_DETAIL_QUERY_FAILED_when_detail_not_found() {
        when(paramsDetailService.getById(1L)).thenReturn(null);

        MOException ex = assertThrows(
                MOException.class,
                () -> moParamsBaseService.buildNodeByDetailId(1L)
        );

        assertEquals(
                ResultCodeEnum.PARAMS_DETAIL_QUERY_BY_ID_FAILED.getCode(),
                ex.getCode()
        );
    }

    @Test
    void should_throw_PARAMS_BASE_QUERY_FAILED_when_base_not_found() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        MoParamsDetail detail = new MoParamsDetail();
        detail.setId(10L);
        detail.setBaseId(20L);

        when(paramsDetailService.getById(10L)).thenReturn(detail);
        doReturn(null).when(spy).getById(20L);

        MOException ex = assertThrows(
                MOException.class,
                () -> spy.buildNodeByDetailId(10L)   // ✅ 必须用 spy
        );

        assertEquals(
                ResultCodeEnum.PARAMS_BASE_QUERY_BY_ID_FAILED.getCode(),
                ex.getCode()
        );
    }


    @Test
    void should_return_ParamsNode_when_detail_and_base_exist() {
        MoParamsBaseServiceImpl spyService = spy(moParamsBaseService);

        MoParamsDetail detail = new MoParamsDetail();
        detail.setId(10L);
        detail.setBaseId(20L);
        detail.setVersionMajor(1);
        detail.setVersionMinor(0);
        detail.setVersionPatch(5);
        detail.setParams(String.valueOf(Map.of("k", "v")));
        detail.setDescription("desc");

        MoParamsBase base = new MoParamsBase();
        base.setId(20L);
        base.setName("Test Params");

        when(paramsDetailService.getById(10L)).thenReturn(detail);
        doReturn(base).when(spyService).getById(20L);

        ParamsNode node = spyService.buildNodeByDetailId(10L);

        assertNotNull(node);
        assertEquals("Test Params", node.getName());
        assertEquals("desc", node.getDesc());
        assertEquals("1.0.5", node.getVersion());
        assertEquals(String.valueOf(Map.of("k", "v")), node.getDetail());
    }

    @Test
    void should_throw_exception_when_process_not_found() {
        when(moProcessFlowService.list(any(LambdaQueryWrapper.class)))
                .thenReturn(Collections.emptyList());

        MOException ex = assertThrows(
                MOException.class,
                () -> moParamsBaseService.getDetailIdByFlowNo("FLOW_001")
        );

        assertEquals(
                ResultCodeEnum.FAILED_TO_FIND_PROCESS_BY_PROCESS_ID.getCode(),
                ex.getCode()
        );
    }

    @Test
    void should_return_paramsDetailId_when_process_found() {
        MoProcessFlow flow = new MoProcessFlow();
        flow.setProcessCode("FLOW_001");
        flow.setParamsDetailId(100L);

        when(moProcessFlowService.list(any(LambdaQueryWrapper.class)))
                .thenReturn(List.of(flow));

        Long detailId = moParamsBaseService.getDetailIdByFlowNo("FLOW_001");

        assertEquals((long) detailId, 100L);
    }

    @Test
    void should_return_empty_list_when_no_flows_found() {
        when(moProcessFlowService.fetchByCode("FLOW_001"))
                .thenReturn(Collections.emptyList());

        List<ParamsNode> result = moParamsBaseService.buildStepParamsNodes("FLOW_001");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void should_skip_when_stage_not_found() {
        MoProcessFlow flow = new MoProcessFlow();
        flow.setStageCode("S1");

        when(moProcessFlowService.fetchByCode("FLOW_001"))
                .thenReturn(List.of(flow));
        when(moWorkstageService.getByCode("S1"))
                .thenReturn(null);

        List<ParamsNode> result = moParamsBaseService.buildStepParamsNodes("FLOW_001");

        assertTrue(result.isEmpty());
    }

    @Test
    void should_skip_when_paramsDetailId_is_null() {
        MoProcessFlow flow = new MoProcessFlow();
        flow.setStageCode("S1");

        MoWorkstage stage = new MoWorkstage();
        stage.setParamsDetailId(null);

        when(moProcessFlowService.fetchByCode("FLOW_001"))
                .thenReturn(List.of(flow));
        when(moWorkstageService.getByCode("S1"))
                .thenReturn(stage);

        List<ParamsNode> result = moParamsBaseService.buildStepParamsNodes("FLOW_001");

        assertTrue(result.isEmpty());
    }

    @Test
    void should_skip_when_buildNode_returns_null() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        MoProcessFlow flow = new MoProcessFlow();
        flow.setStageCode("S1");

        MoWorkstage stage = new MoWorkstage();
        stage.setParamsDetailId(10L);

        when(moProcessFlowService.fetchByCode("FLOW_001"))
                .thenReturn(List.of(flow));
        when(moWorkstageService.getByCode("S1"))
                .thenReturn(stage);

        doReturn(null).when(spy).buildNodeByDetailId(10L);

        List<ParamsNode> result = spy.buildStepParamsNodes("FLOW_001");

        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_step_nodes_when_all_data_valid() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        MoProcessFlow flow = new MoProcessFlow();
        flow.setStageCode("S1");

        MoWorkstage stage = new MoWorkstage();
        stage.setParamsDetailId(10L);

        ParamsNode node = new ParamsNode();
        node.setId("10");

        when(moProcessFlowService.fetchByCode("FLOW_001"))
                .thenReturn(List.of(flow));
        when(moWorkstageService.getByCode("S1"))
                .thenReturn(stage);

        doReturn(node).when(spy).buildNodeByDetailId(10L);

        List<ParamsNode> result = spy.buildStepParamsNodes("FLOW_001");

        assertEquals(1, result.size());
        assertEquals("10", result.get(0).getId());
    }

    private MoProduceOrder mockOrder() {
        MoProduceOrder order = new MoProduceOrder();
        order.setId(1L);
        order.setFlowCode("FLOW_001");
        order.setParamsDetailId(100L);
        return order;
    }

    private ParamsNode mockNode(String id) {
        ParamsNode node = new ParamsNode();
        node.setId(id);
        node.setName("name-" + id);
        return node;
    }

    @Test
    void should_build_full_params_payload_when_all_data_present() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        MoProduceOrder order = mockOrder();

        when(moProduceOrderService.getById(1L)).thenReturn(order);

        doReturn(1L).when(spy).getOrderId("SN001");
        doReturn(200L).when(spy).getDetailIdByFlowNo("FLOW_001");

        doReturn(mockNode("order"))
                .when(spy).buildNodeByDetailId(100L);
        doReturn(mockNode("flow"))
                .when(spy).buildNodeByDetailId(200L);

        doReturn(List.of(mockNode("step1"), mockNode("step2")))
                .when(spy).buildStepParamsNodes("FLOW_001");

        Result<ParamsPayload> result = spy.getBySn("SN001");

//        assertTrue(result.isSuccess());

        ParamsContainer params = result.getData().getParams();
        assertNotNull(params.getOrder());
        assertNotNull(params.getFlow());
        assertEquals(2, params.getSteps().size());
    }

    @Test
    void should_throw_exception_when_order_not_found() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        doReturn(1L).when(spy).getOrderId("SN001");
        when(moProduceOrderService.getById(1L)).thenReturn(null);

        MOException ex = assertThrows(
                MOException.class,
                () -> spy.getBySn("SN001")
        );

        assertEquals(
                ResultCodeEnum.WORK_ORDER_QUERY_BY_ID_FAILED.getCode(),
                ex.getCode()
        );
    }

    @Test
    void should_skip_order_params_when_order_detail_id_is_null() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        MoProduceOrder order = mockOrder();
        order.setParamsDetailId(null);

        doReturn(1L).when(spy).getOrderId("SN001");
        when(moProduceOrderService.getById(1L)).thenReturn(order);

        doReturn(200L).when(spy).getDetailIdByFlowNo("FLOW_001");
        doReturn(mockNode("flow")).when(spy).buildNodeByDetailId(200L);
        doReturn(Collections.emptyList()).when(spy).buildStepParamsNodes("FLOW_001");

        Result<ParamsPayload> result = spy.getBySn("SN001");

        ParamsContainer params = result.getData().getParams();
        assertNull(params.getOrder());
        assertNotNull(params.getFlow());
    }

    @Test
    void should_skip_flow_params_when_flow_detail_id_is_null() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        MoProduceOrder order = mockOrder();

        doReturn(1L).when(spy).getOrderId("SN001");
        when(moProduceOrderService.getById(1L)).thenReturn(order);

        doReturn(null).when(spy).getDetailIdByFlowNo("FLOW_001");
        doReturn(mockNode("order")).when(spy).buildNodeByDetailId(100L);
        doReturn(Collections.emptyList()).when(spy).buildStepParamsNodes("FLOW_001");

        Result<ParamsPayload> result = spy.getBySn("SN001");

        ParamsContainer params = result.getData().getParams();
        assertNotNull(params.getOrder());
        assertNull(params.getFlow());
    }

    @Test
    void should_return_empty_steps_when_no_step_params() {
        MoParamsBaseServiceImpl spy = Mockito.spy(moParamsBaseService);

        MoProduceOrder order = mockOrder();

        doReturn(1L).when(spy).getOrderId("SN001");
        when(moProduceOrderService.getById(1L)).thenReturn(order);

        doReturn(200L).when(spy).getDetailIdByFlowNo("FLOW_001");
        doReturn(mockNode("order")).when(spy).buildNodeByDetailId(100L);
        doReturn(mockNode("flow")).when(spy).buildNodeByDetailId(200L);
        doReturn(Collections.emptyList()).when(spy).buildStepParamsNodes("FLOW_001");

        Result<ParamsPayload> result = spy.getBySn("SN001");

        assertNotNull(result.getData().getParams().getSteps());
        assertTrue(result.getData().getParams().getSteps().isEmpty());
    }


}