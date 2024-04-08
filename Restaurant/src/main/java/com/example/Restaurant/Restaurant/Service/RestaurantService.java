package com.example.Restaurant.Restaurant.Service;

import com.example.Restaurant.Restaurant.Dto.GET.GetRestaurantsResponse;
import com.example.Restaurant.Restaurant.Dto.GET.RestaurantDetails;
import com.example.Restaurant.Restaurant.Dto.GET.RestaurantDto;
import com.example.Restaurant.Restaurant.Dto.PATCH.PatchRestaurantRequest;
import com.example.Restaurant.Restaurant.Dto.PUT.PutRestaurantRequest;
import com.example.Restaurant.Restaurant.EntityClass.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RestaurantService {

    List<Restaurant> retrieveAllRestaurants();
    Restaurant getRestaurantByID(UUID restaurant_ID);
    void saveRestaurant(Restaurant restaurant);
    Restaurant createRestaurantRequest(PutRestaurantRequest restaurantRequest);
    Restaurant patchRestaurantRequest(UUID id, PatchRestaurantRequest restaurantRequest);
    boolean deleteRestaurantRequest(UUID id);
    RestaurantDetails convertToDto(Restaurant restaurant);
    GetRestaurantsResponse convertToDtoList(List<Restaurant> restaurants);

    Restaurant getRestaurantByName(String name);

}
