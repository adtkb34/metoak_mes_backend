package com.metoak.mes.common.util;

import com.metoak.mes.common.MOException;
import com.metoak.mes.common.dto.SnSequence;
import com.metoak.mes.common.result.ResultCodeEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnParseUtil {

    public static SnSequence parseAndValidateSnSequence(String originSn, int count) {
        Pattern pattern = Pattern.compile("^(.*?)(\\d+)$");
        Matcher matcher = pattern.matcher(originSn);

        if (!matcher.matches()) {
            throw new MOException(ResultCodeEnum.INVALID_SN_FORMAT);
        }

        String prefix = matcher.group(1);
        String numberStr = matcher.group(2);

        int start = Integer.parseInt(numberStr);
        int length = numberStr.length();

        int end = start + count - 1;
        if (String.valueOf(end).length() > length) {
            throw new MOException(ResultCodeEnum.SN_SEQUENCE_EXCEEDS_MAX_LENGTH);
        }

        return new SnSequence(prefix, start, length);
    }

    public static String generateSn(SnSequence seq, int offset) {
        int number = seq.getStart() + offset;
        return seq.getPrefix()
                + String.format("%0" + seq.getLength() + "d", number);
    }

}
