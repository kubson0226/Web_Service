package com.example.Worker.Restaurant.Service;

import com.example.Worker.Restaurant.Dto.RestaurantDto;
import com.example.Worker.Restaurant.EntityClass.Restaurant;
import com.example.Worker.Restaurant.Dto.PutRestaurantRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface RestaurantService {
    void saveRestaurant(Restaurant restaurant);
    boolean deleteRestaurantRequest(UUID id);
    Restaurant createRestaurantRequest(PutRestaurantRequest restaurantRequest);
    RestaurantDto convertToDto(Restaurant restaurant);
    Restaurant getRestaurantByID(UUID id);
}
