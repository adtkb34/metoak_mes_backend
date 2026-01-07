package com.metoak.mes.common.util;

import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.ResultCodeEnum;

public class ProductionRecordValidator {

    public static Integer parseIntOrThrow(
            String value,
            ResultCodeEnum errorCode
    ) {
        if (value == null || value.trim().isEmpty()) {
            throw new MOException(errorCode);
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new MOException(errorCode);
        }
    }
}
