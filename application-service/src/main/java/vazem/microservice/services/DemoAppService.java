package vazem.microservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vazem.microservices.dtos.DemoModelDTO;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DemoAppService {

    public DemoModelDTO findByNumber(String number) {
        return new DemoModelDTO();
    }

    public List findByCustomerId(String customerId) {
        return Collections.emptyList();
    }

    public List findAll() {
        return Collections.emptyList();
    }

    public DemoModelDTO save(DemoModelDTO account) {
        return new DemoModelDTO();
    }
}
