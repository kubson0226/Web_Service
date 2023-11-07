package com.example.demo.Controllers;

import com.example.demo.Dto.GET.RestaurantDto;
import com.example.demo.Dto.GET.GetRestaurantsResponse;
import com.example.demo.Dto.PATCH.PatchRestaurantRequest;
import com.example.demo.Dto.PUT.PutRestaurantRequest;
import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.ServiceImpl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantServiceImpl restaurantService;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
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
