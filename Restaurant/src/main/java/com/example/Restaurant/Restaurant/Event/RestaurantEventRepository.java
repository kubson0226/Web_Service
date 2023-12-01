package com.example.Restaurant.Restaurant.Event;

import com.example.Restaurant.Restaurant.Dto.PUT.PutRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class RestaurantEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public RestaurantEventRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void delete(UUID id) {
        restTemplate.delete("http://localhost:8081/restaurants/{id}", id);
    }

    public void create(PutRestaurantRequest restaurantRequest) {
        restTemplate.postForLocation("http://localhost:8081/restaurants", restaurantRequest);
    }

}
