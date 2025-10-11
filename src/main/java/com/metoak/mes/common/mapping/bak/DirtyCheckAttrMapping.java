package com.metoak.mes.common.mapping.bak;

import java.util.HashMap;
import java.util.Map;

public class DirtyCheckAttrMapping {

    public static final String STAIN_SIZE_USL = "001";
    public static final String STAIN_COUNT_USL = "002";
    public static final String STAINS = "003";
    public static final String IMG_PATH = "004";

    public static final Map<String, Map<String, String>> NO_TO_FIELD = new HashMap<>();

    static {
        Map<String, String> valMap = new HashMap<>();
        valMap.put("001", "stainSizeUsl");
        valMap.put("002", "stainCountUsl");
        valMap.put("003", "stains");
        valMap.put("004", "imagePath");

        NO_TO_FIELD.put("val", valMap);
    }

}
