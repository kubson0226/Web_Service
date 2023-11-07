package com.example.demo.Service;

import com.example.demo.Dto.GET.WorkerDto;
import com.example.demo.Dto.GET.GetWorkersResponse;
import com.example.demo.Dto.PATCH.PatchWorkerRequest;
import com.example.demo.Dto.PUT.PutWorkerRequest;
import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.EntityClasses.Worker;
import com.example.demo.Repositories.RestaurantRepository;
import com.example.demo.Repositories.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, RestaurantRepository restaurantRepository) {
        this.workerRepository = workerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Optional<Worker> getWorkersByRestaurant(Restaurant restaurant) {
        return workerRepository.getWorkerByRestaurant(restaurant);
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public void saveWorker(Worker worker) {
        workerRepository.save(worker);
    }

    public void deleteWorker(Worker worker) {
        workerRepository.delete(worker);
    }

    public Worker getWorkerById(UUID worker_ID) {
        return workerRepository.findById(worker_ID).orElse(null);
    }

    public Worker createWorkerRequest(PutWorkerRequest workerRequest, UUID restaurantID) {

        Restaurant restaurant = restaurantRepository.findById(restaurantID).orElse(null);
        if(restaurant == null) {
            return null;
        } else {
            Worker newWorker = new Worker();
            newWorker.setID(UUID.randomUUID());
            newWorker.setName(workerRequest.getName());
            newWorker.setAge(workerRequest.getAge());
            newWorker.setRestaurant(restaurant);
            return workerRepository.save(newWorker);
        }
    }

    public Worker patchWorkerRequest(UUID id, PatchWorkerRequest workerRequest) {

        Worker existingWorker = workerRepository.findById(id).orElse(null);
        if(existingWorker != null) {
            existingWorker.setName(workerRequest.getName());
            existingWorker.setAge(workerRequest.getAge());
            return workerRepository.save(existingWorker);
        }
        return null;

    }

    public boolean deleteWorkerRequest(UUID id) {

        Worker existingWorker = workerRepository.findById(id).orElse(null);
        if (existingWorker != null) {
            workerRepository.delete(existingWorker);
            return true;
        }
        return false;

    }

    public WorkerDto convertToDto(Worker worker) {

        WorkerDto convertedWorker = new WorkerDto();
        convertedWorker.setID(worker.getID());
        convertedWorker.setName(worker.getName());
        convertedWorker.setAge(worker.getAge());
        convertedWorker.setRestaurantName(worker.getRestaurant().getName());
        return convertedWorker;

    }

    public List<GetWorkersResponse> convertToDtoList(List<Worker> workers) {
        return workers.stream()
                .map(worker -> {
                    GetWorkersResponse newWorker = new GetWorkersResponse();
                    newWorker.setName(worker.getName());
                    newWorker.setId(worker.getID());
                    return newWorker;
                })
                .collect(Collectors.toList());
    }
}
