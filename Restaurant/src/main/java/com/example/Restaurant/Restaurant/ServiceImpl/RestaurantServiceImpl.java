package com.example.Restaurant.Restaurant.ServiceImpl;

import com.example.Restaurant.Restaurant.Dto.GET.GetRestaurantsResponse;
import com.example.Restaurant.Restaurant.Dto.GET.RestaurantDetails;
import com.example.Restaurant.Restaurant.Dto.GET.RestaurantDto;
import com.example.Restaurant.Restaurant.Dto.PATCH.PatchRestaurantRequest;
import com.example.Restaurant.Restaurant.Dto.PUT.PutRestaurantRequest;
import com.example.Restaurant.Restaurant.EntityClass.Restaurant;
import com.example.Restaurant.Restaurant.Event.RestaurantEventRepository;
import com.example.Restaurant.Restaurant.Repository.RestaurantRepository;
import com.example.Restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantEventRepository eventRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantEventRepository eventRepository) {
        this.restaurantRepository = restaurantRepository;
        this.eventRepository = eventRepository;
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
        Restaurant newRestaurant = Restaurant.builder().build();
        newRestaurant.setID(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"));
        newRestaurant.setName(restaurantRequest.getName());
        newRestaurant.setNumberOfSits(restaurantRequest.getNumberOfSits());
        eventRepository.create(restaurantRequest);

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
            eventRepository.delete(id);
            restaurantRepository.delete(existingRestaurant);
            return true;
        }
        return false;
    }

    public RestaurantDetails convertToDto(Restaurant restaurant) {

        RestaurantDetails convertedRestaurant = new RestaurantDetails();

        convertedRestaurant.setId(restaurant.getID());
        convertedRestaurant.setName(restaurant.getName());
        convertedRestaurant.setNumberOfSits(restaurant.getNumberOfSits());

        return convertedRestaurant;
    }

    public GetRestaurantsResponse convertToDtoList(List<Restaurant> restaurants) {
        return GetRestaurantsResponse.builder().restaurants(
                restaurants.stream().map(restaurant ->
                        RestaurantDto.builder()
                                .id(restaurant.getID())
                                .name(restaurant.getName()).build()).toList()).build();
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.getRestaurantByName(name);
    }

}
