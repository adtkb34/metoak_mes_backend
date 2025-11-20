package com.metoak.mes.common.mapping.bak;

import java.util.HashMap;
import java.util.Map;

public class MtfValueMapper {

    // 编号 → MoLensMtfChecking 字段名
    public static final Map<String, String> NO_TO_FIELD = new HashMap<>();

    static {
        NO_TO_FIELD.put("003", "tMtf");
        NO_TO_FIELD.put("004", "sMtf");
        NO_TO_FIELD.put("005", "tPeak");
        NO_TO_FIELD.put("006", "sPeak");
        NO_TO_FIELD.put("007", "tFs");
        NO_TO_FIELD.put("008", "sFs");
        NO_TO_FIELD.put("009", "astigmatism");
        NO_TO_FIELD.put("010", "fieldCurvatureT");
        NO_TO_FIELD.put("011", "fieldCurvatureS");
        NO_TO_FIELD.put("012", "offsetT");
        NO_TO_FIELD.put("013", "offsetS");
        NO_TO_FIELD.put("014", "uniformityT");
        NO_TO_FIELD.put("015", "uniformityS");
        NO_TO_FIELD.put("016", "depthOfFocus");
        NO_TO_FIELD.put("017", "backFocalDistance");
        NO_TO_FIELD.put("018", "focalLength");
    }

}
