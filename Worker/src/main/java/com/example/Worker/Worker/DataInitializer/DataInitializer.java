package com.example.Worker.Worker.DataInitializer;

import com.example.Worker.Restaurant.EntityClass.Restaurant;
import com.example.Worker.Worker.EntityClass.Worker;
import com.example.Worker.Restaurant.ServiceImpl.RestaurantServiceImpl;
import com.example.Worker.Worker.ServiceImpl.WorkerServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {
    private final RestaurantServiceImpl restaurantService;
    private final WorkerServiceImpl workerService;

    @Autowired
    public DataInitializer(RestaurantServiceImpl restaurantService, WorkerServiceImpl workerService) {
        this.restaurantService = restaurantService;
        this.workerService = workerService;
    }
    @PostConstruct
    public void initializeData() {
        try {
            Restaurant restaurant1 = Restaurant.builder().ID(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76")).name("KFC").build();
            Restaurant restaurant2 = Restaurant.builder().ID(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4")).name("McDonald's").build();

            restaurantService.saveRestaurant(restaurant1);
            restaurantService.saveRestaurant(restaurant2);

            Worker worker1 = Worker.builder().ID(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd")).name("Jakub").age(22).restaurant(restaurant1).build();
            Worker worker2 = Worker.builder().ID(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6")).name("Kamil").age(23).restaurant(restaurant1).build();
            Worker worker3 = Worker.builder().ID(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e")).name("Alicja").age(19).restaurant(restaurant2).build();
            Worker worker4 = Worker.builder().ID(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197")).name("Julita").age(20).restaurant(restaurant2).build();
            Worker worker5 = Worker.builder().ID(UUID.fromString("f08ef7e3-7f2a-4378-b1fb-2922d730c70d")).name("Hubert").age(22).restaurant(restaurant2).build();
            Worker worker6 = Worker.builder().ID(UUID.fromString("ff327e8a-77c0-4f9b-90a2-89e16895d1e1")).name("Natalia").age(20).restaurant(restaurant2).build();

            workerService.saveWorker(worker1);
            workerService.saveWorker(worker2);
            workerService.saveWorker(worker3);
            workerService.saveWorker(worker4);
            workerService.saveWorker(worker5);
            workerService.saveWorker(worker6);

        } catch (Exception e){
            System.out.println("Initialization failed: " + e.getMessage());
        }
    }
}
