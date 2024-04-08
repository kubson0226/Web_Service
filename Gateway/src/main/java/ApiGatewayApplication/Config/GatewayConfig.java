package ApiGatewayApplication.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                                     @Value("${isa.restaurant.url}") String restaurantUrl,
                                     @Value("${isa.worker.url}") String workerUrl,
                                     @Value("${isa.gateway.host}") String host) {
        return builder.routes()
                .route("restaurants_route", r -> r
                        .path("/restaurants/**")
                        .uri(restaurantUrl))
                .route("workers_route", r -> r
                        .path("/workers/**")
                        .uri(workerUrl))
                .route("restaurant_id_route", r -> r
                        .path("/restaurants/{id}")
                        .uri(restaurantUrl))
                .route("worker_id_route", r -> r
                        .path("/workers/{id}")
                        .uri(workerUrl))
                .route("fallback_route", r -> r
                        .path("/**")
                        .filters(f -> f.rewritePath("/api(?<remaining>.*)", "/$\\{remaining}"))
                        .uri(host))
                .build();
    }
}
