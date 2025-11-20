package com.metoak.mes.common.util;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class StatisticsCalculator {

    public static double calculateAverage(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        double sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum / numbers.size();
    }

    public static double calculateMedian(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        Collections.sort(numbers);
        int size = numbers.size();
        if (size % 2 == 0) {
            // 如果是偶数个数，取中间两个数的平均值
            return (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        } else {
            // 如果是奇数个数，取中间的那个数
            return numbers.get(size / 2);
        }
    }

    public static TreeMap<Integer, Integer> countOccurrences(List<Integer> numbers) {
        TreeMap<Integer, Integer> occurrences = new TreeMap<>();
        for (Integer number : numbers) {
            occurrences.put(number, occurrences.getOrDefault(number, 0) + 1);
        }
        return occurrences;
    }
}