package org.team.cuc.billingsystem.servicesuit.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger的配置类，扫描com.mirocframe.base下的包
 *
 * @author zhengshuaib
 */
@EnableSwagger2
@SpringBootConfiguration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // swagger要扫描的包
                .apis(RequestHandlerSelectors.basePackage("org.team.cuc.billingsystem"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("").description("微服务开发平台")
                .contact(new Contact("联系人", null, "jssd"))
                .version("1.0.0").build();
    }


}
