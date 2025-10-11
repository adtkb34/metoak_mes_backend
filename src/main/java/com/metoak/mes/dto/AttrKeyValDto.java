package com.metoak.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttrKeyValDto {

    private String no;

    private String key;

    private String val;

    private String stage;       // AA 阶段

    private String position;    // 位置

    private String frequency;   // mtf

    private String block;       // mtf

    private String idx;       // 同一个料盘不同的序号

    private String spec;

    private String usl;

    private String lsl;

    private String result;

}