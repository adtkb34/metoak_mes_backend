package com.metoak.mes.k3Cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.entity.MoPackingInfo;
import com.metoak.mes.k3Cloud.enums.BarcodeType;
import com.metoak.mes.k3Cloud.service.IBarcodePackageService;
import com.metoak.mes.k3Cloud.service.IKingdeeBarcodeMasterService;
import com.metoak.mes.service.IMoPackingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarcodePackageServiceImpl implements IBarcodePackageService {

    private K3CloudApi api;

    @Autowired
    private IMoPackingInfoService moPackingInfoService;

    @Autowired
    private IKingdeeBarcodeMasterService kingdeeBarcodeMasterService;


    @Override
    public void saveBarcodePackage(String packingCode) throws Exception {
        LambdaQueryWrapper<MoPackingInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoPackingInfo::getPackingCode, packingCode);
        List<MoPackingInfo> list = moPackingInfoService.list(wrapper);
        kingdeeBarcodeMasterService.saveBarcode(packingCode, BarcodeType.PACKING);
        list.forEach(o ->
        {
            try {
                kingdeeBarcodeMasterService.saveBarcode(o.getCameraSn(), BarcodeType.PRODUCT);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        api = new K3CloudApi();

        List<List<Object>> lists = api.executeBillQuery("{\n" +
                "  \"FormId\": \"UN_Packaging\",\n" +
                "  \"FieldKeys\": \"Fpackaging\",\n" +
                "  \"FilterString\": \"Fpackaging = '" + packingCode + "'\",\n" +
                "}"
        );
        if (!lists.isEmpty()) return;
        // ========== root ==========
        JsonObject root = new JsonObject();

        // ========== Model ==========
        JsonObject model = new JsonObject();
        model.addProperty("Fpackaging", packingCode);

        // ========== FEntity ==========
        JsonArray fEntityArray = new JsonArray();


        list.forEach(o -> {
            JsonObject row = new JsonObject();
            row.addProperty("FEntryBarCode", o.getCameraSn());
            fEntityArray.add(row);
        });

        model.add("FEntity", fEntityArray);
        root.add("Model", model);

        // ========== 调用保存 ==========
        String reqJson = root.toString();
        String resp = api.save("UN_Packaging", reqJson);
        JsonObject respJson = JsonParser.parseString(resp).getAsJsonObject();

        JsonObject responseStatus = respJson
                .getAsJsonObject("Result")
                .getAsJsonObject("ResponseStatus");

        boolean success = responseStatus.get("IsSuccess").getAsBoolean();
        if (!success) throw new MOException(ResultCodeEnum.FAILED_TO_SAVE_PACKING_RELATION);
    }

}
