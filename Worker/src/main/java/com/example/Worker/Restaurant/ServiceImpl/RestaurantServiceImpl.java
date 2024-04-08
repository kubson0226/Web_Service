package com.example.Worker.Restaurant.ServiceImpl;

import com.example.Worker.Restaurant.Dto.RestaurantDto;
import com.example.Worker.Restaurant.EntityClass.Restaurant;
import com.example.Worker.Restaurant.Dto.PutRestaurantRequest;
import com.example.Worker.Restaurant.Repository.RestaurantRepository;
import com.example.Worker.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurantByID(UUID restaurant_ID) {
        return restaurantRepository.findById(restaurant_ID).orElse(null);
    }

    public Restaurant createRestaurantRequest(PutRestaurantRequest restaurantRequest) {
        Restaurant newRestaurant = Restaurant.builder().build();
        newRestaurant.setID(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"));
        newRestaurant.setName(restaurantRequest.getName());

        return restaurantRepository.save(newRestaurant);
    }

    public boolean deleteRestaurantRequest(UUID id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElse(null);

        if(existingRestaurant != null) {
            restaurantRepository.delete(existingRestaurant);
            return true;
        }
        return false;
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public RestaurantDto convertToDto(Restaurant restaurant) {

        RestaurantDto convertedRestaurant = new RestaurantDto();

        convertedRestaurant.setId(restaurant.getID());
        convertedRestaurant.setName(restaurant.getName());

        return convertedRestaurant;
    }

}
