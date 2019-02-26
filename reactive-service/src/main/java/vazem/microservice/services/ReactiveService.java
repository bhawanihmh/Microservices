package vazem.microservice.services;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import vazem.microservices.dtos.DemoModelDTO;

@Repository
public interface ReactiveService extends ReactiveCrudRepository<DemoModelDTO, String> {

    Mono<DemoModelDTO> findByNumber(String number);

    Mono<DemoModelDTO> findByCustomerId(String customerId);
}
