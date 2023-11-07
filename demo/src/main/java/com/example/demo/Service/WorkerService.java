package com.example.demo.Service;

import com.example.demo.Dto.GET.GetWorkersResponse;
import com.example.demo.Dto.GET.WorkerDto;
import com.example.demo.Dto.PATCH.PatchWorkerRequest;
import com.example.demo.Dto.PUT.PutWorkerRequest;
import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.EntityClasses.Worker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkerService {
    Optional<Worker> getWorkersByRestaurant(Restaurant restaurant);
    List<Worker> getAllWorkers();
    void saveWorker(Worker worker);
    void deleteWorker(Worker worker);
    Worker getWorkerById(UUID worker_ID);
    Worker createWorkerRequest(PutWorkerRequest workerRequest, UUID restaurantID);
    Worker patchWorkerRequest(UUID id, PatchWorkerRequest workerRequest);
    boolean deleteWorkerRequest(UUID id);
    WorkerDto convertToDto(Worker worker);
    List<GetWorkersResponse> convertToDtoList(List<Worker> workers);
}
