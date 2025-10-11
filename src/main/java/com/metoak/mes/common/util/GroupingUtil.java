package com.metoak.mes.common.util;

import com.metoak.mes.dto.AttrKeyValDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupingUtil {

    public static List<List<AttrKeyValDto>> group(List<AttrKeyValDto> list) {
        // 使用 Map 来存储分组结果
        Map<String, List<AttrKeyValDto>> groupedMap = new HashMap<>();

        // 遍历列表，根据 stage 和 position 分组
        for (AttrKeyValDto dto : list) {
            String key = dto.getStage() + "-" + dto.getPosition() + "-" + dto.getFrequency() + "-" + dto.getBlock() + "-" + dto.getIdx();
            groupedMap.computeIfAbsent(key, k -> new ArrayList<>()).add(dto);
        }

        // 将分组结果转换为 List<List<AttrKeyValDto>>
        List<List<AttrKeyValDto>> result = new ArrayList<>(groupedMap.values());

        return result;
    }

}