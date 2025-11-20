package com.metoak.mes.common.mapping.bak;

import java.util.HashMap;
import java.util.Map;

public class UvDispensingAttrMapping {
    public static final String GLUE_WIDTH = "001";
    public static final String CIRCLE_CENTER_OFFSET = "003";
    public static final String IMG_PATH = "005";

    public static final Map<String, Map<String, String>> NO_TO_FIELD = new HashMap<>();

    static {
        Map<String, String> valMap = new HashMap<>();
        valMap.put("001", "glueWidth");
        valMap.put("003", "circleCenterOffset");
        valMap.put("005", "imagePath");

        NO_TO_FIELD.put("val", valMap);

        valMap = new HashMap<>();
        valMap.put("001", "glueWidthUsl");
        valMap.put("003", "circleCenterOffsetUsl");

        NO_TO_FIELD.put("usl", valMap);

        valMap = new HashMap<>();
        valMap.put("001", "glueWidthLsl");

        NO_TO_FIELD.put("lsl", valMap);
    }
}
