package com.metoak.mes.k3Cloud.service;

import com.metoak.mes.k3Cloud.dto.BarcodeSaveRequest;
import com.metoak.mes.k3Cloud.enums.BarcodeType;

public interface IKingdeeBarcodeMasterService {

    void saveBarcode(String barcode, BarcodeType barcodeType) throws Exception;

}
