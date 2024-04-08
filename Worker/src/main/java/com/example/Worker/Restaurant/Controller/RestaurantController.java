package com.example.Worker.Restaurant.Controller;

import com.example.Worker.Restaurant.Dto.RestaurantDto;
import com.example.Worker.Restaurant.EntityClass.Restaurant;
import com.example.Worker.Restaurant.Dto.PutRestaurantRequest;
import com.example.Worker.Restaurant.Service.RestaurantService;
import com.example.Worker.Worker.EntityClass.Worker;
import com.example.Worker.Worker.Repository.WorkerRepository;
import com.example.Worker.Worker.Service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantService restaurantService;
    private WorkerRepository workerRepository;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, WorkerRepository workerRepository) {
        this.restaurantService = restaurantService;
        this.workerRepository = workerRepository;
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody PutRestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantService.createRestaurantRequest(restaurantRequest);
        RestaurantDto convertedRestaurant = restaurantService.convertToDto(restaurant);
        return new ResponseEntity<>(convertedRestaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        Restaurant restaurant = restaurantService.getRestaurantByID(id);
        List<Worker> listOfWorkers = workerRepository.getWorkerByRestaurant(restaurant.getName());
        listOfWorkers.forEach(worker -> workerRepository.delete(worker));
        boolean isDeleted = restaurantService.deleteRestaurantRequest(id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
