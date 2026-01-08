package com.metoak.mes.common.dto;

public class SnSequence {
    private final String prefix;
    private final int start;
    private final int length;

    public SnSequence(String prefix, int start, int length) {
        this.prefix = prefix;
        this.start = start;
        this.length = length;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }
}
