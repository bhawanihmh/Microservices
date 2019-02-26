package vazem.microservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vazem.microservice.services.ReactiveService;
import vazem.microservices.api.ReactiveAppApi;
import vazem.microservices.dtos.DemoModelDTO;

@Slf4j
@RestController
@RequestMapping({"", "/v1", "/v2", "/v3"})
@RequiredArgsConstructor
@ReactiveFeignClient
public class ReactiveController implements ReactiveAppApi {

    private final ReactiveService reactiveService;

    @Override
    public Mono<DemoModelDTO> findByNumber(@PathVariable("number") String number) {
        log.info(String.format("DemoModel.findByNumber(%s)", number));
        return reactiveService.findByNumber(number);
    }

    @Override
    public Mono<DemoModelDTO> findByCustomer(@PathVariable("customer") String customerId) {
        log.info(String.format("DemoModel.findByCustomer(%s)", customerId));
        return reactiveService.findByCustomerId(customerId);
    }

    @Override
    public Flux<DemoModelDTO> findAll() {
        log.info("DemoModel.findAll()");
        return reactiveService.findAll();
    }

    @Override
    public Mono<DemoModelDTO> add(@RequestBody DemoModelDTO account) {
        log.info(String.format("DemoModel.add(%s)", account));
        return reactiveService.save(account);
    }

    @Override
    public Mono<DemoModelDTO> update(@RequestBody DemoModelDTO account) {
        log.info(String.format("DemoModel.update(%s)", account));
        return reactiveService.save(account);
    }
}
