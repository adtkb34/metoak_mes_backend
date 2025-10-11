package com.metoak.mes.controller;

import com.metoak.mes.common.result.Result;
import com.metoak.mes.service.IMoStepAttrKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kevin
 * @since 2025-09-15 17:42:42
 */
@RestController
@RequestMapping("/api/mes/v1/stepAttrKeys")
public class MoStepAttrKeysController {

    @Autowired
    private IMoStepAttrKeysService moStepAttrKeysService;

    @GetMapping
    public Result getByStepTypeNo(@RequestParam String stepTypeNo) {
        return Result.ok(moStepAttrKeysService.getByStepTypeNo(stepTypeNo));
    }


}
