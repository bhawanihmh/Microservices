package vazem.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class RoutingServiceApplication {

	private static String SERVICE_PATTERN = "(?<name>^.+)-(?<version>v.+$)";
	private static String ROUTE_PATTERN = "${version}/${name}";

	public static void main(String[] args) {
		SpringApplication.run(RoutingServiceApplication.class, args);
	}

	/**
	 * A serviceId of booking-v1 is mapped to route /v1/booking/**.
	 * Any regular expression is accepted, but all named groups must be present in both servicePattern and routePattern.
	 * If servicePattern does not match a serviceId, the default behavior is used.
	 * In the preceding example, a serviceId of booking is mapped to the "/booking/**" route (with no version detected).
	 * This feature is disabled by default and only applies to discovered services.
	 */
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper(SERVICE_PATTERN, ROUTE_PATTERN);
	}
}
