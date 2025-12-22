package com.metoak.mes.k3Cloud.controller;


import com.metoak.mes.common.result.Result;
import com.metoak.mes.k3Cloud.service.IMaterialService;
import com.metoak.mes.k3Cloud.service.IProdcutionReportAppService;
import com.metoak.mes.traceability.vo.MaterialVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/productionReportApp")
public class ProductionReportAppController {


    @Autowired
    private IProdcutionReportAppService prodcutionReportAppService;

    @GetMapping("/insertSn")
    public Result<Boolean> insertSn() throws Exception {
        return Result.ok(prodcutionReportAppService.insertSn());
    }

}
