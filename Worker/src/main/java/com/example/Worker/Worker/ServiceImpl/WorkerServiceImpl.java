package com.example.Worker.Worker.ServiceImpl;

import com.example.Worker.Restaurant.Repository.RestaurantRepository;
import com.example.Worker.Worker.Dto.GET.GetWorkersResponse;
import com.example.Worker.Worker.Dto.GET.WorkerDetails;
import com.example.Worker.Worker.Dto.GET.WorkerDto;
import com.example.Worker.Worker.Dto.PATCH.PatchWorkerRequest;
import com.example.Worker.Worker.Dto.PUT.PutWorkerRequest;
import com.example.Worker.Restaurant.EntityClass.Restaurant;
import com.example.Worker.Worker.EntityClass.Worker;
import com.example.Worker.Worker.Repository.WorkerRepository;
import com.example.Worker.Worker.Service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, RestaurantRepository restaurantRepository) {
        this.workerRepository = workerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public List<Worker> getWorkersByRestaurant(String name) {
        return workerRepository.getWorkerByRestaurant(name);
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
            Worker newWorker = Worker.builder().build();
            newWorker.setID(UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"));
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

    public WorkerDetails convertToDto(Worker worker) {

        WorkerDetails convertedWorker = new WorkerDetails();
        convertedWorker.setID(worker.getID());
        convertedWorker.setName(worker.getName());
        convertedWorker.setAge(worker.getAge());
        convertedWorker.setRestaurantName(worker.getRestaurant().getName());
        return convertedWorker;

    }

    public GetWorkersResponse convertToDtoList(List<Worker> workers) {
        return GetWorkersResponse.builder().workers(
                workers.stream().map(worker ->
                        WorkerDto.builder()
                                .ID(worker.getID())
                                .name(worker.getName()).build()).toList()).build();
    }
}
