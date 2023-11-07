package com.example.demo.Service;

import com.example.demo.Dto.GET.RestaurantDto;
import com.example.demo.Dto.GET.GetRestaurantsResponse;
import com.example.demo.Dto.PATCH.PatchRestaurantRequest;
import com.example.demo.Dto.PUT.PutRestaurantRequest;
import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.Repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> retrieveAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantByID(UUID restaurant_ID) {
        return restaurantRepository.findById(restaurant_ID).orElse(null);
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public Restaurant createRestaurantRequest(PutRestaurantRequest restaurantRequest) {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setID(UUID.randomUUID());
        newRestaurant.setName(restaurantRequest.getName());
        newRestaurant.setNumberOfSits(restaurantRequest.getNumberOfSits());

        return restaurantRepository.save(newRestaurant);
    }

    public Restaurant patchRestaurantRequest(UUID id, PatchRestaurantRequest restaurantRequest) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElse(null);

        if(existingRestaurant != null) {
            existingRestaurant.setName(restaurantRequest.getName());
            existingRestaurant.setNumberOfSits(restaurantRequest.getNumberOfSits());
            return restaurantRepository.save(existingRestaurant);
        }
        return null;
    }

    public boolean deleteRestaurantRequest(UUID id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElse(null);

        if(existingRestaurant != null) {
            restaurantRepository.delete(existingRestaurant);
            return true;
        }
        return false;
    }

    public RestaurantDto convertToDto(Restaurant restaurant) {

        RestaurantDto convertedRestaurant = new RestaurantDto();

        convertedRestaurant.setId(restaurant.getID());
        convertedRestaurant.setName(restaurant.getName());
        convertedRestaurant.setNumberOfSits(restaurant.getNumberOfSits());

        return convertedRestaurant;
    }

    public List<GetRestaurantsResponse> convertToDtoList(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> {
                    GetRestaurantsResponse newRestaurant = new GetRestaurantsResponse();
                    newRestaurant.setName(restaurant.getName());
                    newRestaurant.setId(restaurant.getID());
                    return newRestaurant;
                })
                .collect(Collectors.toList());
    }

}
