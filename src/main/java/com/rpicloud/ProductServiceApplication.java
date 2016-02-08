package com.rpicloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class ProductServiceApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
	}

    @Autowired
    public void setEnvironment(Environment e){
        System.out.println(e.getProperty("configuration.projectName"));
    }

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                //.paths(regex("/products.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Product-service documentation")
                .description("This is the documentation of the product-service")
                .contact("Kasper Nissen")
                .license("Apache License Version 2.0")
                .version("1.0")
                .build();
    }
}

@RestController
@RefreshScope
class ProjectNameRestController {
    @Value("${configuration.projectName}")
    String projectName;

    @RequestMapping("/project-name")
    String projectName(){
        return this.projectName;
    }
}
