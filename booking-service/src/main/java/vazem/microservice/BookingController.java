package vazem.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import vazem.microservices.api.BookingApi;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class BookingController implements BookingApi {

    @Override
    public String greet() {
        log.info("Access /greeting");

        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
        Random rand = new Random();

        int randomNum = rand.nextInt(greetings.size());
        return greetings.get(randomNum);
    }

    @Override
    public String home() {
        log.info("Access /");
        return "Hi!";
    }
}
