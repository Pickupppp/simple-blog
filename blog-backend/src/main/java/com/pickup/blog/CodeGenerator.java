package com.pickup.blog;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;

/**
 * 代码生成器
 */
public class CodeGenerator {

    public static void main(String[] args) {

        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/blog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                        "root",
                        "root"
                )
                .globalConfig(builder -> {
                    builder.author("pickup") // 设置作者
                            .enableSwagger() // 开启 Swagger
                            .disableOpenDir() // 不打开目录
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java"); // 输出目录
                })
                .packageConfig(builder -> builder.parent("com.pickup.blog")
                        .moduleName("")
                        .xml("mapper")
                        .pathInfo(new HashMap<OutputFile, String>() {{
                            put(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper");
                        }}))
                .strategyConfig(builder -> builder.addInclude("user", "blog")
                        // Controller 层
                        .controllerBuilder()
                        .formatFileName("%sController")
                        .enableFileOverride()
                        .enableRestStyle()
                        // Service 层
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .enableFileOverride()
                        // mapper 层
                        .mapperBuilder()
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper")
                        .enableFileOverride()
                        // entity 层
                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .enableFileOverride())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
