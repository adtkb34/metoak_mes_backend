package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.SkuMaterialBindingDto;
import com.metoak.mes.entity.MoTagInfo;
import com.metoak.mes.entity.MoTagShellInfo;
import com.metoak.mes.entity.ProductMaterialBatch;
import com.metoak.mes.service.IMoTagShellInfoService;
import com.metoak.mes.service.IProductMaterialBatchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@RestController
@RequestMapping("/api/v1/skusProductionProcesses")
public class MaterialsController {

    @Autowired
    private IMoTagShellInfoService moTagShellInfoService;

    @Autowired
    private IProductMaterialBatchService productMaterialBatchService;

    @PostMapping("materialBinding")
    @Operation(summary = "物料绑定")
    public Result materialBinding(@RequestBody SkuMaterialBindingDto skuMaterialBindingDto) {

        String productSn = skuMaterialBindingDto.getProductSn();
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
        skuMaterialBindingDto.getMaterials().forEach(materialDto -> {
//            ProductMaterialBatch productMaterialBatch = new ProductMaterialBatch(beamSn,
//                                                                    materialDto.getCategory(),
//                                                                    materialDto.getLotNo(),
//                                                                    skuMaterialBindingDto.getPlc(),
//                                                                    skuMaterialBindingDto.getPlcVersion(),
//                                                                    skuMaterialBindingDto.getOperator(),
//                                                                    materialDto.getSerialNo(),
//                                                                    skuMaterialBindingDto.getWorkshopNo(),
//                                                                    skuMaterialBindingDto.getWorkstationNo(),
//                                                                    skuMaterialBindingDto.getProductionProcessNo());

//            productMaterialBatchService.save(productMaterialBatch);
        });

        return Result.ok();
    }

}
