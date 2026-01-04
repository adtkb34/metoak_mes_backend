package com.metoak.mes.params.mapper;

import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.vo.ParamsNode;

public final class ParamsDtoMapper {

    private ParamsDtoMapper() {}

    public static ParamsNode toNode(
            MoParamsBase base,
            MoParamsDetail detail
    ) {
        if (base == null || detail == null) {
            return null;
        }

        ParamsNode node = new ParamsNode();
        node.setId(String.valueOf(detail.getId()));
        node.setName(base.getName());
        node.setDesc(detail.getDescription());
        node.setVersion(buildVersion(
                detail.getVersionMajor(),
                detail.getVersionMinor(),
                detail.getVersionPatch()
        ));
        node.setDetail(detail.getParams());

        return node;
    }

    private static String buildVersion(
            Integer major,
            Integer minor,
            Integer patch
    ) {
        if (major == null || minor == null || patch == null) {
            return null;
        }
        return major + "." + minor + "." + patch;
    }
}
