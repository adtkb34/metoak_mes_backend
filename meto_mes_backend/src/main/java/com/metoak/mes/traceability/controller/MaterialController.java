package com.metoak.mes.traceability.controller;

import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.k3Cloud.service.IMaterialService;
import com.metoak.mes.traceability.vo.MaterialBindVo;
import com.metoak.mes.traceability.vo.MaterialVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/api/mes/v1/materials")
public class MaterialController {

    @Autowired
    private IMaterialService materialService;

    @GetMapping("/fromWorkOrder")
    @Operation(summary = "从工单获取获取物料列表")
    public Result<List<MaterialVo>> listMaterialsFromWorkOrder() throws Exception {
        return Result.ok(materialService.listMaterialsFromWorkOrder());
    }

    @GetMapping("/{materialCode}/bom")
    @Operation(summary = "通过物料料号获取BOM")
    public Result<List<MaterialVo>> listBomByMaterialCode(@PathVariable String materialCode) throws Exception {
        return Result.ok(materialService.getBomByMaterialCode(materialCode));
    }

    @GetMapping("/{materialCode}/batches")
    @Operation(summary = "通过物料编号获取物料批次号")
    public Result<List<String>> listBatchesByMaterialCode(@PathVariable String materialCode) throws Exception {
        return Result.ok(materialService.getBatchesByMaterialCode(materialCode));
    }

    @DeleteMapping("/deleteBinding")
    @Operation(summary = "根据产品序列号和物料ID删除物料绑定信息")
    public Result<Boolean> deleteBindingInfo(
            @RequestParam String productSn,
            @RequestParam String materialID,
            @RequestParam(defaultValue = "0") Integer count
    ) throws Exception {
        if (productSn == null || materialID == null || count == null) {
            return Result.fail(402,"存在参数为空");
        }
        log.info("删除物料绑定信息 - 产品序列号: {}, 物料ID: {}, 删除数量: {}", productSn, materialID, count);

        // 匹配字符串末尾的数字部分
        Pattern pattern = Pattern.compile("(\\d+)$");
        Matcher matcher = pattern.matcher(productSn);

        if (!matcher.find()) {
            return Result.fail(401,"产品序列号必须以数字结尾");
        }

        // 获取数字部分和前缀
        String numberStr = matcher.group();
        String prefix = productSn.substring(0, matcher.start());
        int originalNumber = Integer.parseInt(numberStr);
        int numberLength = numberStr.length();

        List<String> deletedSnList = new ArrayList<>();

        int  i = 0;
        do{
            String currentProductSn = generateProductSn(prefix, originalNumber, i, numberLength);
            deletedSnList.add(currentProductSn);
            i++;
        } while (i < count);
        System.out.println(deletedSnList);
        boolean isDeleted = materialService.deleteBySNAndMaterialID(deletedSnList, materialID.trim());
        return Result.ok(isDeleted);

    }

    /**
     * 生成产品序列号
     */
    private String generateProductSn(String prefix, int originalNumber, int increment, int numberLength) {
        int currentNumber = originalNumber + increment;

        // 检查位数是否超出
        String currentNumberStr = String.valueOf(currentNumber);
        if (currentNumberStr.length() > numberLength) {
            throw new MOException(ResultCodeEnum.SN_SEQUENCE_EXCEEDS_MAX_LENGTH);
        }

        // 保持相同的数字位数（前导零）
        return prefix + String.format("%0" + numberLength + "d", currentNumber);
    }

    @Operation(summary = "获取所有绑定记录")
    @GetMapping("/materialBindings")
    public Result<List<MaterialBindVo>> getBindings(
            @RequestParam(required = false) String materialCode,
            @RequestParam(required = false) String cameraSn,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime
    ) throws Exception {
        // 验证至少有一个查询条件
        if (materialCode == null && cameraSn == null && startTime == null && endTime == null) {
            return Result.fail(400, "至少需要提供一个查询条件");
        }

        // 验证时间范围合理性
        if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
            return Result.fail(400, "开始时间不能晚于结束时间");
        }

        log.info("{}, {}, {}, {}",  materialCode, cameraSn, startTime, endTime);
        List<MaterialBindVo> materialVos = materialService.getBindings(
                materialCode, cameraSn, startTime, endTime);

        return Result.ok(materialVos);
    }
}
