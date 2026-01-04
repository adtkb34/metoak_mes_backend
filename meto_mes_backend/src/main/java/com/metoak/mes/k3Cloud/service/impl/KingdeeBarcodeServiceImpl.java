package com.metoak.mes.k3Cloud.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.k3Cloud.dto.BarcodeSaveRequest;
import com.metoak.mes.k3Cloud.enums.BarcodeType;
import com.metoak.mes.k3Cloud.service.IKingdeeBarcodeMasterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KingdeeBarcodeServiceImpl implements IKingdeeBarcodeMasterService {

    private K3CloudApi api;

    private final String PACKING_BARCODERULE_NUM = "03";
    private final String PRODUCT_BARCODERULE_NUM = "04";

    @Override
    public void saveBarcode(String barcode, BarcodeType barcodeType) throws Exception {
        api = new K3CloudApi();

        List<List<Object>> lists = api.executeBillQuery("{\n" +
                "  \"FormId\": \"BD_BarCodeMainFile\",\n" +
                "  \"FieldKeys\": \"FBarCode\",\n" +
                "  \"FilterString\": \"FBarCode = '" + barcode + "'\",\n" +
                "}"
        );
        if (!lists.isEmpty()) return;
        // ========== root ==========
        JsonObject root = new JsonObject();

        // ========== Model ==========
        JsonObject model = new JsonObject();
        model.addProperty("FBarCode", barcode);

        // ========== FBarCodeRule ==========
        JsonObject fBarCodeRule = new JsonObject();
        if (barcodeType == BarcodeType.PACKING) {
            fBarCodeRule.addProperty("FNUMBER", PACKING_BARCODERULE_NUM);
        } else if (barcodeType == BarcodeType.PRODUCT) {
            fBarCodeRule.addProperty("FNUMBER", PRODUCT_BARCODERULE_NUM);
        }
        model.add("FBarCodeRule", fBarCodeRule);
        root.add("Model", model);

        // ========== 调用保存 ==========
        String reqJson = root.toString();

        // PRD_MORPT = 生产汇报单
        String resp = api.save("BD_BarCodeMainFile", reqJson);
        JsonObject respJson = JsonParser.parseString(resp).getAsJsonObject();

        JsonObject responseStatus = respJson
                .getAsJsonObject("Result")
                .getAsJsonObject("ResponseStatus");

        boolean success = responseStatus.get("IsSuccess").getAsBoolean();
        if (!success) throw new MOException(ResultCodeEnum.FAILED_TO_SAVE_BARCODE_MASTER);
    }
}

