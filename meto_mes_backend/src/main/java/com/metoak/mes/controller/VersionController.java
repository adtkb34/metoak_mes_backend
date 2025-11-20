package com.metoak.mes.controller;

import com.metoak.mes.common.ResultBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mes/v1")
@Tag(name = "版本信息")
public class VersionController {

    @Value("${application.version:unknown}")
    private String applicationVersion;

    @GetMapping("/version")
    @Operation(summary = "获取应用版本")
    public ResultBean<Map<String, String>> version() {
        Map<String, String> versionInfo = new HashMap<>();
        versionInfo.put("version", applicationVersion);
        return ResultBean.ok(versionInfo);
    }
}
