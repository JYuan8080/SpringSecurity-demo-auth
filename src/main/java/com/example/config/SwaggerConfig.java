package com.example.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author JYuan
 * @create 2021-08-18 20:44
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket initDocket(Environment env) {

        //设置要暴漏接口文档的配置环境
        // Docket是Swagger的全局配置对象
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select() // 返回Docket中的选择器 ApiSelectorBuilder
                // 此处表示swagger中显示哪些接口，默认是扫描当前类及其子类中的所有Controller。该方法可以调用多次，取得是交集
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 用于筛选映射方法的请求路径。
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置swagger的标题
                .title("权限验证-Demo接口文档")
                // 当前swagger的描述
                .description("技术支持-JYuan")
                //联系人（可以不写）      // 企业名称       // 企业网站地址                    // 企业邮箱
                .contact(new Contact("JYuan", "http://www.baidu.com", "1119897133@qq.com"))
                .version("1.0")
                .build();
    }
}
