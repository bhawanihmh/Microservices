package vazem.microservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceDescriptionUpdater {

    private static final String DEFAULT_SWAGGER_URL = "/v2/api-docs?group=admin";
    private static final String KEY_SWAGGER_URL = "swagger_url";

    private final RestTemplate template;
    private final DiscoveryClient discoveryClient;
    private final ServiceDefinitionsContext definitionContext;

    @PostConstruct
//    @Scheduled(fixedDelayString= "${swagger.config.refreshrate}")
    public void refreshSwaggerConfigurations(){
        log.debug("Starting Service Definition Context refresh");

        discoveryClient.getServices().forEach(serviceId -> {
            log.debug("Attempting service definition refresh for Service : {} ", serviceId);
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
            if (serviceInstances == null || serviceInstances.isEmpty()){ //Should not be the case kept for failsafe
                log.info("No instances available for service : {} ",serviceId);
            } else {
                ServiceInstance instance = serviceInstances.get(0);
                String swaggerURL = getSwaggerURL( instance);

                Optional<Map<String, Object>> jsonData = getSwaggerDefinitionForAPI(serviceId, swaggerURL);

                if (jsonData.isPresent()) {
                    final Map<String, Object> stringMap = jsonData.get();
                    stringMap.put("host", "localhost:10100");
                    stringMap.put("basePath", "/api");
                    String content = getJSON(serviceId, stringMap);
                    definitionContext.addServiceDefinition(serviceId, content);
                } else {
                    log.error("Skipping service id : {} Error : Could not get Swagger definition from API ",serviceId);
                }

                log.info("Service Definition Context Refreshed at :  {}",LocalDate.now());
            }
        });
    }

    private String getSwaggerURL( ServiceInstance instance){
        String swaggerURL = instance.getMetadata().get(KEY_SWAGGER_URL);
        return swaggerURL != null ? instance.getUri()+swaggerURL : instance.getUri()+DEFAULT_SWAGGER_URL;
    }

    @SuppressWarnings("unchecked")
    private Optional<Map<String, Object>> getSwaggerDefinitionForAPI(String serviceName, String url){
        log.debug("Accessing the SwaggerDefinition JSON for Service : {} : URL : {} ", serviceName, url);
        try {
            Map<String, Object> jsonData = template.getForObject(url, LinkedHashMap.class);
            return Optional.ofNullable(jsonData);
        } catch (RestClientException ex){
            log.error("Error while getting service definition for service : {} Error : {} ", serviceName, ex.getMessage());
            return Optional.empty();
        }
    }

    public String getJSON(String serviceId, Object jsonData){
        try {
            return new ObjectMapper().writeValueAsString(jsonData);
        } catch (JsonProcessingException e) {
            log.error("Error : {} ", e.getMessage());
            return "";
        }
    }
}
