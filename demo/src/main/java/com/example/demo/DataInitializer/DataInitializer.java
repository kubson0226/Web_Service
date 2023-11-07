package com.example.demo.DataInitializer;

import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.EntityClasses.Worker;
import com.example.demo.ServiceImpl.RestaurantServiceImpl;
import com.example.demo.ServiceImpl.WorkerServiceImpl;
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
            Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "KFC", 69, null);
            Restaurant restaurant2 = new Restaurant(UUID.randomUUID(), "McDonald", 120, null);

            restaurantService.saveRestaurant(restaurant1);
            restaurantService.saveRestaurant(restaurant2);

            Worker worker1 = new Worker(UUID.randomUUID(), "Jakub", 22, restaurant1);
            Worker worker2 = new Worker(UUID.randomUUID(), "Kamil", 23, restaurant1);
            Worker worker3 = new Worker(UUID.randomUUID(), "Alicja", 19, restaurant2);
            Worker worker4 = new Worker(UUID.randomUUID(), "Julita", 20, restaurant2);
            Worker worker5 = new Worker(UUID.randomUUID(), "Hubert", 22, restaurant2);
            Worker worker6 = new Worker(UUID.randomUUID(), "Natalia", 20, restaurant2);

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
