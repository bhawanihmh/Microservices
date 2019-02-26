package vazem.microservices.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.util.MimeTypeUtils.TEXT_HTML_VALUE;

@FeignClient("booking-service")
@Api(value = "booking-api", description = "Booking CRUD Controller")
public interface BookingApi {

    @RequestMapping(value = "/booking/greeting")
    @ApiOperation(value = "", httpMethod = "GET", produces = TEXT_HTML_VALUE, response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Greeting"),
            @ApiResponse(code = 209, message = "Person not mentioned")
    })
    String greet();

    @RequestMapping(value = "/booking")
    @ApiOperation("")
    String home();
}
