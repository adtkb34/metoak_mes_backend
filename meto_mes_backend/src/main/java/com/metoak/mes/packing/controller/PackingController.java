package com.metoak.mes.packing.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.k3Cloud.service.IBarcodePackageService;
import com.metoak.mes.packing.dto.BarcodePackageSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mes/v1/packing")
@Validated
@Tag(name = "装箱")
public class PackingController {

    private static final String SAVE_BARCODE_PACKAGE_OPERATION = "保存装箱条码信息";

    @Autowired
    private IBarcodePackageService barcodePackageService;

    @PostMapping("/barcode-packages")
    @Operation(summary = SAVE_BARCODE_PACKAGE_OPERATION)
    public Result<Void> saveBarcodePackage(@RequestBody @Valid BarcodePackageSaveDto saveDto) throws Exception {
        barcodePackageService.saveBarcodePackage(saveDto.getPackingCode(), saveDto.getProductCode());
        return Result.ok();
    }
}
