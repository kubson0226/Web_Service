package com.example.Worker.Restaurant.Controller;

import com.example.Worker.Restaurant.Dto.RestaurantDto;
import com.example.Worker.Restaurant.EntityClass.Restaurant;
import com.example.Worker.Restaurant.Dto.PutRestaurantRequest;
import com.example.Worker.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody PutRestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantService.createRestaurantRequest(restaurantRequest);
        RestaurantDto convertedRestaurant = restaurantService.convertToDto(restaurant);
        return new ResponseEntity<>(convertedRestaurant, HttpStatus.CREATED);
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
