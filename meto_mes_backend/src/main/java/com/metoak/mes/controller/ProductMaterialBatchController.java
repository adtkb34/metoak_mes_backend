package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.SkuMaterialBindingDto;
import com.metoak.mes.entity.MoAssembleInfo;
import com.metoak.mes.entity.MoTagShellInfo;
import com.metoak.mes.entity.ProductMaterialBatch;
import com.metoak.mes.service.IMoAssembleInfoService;
import com.metoak.mes.service.IMoTagShellInfoService;
import com.metoak.mes.service.IProductMaterialBatchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skus")
public class ProductMaterialBatchController {

    @Autowired
    private IMoAssembleInfoService moAssembleInfoService;

    @Autowired
    private IMoTagShellInfoService moTagShellInfoService;

    @Autowired
    private IProductMaterialBatchService productMaterialBatchService;

    @PostMapping("materials")
    @Operation(summary = "物料追溯")
    public Result materialBinding(@RequestParam String productSn) {

        LambdaQueryWrapper<MoTagShellInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MoTagShellInfo::getShellSn, productSn);
        queryWrapper.orderByDesc(MoTagShellInfo::getId);
        MoTagShellInfo moTagShellInfo = moTagShellInfoService.getOne(queryWrapper);
        String beamSn;
        if (moTagShellInfo == null) {
            beamSn = productSn;
        } else {
            beamSn = moTagShellInfo.getCameraSn();
        }
        LambdaQueryWrapper<ProductMaterialBatch> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(ProductMaterialBatch::getBeamSn, beamSn);
        List<ProductMaterialBatch> materials = productMaterialBatchService.list(queryWrapper1);

        LambdaQueryWrapper<MoAssembleInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(MoAssembleInfo::getCameraSn, beamSn);
        queryWrapper2.eq(MoAssembleInfo::getInvalid, 0);
        moAssembleInfoService.list(queryWrapper2).forEach(moAssembleInfo -> {
            materials.add(new ProductMaterialBatch(moAssembleInfo.getCameraSn(), "PCBA", moAssembleInfo.getOperator(), moAssembleInfo.getPcbaCode(), moAssembleInfo.getStationNumber().toString()));
        });

        return Result.ok(materials);
    }

}
