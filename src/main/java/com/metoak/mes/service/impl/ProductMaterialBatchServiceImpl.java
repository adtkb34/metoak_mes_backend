package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.CalibresultLeftCenterOffsetOfXAndYDto;
import com.metoak.mes.entity.Calibresult;
import com.metoak.mes.entity.ProductMaterialBatch;
import com.metoak.mes.mapper.CalibresultMapper;
import com.metoak.mes.mapper.ProductMaterialBatchMapper;
import com.metoak.mes.service.ICalibresultService;
import com.metoak.mes.service.IProductMaterialBatchService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Data
@Service
public class ProductMaterialBatchServiceImpl extends ServiceImpl<ProductMaterialBatchMapper, ProductMaterialBatch> implements IProductMaterialBatchService {

//    @Override
//    public Long add(ProductMaterialBatch productMaterialBatch) {
//        productMaterialBatch.setId(null);
//        save(productMaterialBatch);
//        return productMaterialBatch.getId();
//    }
//
//    @Override
//    public List<ProductMaterialBatch> moList(String productSn) {
//        LambdaQueryWrapper<ProductMaterialBatch> queryWrapper = new LambdaQueryWrapper<ProductMaterialBatch>().
//                eq(ProductMaterialBatch::getProductSn, productSn);
//        return list(queryWrapper);
//    }
}
