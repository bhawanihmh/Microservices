package vazem.microservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vazem.microservice.services.DemoAppService;
import vazem.microservices.api.DemoAppApi;
import vazem.microservices.dtos.DemoModelDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"", "/v1", "/v2", "/v3"})
@RequiredArgsConstructor
public class DemoAppController implements DemoAppApi {

    private final DemoAppService demoAppService;

    @Override
    public DemoModelDTO findByNumber(@PathVariable("number") String number) {
        log.info(String.format("DemoModel.findByNumber(%s)", number));
        return demoAppService.findByNumber(number);
    }

    @Override
    public List findByCustomer(@PathVariable("customer") String customerId) {
        log.info(String.format("DemoModel.findByCustomer(%s)", customerId));
        return demoAppService.findByCustomerId(customerId);
    }

    @Override
    public List findAll() {
        log.info("DemoModel.findAll()");
        return demoAppService.findAll();
    }

    @Override
    public DemoModelDTO add(@RequestBody DemoModelDTO account) {
        log.info(String.format("DemoModel.add(%s)", account));
        return demoAppService.save(account);
    }

    @Override
    public DemoModelDTO update(@RequestBody DemoModelDTO account) {
        log.info(String.format("DemoModel.update(%s)", account));
        return demoAppService.save(account);
    }
}
