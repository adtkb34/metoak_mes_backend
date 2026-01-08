package com.metoak.mes.k3Cloud.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.metoak.mes.k3Cloud.service.IProdcutionReportAppService;
import org.springframework.stereotype.Service;

@Service
public class ProductionReportAppServiceImpl implements IProdcutionReportAppService {

    private K3CloudApi api;

    @Override
    public boolean insertSn() throws Exception  {
        Integer fid = 102625;
        Integer entryId = 102663;
        String FSerialNo = "SN5";
        api = new K3CloudApi();
        // ========== root ==========
        JsonObject root = new JsonObject();

        root.add("NeedUpDateFields", new JsonArray());
        root.add("NeedReturnFields", new JsonArray());
        root.addProperty("IsDeleteEntry", "false");

        // ========== Model ==========
        JsonObject model = new JsonObject();
        model.addProperty("FID", fid);

        // ========== FEntity ==========
        JsonArray fEntityArray = new JsonArray();
        JsonObject fEntity = new JsonObject();
        fEntity.addProperty("FEntryID", entryId);

        // ========== FSerialSubEntity ==========
        JsonArray serialArray = new JsonArray();
        JsonObject snObj = new JsonObject();
        snObj.addProperty("FSerialNo", FSerialNo);
        serialArray.add(snObj);

        fEntity.add("FSerialSubEntity", serialArray);
        fEntityArray.add(fEntity);

        model.add("FEntity", fEntityArray);
        root.add("Model", model);

        // ========== 调用保存 ==========
        String reqJson = root.toString();
        System.out.println("Save Request = " + reqJson);

        // PRD_MORPT = 生产汇报单
        System.out.println(api.save("PRD_MORPT", reqJson));
        return true;

    }

}
