package com.aaa.pro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.aaa.pro.staticproperties.RequestUrlProperties.PACKAGE_CONTROLLER_URL;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 8:53
 * @Description
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * @Author zyb
     * @Description 配置api的详细信息以及api扫描的基础包controller
     * @Date 2020/7/13 9:18
     * @Param []
     * @Return springfox.documentation.spring.web.plugins.Docket
     **/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 选择swagger具体生效的接口是什么?(mapper,service,controller)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_CONTROLLER_URL))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @Author zyb
     * @Description 构建了整个项目的一些描述信息
     * @Date 2020/7/13 9:17
     * @Param []
     * @Return springfox.documentation.service.ApiInfo
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测绘管理系统")
                .description("阳江市测绘服务平台")
                .contact(new Contact("zyb", "http://www.zyb.com", "1755103832@qq.com"))
                .version("1.0")
                .build();
    }
}
