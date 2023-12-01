package com.example.Restaurant.Restaurant.Controller;

import com.example.Restaurant.Restaurant.Dto.GET.GetRestaurantsResponse;
import com.example.Restaurant.Restaurant.Dto.GET.RestaurantDto;
import com.example.Restaurant.Restaurant.Dto.PATCH.PatchRestaurantRequest;
import com.example.Restaurant.Restaurant.Dto.PUT.PutRestaurantRequest;
import com.example.Restaurant.Restaurant.EntityClass.Restaurant;
import com.example.Restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantService restaurantService;
    private RestTemplate restTemplate;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestTemplate restTemplate) {
        this.restaurantService = restaurantService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<GetRestaurantsResponse>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.retrieveAllRestaurants();
        List<GetRestaurantsResponse> convertedRestaurants = restaurantService.convertToDtoList(restaurants);
        return new ResponseEntity<>(convertedRestaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getSpecificRestaurant(@PathVariable UUID id) {
        Restaurant existingRestaurant = restaurantService.getRestaurantByID(id);
        if(existingRestaurant != null){
            RestaurantDto convertedRestaurant = restaurantService.convertToDto(existingRestaurant);
            return new ResponseEntity<>(convertedRestaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody PutRestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantService.createRestaurantRequest(restaurantRequest);
        RestaurantDto convertedRestaurant = restaurantService.convertToDto(restaurant);
        return new ResponseEntity<>(convertedRestaurant, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable UUID id, @RequestBody PatchRestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantService.patchRestaurantRequest(id, restaurantRequest);
        if(restaurant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            RestaurantDto convertedRestaurant = restaurantService.convertToDto(restaurant);
            return new ResponseEntity<>(convertedRestaurant, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        boolean isDeleted = restaurantService.deleteRestaurantRequest(id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
