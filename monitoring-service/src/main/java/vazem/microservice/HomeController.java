package vazem.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vazem.microservice.config.ServiceDefinitionsContext;

@Controller
public class HomeController {

    @Autowired
    private ServiceDefinitionsContext definitionContext;

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

    @GetMapping("/service/{servicename}")
    public @ResponseBody String getServiceDefinition(@PathVariable("servicename") String serviceName) {
        return definitionContext.getSwaggerDefinition(serviceName);
    }
}
