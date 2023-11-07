package com.example.demo.Service;

import com.example.demo.Dto.GET.GetRestaurantsResponse;
import com.example.demo.Dto.GET.RestaurantDto;
import com.example.demo.Dto.PATCH.PatchRestaurantRequest;
import com.example.demo.Dto.PUT.PutRestaurantRequest;
import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.Repositories.RestaurantRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {

    List<Restaurant> retrieveAllRestaurants();
    Restaurant getRestaurantByID(UUID restaurant_ID);
    void saveRestaurant(Restaurant restaurant);
    Restaurant createRestaurantRequest(PutRestaurantRequest restaurantRequest);
    Restaurant patchRestaurantRequest(UUID id, PatchRestaurantRequest restaurantRequest);
    boolean deleteRestaurantRequest(UUID id);
    RestaurantDto convertToDto(Restaurant restaurant);
    List<GetRestaurantsResponse> convertToDtoList(List<Restaurant> restaurants);

}
