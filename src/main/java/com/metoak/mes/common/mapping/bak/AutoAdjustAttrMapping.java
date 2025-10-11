package com.metoak.mes.common.mapping.bak;

import java.util.HashMap;
import java.util.Map;

public class AutoAdjustAttrMapping {

    public static final String OC_X_OFFSET = "002";
    public static final String OC_Y_OFFSET = "004";
    public static final String COD_X_OFFSET = "006";
    public static final String COD_Y_OFFSET = "008";
    public static final String MTF_CENTER = "010";
    public static final String MTF_TL = "013";
    public static final String MTF_TR = "016";
    public static final String MTF_BL = "019";
    public static final String MTF_BR = "022";
    public static final String MTF_RANGE_OFFSET = "025";
    public static final String IMG_PATH = "028";
    public static final String AA_CLAW_GRIP_PRESSURE = "029";
    public static final String RELEASE_CLAW_MINUS_UV_CURED_MTF_CENTER_DIFF = "031";
    public static final String RELEASE_CLAW_MINUS_UV_CURED_MTF_TL_DIFF = "032";
    public static final String RELEASE_CLAW_MINUS_UV_CURED_MTF_TR_DIFF = "033";
    public static final String RELEASE_CLAW_MINUS_UV_CURED_MTF_BL_DIFF = "034";
    public static final String RELEASE_CLAW_MINUS_UV_CURED_MTF_BR_DIFF = "035";

    public static final Map<String, Map<String, String>> NO_TO_FIELD = new HashMap<>();

    static {
        Map<String, String> valMap = new HashMap<>();
        valMap.put("002", "ocXOffset");
        valMap.put("004", "ocYOffset");
        valMap.put("006", "codXOffset");
        valMap.put("008", "codYOffset");
        valMap.put("010", "mtfCenterValue");
        valMap.put("013", "mtfLeftupValue");
        valMap.put("016", "mtfRightupValue");
        valMap.put("019", "mtfLeftdownValue");
        valMap.put("022", "mtfRightdownValue");
        valMap.put("025", "mtfRangeOffset");
        valMap.put("028", "imagePath");
        valMap.put("029", "aaClawGripPressure");

        NO_TO_FIELD.put("val", valMap);

        valMap = new HashMap<>();
        valMap.put("010", "mtfCenterLsl");
        valMap.put("013", "mtfLeftupLsl");
        valMap.put("016", "mtfRightupLsl");
        valMap.put("019", "mtfLeftdownLsl");
        valMap.put("022", "mtfRightdownLsl");

        NO_TO_FIELD.put("lsl", valMap);

        valMap = new HashMap<>();
        valMap.put("002", "ocXOffsetUsl");
        valMap.put("004", "ocYOffsetUsl");
        valMap.put("006", "codXOffsetUsl");
        valMap.put("008", "codYOffsetUsl");
        valMap.put("025", "mtfRangeOffsetUsl");
        valMap.put("031", "releaseClawMinusUvCuredMtfCenterDiffUsl");
        valMap.put("032", "releaseClawMinusUvCuredMtfTlDiffUsl");
        valMap.put("033", "releaseClawMinusUvCuredMtfTrDiffUsl");
        valMap.put("034", "releaseClawMinusUvCuredMtfBlDiffUsl");
        valMap.put("035", "releaseClawMinusUvCuredMtfBrDiffUsl");

        NO_TO_FIELD.put("usl", valMap);

        valMap = new HashMap<>();
        valMap.put("029", "aaClawGripPressureSpec");

        NO_TO_FIELD.put("spec", valMap);

    }
}
