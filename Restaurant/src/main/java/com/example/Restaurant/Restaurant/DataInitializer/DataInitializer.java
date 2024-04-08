package com.example.Restaurant.Restaurant.DataInitializer;

import com.example.Restaurant.Restaurant.EntityClass.Restaurant;
import com.example.Restaurant.Restaurant.ServiceImpl.RestaurantServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {
    private final RestaurantServiceImpl restaurantService;

    @Autowired
    public DataInitializer(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }
    @PostConstruct
    public void initializeData() {
        try {
            Restaurant restaurant1 = Restaurant.builder().ID(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76")).name("KFC").numberOfSits(69).build();
            Restaurant restaurant2 = Restaurant.builder().ID(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4")).name("McDonald's").numberOfSits(120).build();

            restaurantService.saveRestaurant(restaurant1);
            restaurantService.saveRestaurant(restaurant2);

        } catch (Exception e){
            System.out.println("Initialization failed: " + e.getMessage());
        }
    }
}
