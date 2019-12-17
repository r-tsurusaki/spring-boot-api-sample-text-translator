package com.translator.gwa.swagger;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Setter
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {

    private String title;
    private String description;
    private String version;

    /**
     * Create swagger docket.
     * http://localhost:8080/swagger-ui.html
     *
     * @return Docket object.
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.translator.gwa"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    /**
     * Create swagger apiInfo.
     *
     * @return ApiInfo object.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.title)
                .description(this.description)
                .version(this.version)
                .build();
    }


}
