package com.rpicloud;

import com.rpicloud.models.Product;
import com.rpicloud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.fasterxml.classmate.TypeResolver;

import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;



@SpringBootApplication
@EnableSwagger2
public class ProductServiceApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

	public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        repository.save(new Product("Microservices by Sam Newton", "Book", 23.45, 56.34));
        repository.save(new Product("Cloud Native by Josh Long", "Book", 23.78, 28.89));

        System.out.println("Products found with findAll():");
        System.out.println("-------------------------------");
        repository.findAll().forEach(System.out::println);
        System.out.println();

        // fetch an individual customer
        System.out.println("Product found with findByProductName('Microservices by Sam Newton'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByProductName("Microservices by Sam Newton"));
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
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseEntity.class, WildcardType.class)), typeResolver.resolve(WildcardType.class)));
    }

    @Autowired
    private TypeResolver typeResolver;

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
