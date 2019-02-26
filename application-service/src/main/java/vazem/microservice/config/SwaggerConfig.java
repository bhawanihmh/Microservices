package vazem.microservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.includePaths:/v\\d/.*}")
    private String swaggerIncludePaths;

    @Value("${swagger.adminGroupName:admin}")
    private String swaggerAdminGroupName;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Application API Docs")
                .description("Centralized API documentation with swagger 2")
                .termsOfServiceUrl("")
                .version("1.0.0-SNAPSHOT")
                .contact(new Contact("Mayuresh Vaze", "https://github.com/mavaze", "mayur_vaze@yahoo.co.in"))
                .build();
    }

    @Bean
    Docket swaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerAdminGroupName)
                .select()
//                .apis(withAdminApiOperationTag())
                .apis(RequestHandlerSelectors.basePackage("vazem.microservice"))
                .paths(path -> path.matches(swaggerIncludePaths))// apis and paths is an and operation
                .build()
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
//                .directModelSubstitute(org.joda.time.DateTime.class, String.class)
//                .alternateTypeRules(getPatchRule())
                .apiInfo(apiInfo());
    }

//    private AlternateTypeRule getPatchRule() {
//        TypeResolver typeResolver = new TypeResolver();
//
//        return AlternateTypeRules.newRule(
//                //replace Patch
//                Patch.class,
//                //with List<PatchOperation>
//                typeResolver.resolve(List.class, PatchOperation.class));
//    }
//
//    private Predicate<RequestHandler> withAdminApiOperationTag() {
//        return input -> ofNullable(input)
//                .map(i -> i.findAnnotation(ApiOperation.class))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .map(apiOperation -> Arrays.asList(apiOperation.tags()).contains(swaggerAdminGroupName))
//                .orElse(false);
//    }
}
