package vazem.microservices.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vazem.microservices.dtos.DemoModelDTO;

import static org.springframework.util.MimeTypeUtils.TEXT_HTML_VALUE;

@ReactiveFeignClient("reactive-service")
@Api(value = "base-api", description = "Reactive CRUD Controller")
public interface ReactiveAppApi {

    @ApiOperation(value = "", httpMethod = "GET", produces = TEXT_HTML_VALUE, response = DemoModelDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Account found"),
            @ApiResponse(code = 209, message = "Invalid account number")
    })
    @GetMapping(value = "/accounts/{number}")
    Mono<DemoModelDTO> findByNumber(@PathVariable("number") String number);

    @GetMapping(value = "/accounts/customer/{customer}")
    Mono<DemoModelDTO> findByCustomer(@PathVariable("customer") String customerId);

    @GetMapping(value = "/accounts")
    Flux<DemoModelDTO> findAll();

    @PostMapping(value = "/accounts")
    Mono<DemoModelDTO> add(@RequestBody DemoModelDTO account);

    @PutMapping(value = "/accounts")
    Mono<DemoModelDTO> update(@RequestBody DemoModelDTO account);
}

