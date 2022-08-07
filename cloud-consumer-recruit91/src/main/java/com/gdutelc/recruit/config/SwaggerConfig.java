package com.gdutelc.recruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger API 文档配置
 *
 * @author gregPerlinLi
 * @date 2022-08-07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger Docket 配置
     * @param environment 环境变量
     * @return Docket
     */
    @Bean
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(profiles);

        List<Parameter> parameters = new ArrayList<>();

        parameters.add(new ParameterBuilder().name("token")
                                                .description("登录凭据")
                                                .modelRef(new ModelRef("string"))
                                                .parameterType("header")
                                                .defaultValue("default value")
                                                .hidden(true)
                                                .required(false)
                                                .build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(webApiInfo())
                .groupName("recruit")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gdutelc.recruit.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder().title("GDUTELC招新系统")
                                    .description("这里是广工电协的招新系统API文档")
                                    .version("v1.0")
                                    .contact(new Contact("gregPerlinLi", "https://github.com/gregPerlinLi", "lihaolin13@outlook.com"))
                                    .contact(new Contact("TufSolareyes", "https://github.com/TUFSolareyes", "1378239869@qq.com"))
                                    .contact(new Contact("Cherry-Jerry", "https://github.com/Cherry-Jerry", "cherryjerry626@gmail.com"))
                                    .license("招新系统API文档")
                                    .licenseUrl("https://github.com/gregPerlinLi/elc-recruit-system")
                                    .build();
    }
}
