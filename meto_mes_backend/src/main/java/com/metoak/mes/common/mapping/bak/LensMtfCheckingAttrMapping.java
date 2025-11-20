package com.metoak.mes.common.mapping.bak;

import java.util.HashMap;
import java.util.Map;

public class LensMtfCheckingAttrMapping {

    public static final String T_MTF = "003";
    public static final String S_MTF = "004";
    public static final String T_PEAK = "005";
    public static final String S_PEAK = "006";
    public static final String T_FS = "007";
    public static final String S_FS = "008";
    public static final String ASTIGMATISM = "009";
    public static final String FIELD_CURVATURE_T = "010";
    public static final String FIELD_CURVATURE_S = "011";
    public static final String OFFSET_T = "012";
    public static final String OFFSET_S = "013";
    public static final String UNIFORMITY_T = "014";
    public static final String UNIFORMITY_S = "015";
    public static final String DEPTH_OF_FOCUS = "016";
    public static final String BACK_FOCAL_DISTANCE = "017";
    public static final String FOCAL_LENGTH = "018";

    public static final Map<String, Map<String, String>> NO_TO_FIELD = new HashMap<>();

    static {
        Map<String, String> valMapping = new HashMap<>();
        valMapping.put("003", "tMtf");
        valMapping.put("004", "sMtf");
        valMapping.put("005", "tPeak");
        valMapping.put("006", "sPeak");
        valMapping.put("007", "tFs");
        valMapping.put("008", "sFs");
        valMapping.put("009", "astigmatism");
        valMapping.put("010", "fieldCurvatureT");
        valMapping.put("011", "fieldCurvatureS");
        valMapping.put("012", "offsetT");
        valMapping.put("013", "offsetS");
        valMapping.put("014", "uniformityT");
        valMapping.put("015", "uniformityS");
        valMapping.put("016", "depthOfFocus");
        valMapping.put("017", "backFocalDistance");
        valMapping.put("018", "focalLength");

        NO_TO_FIELD.put("val", valMapping);
    }

}
