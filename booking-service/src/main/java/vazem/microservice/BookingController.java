package vazem.microservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vazem.microservice.services.BookingService;
import vazem.microservices.api.BookingApi;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping({"", "/v1"})
@RequiredArgsConstructor
public class BookingController implements BookingApi {

    private final BookingService bookingService;

    @Override
    public String greet() {
        log.info("Access /greeting");

        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");

        Random rand = new Random();
        int randomNum = rand.nextInt(greetings.size());
        return greetings.get(randomNum);
    }

    @Override
    @HystrixCommand
    public String home() {
        log.info("Access /");
        Random rand = new Random();
        return "Hi " + bookingService.square((long) rand.nextInt(25));
    }
}
