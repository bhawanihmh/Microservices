package vazem.microservices.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import vazem.microservices.dtos.DemoModelDTO;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.TEXT_HTML_VALUE;

@FeignClient("application-service")
@Api(value = "base-api", description = "Base CRUD Controller")
public interface DemoAppApi {

    @ApiOperation(value = "", httpMethod = "GET", produces = TEXT_HTML_VALUE, response = DemoModelDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Account found"),
            @ApiResponse(code = 209, message = "Invalid account number")
    })
    @GetMapping(value = "/accounts/{number}")
    DemoModelDTO findByNumber(@PathVariable("number") String number);

    @GetMapping(value = "/accounts/customer/{customer}")
    List findByCustomer(@PathVariable("customer") String customerId);

    @GetMapping(value = "/accounts")
    List findAll();

    @PostMapping(value = "/accounts")
    DemoModelDTO add(@RequestBody DemoModelDTO account);

    @PutMapping(value = "/accounts")
    DemoModelDTO update(@RequestBody DemoModelDTO account);
}
