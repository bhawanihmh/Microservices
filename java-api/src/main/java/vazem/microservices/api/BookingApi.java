package vazem.microservices.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("booking-service")
public interface BookingApi {

    @RequestMapping(value = "/greeting")
    String greet();

    @RequestMapping(value = "/")
    String home();
}
