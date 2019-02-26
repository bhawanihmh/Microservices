package vazem.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerUIConfig {

    @Autowired
    private ServiceDefinitionsContext definitionContext;

    @Bean
    public RestTemplate configureTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Lazy
    @Primary
    @ConditionalOnBean(ServiceDescriptionUpdater.class)
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider, RestTemplate temp) {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
            resources.clear();
            resources.addAll(definitionContext.getSwaggerDefinitions());
            return resources;
        };
    }

//    @Primary
//    @Component
//    @ConditionalOnBean(ServiceDescriptionUpdater.class)
//    public class DiscoveryClientSwaggerResourcesProvider implements SwaggerResourcesProvider {
//
//        @Override
//        public List<SwaggerResource> get() {
//            return definitionContext.getSwaggerDefinitions();
//        }
//    }

//    {"deepLinking":true,"displayOperationId":false,"defaultModelsExpandDepth":1,
//            "defaultModelExpandDepth":1,"defaultModelRendering":"example",
//            "displayRequestDuration":false,"docExpansion":"none","filter":false,
//            "operationsSorter":"alpha","showExtensions":false,"tagsSorter":"alpha",
//            "validatorUrl":"","apisSorter":"alpha","jsonEditor":false,"showRequestHeaders":false,
//            "supportedSubmitMethods":["get","put","post","delete","options","head","patch","trace"]}

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .validatorUrl("validatorUrl")
                .docExpansion(DocExpansion.LIST)
                .operationsSorter(OperationsSorter.ALPHA)
                .defaultModelRendering(ModelRendering.of("schema"))
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .build();
//        return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
//                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }
}
