package com.metoak.mes.common.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class GenMbpForMysql {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://172.24.81.104:3306/mo_mes_db?allowMultiQueries=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8",
                        "root", "momeshou")
                .globalConfig(builder -> {
                    builder.author("kevin")     // 设置作者
                            .enableSwagger()    // 开启 swagger 模式
//                            .fileOverride()   // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd HH:mm:ss")
                            .outputDir("C:\\Users\\Administrator\\Downloads\\meto_mes_backend\\src\\main\\java\\");    // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.metoak.mes")      // 设置父包名
//                            .moduleName("model")      // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\Administrator\\Downloads\\meto_mes_backend\\src\\main\\resources\\mapper")); // 设置mapper Xml生成路径
                })
                .strategyConfig(builder -> {
//                    builder
//                            .addTablePrefix("t_");  // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}