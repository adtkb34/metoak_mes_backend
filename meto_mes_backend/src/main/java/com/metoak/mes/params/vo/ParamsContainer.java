package com.metoak.mes.params.vo;

import lombok.Data;
import java.util.List;

@Data
public class ParamsContainer {

    private ParamsNode order;

    private ParamsNode flow;

    private List<ParamsNode> steps;
}
