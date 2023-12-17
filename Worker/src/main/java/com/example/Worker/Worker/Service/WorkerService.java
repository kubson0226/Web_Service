package com.example.Worker.Worker.Service;

import com.example.Worker.Worker.Dto.GET.GetWorkersResponse;
import com.example.Worker.Worker.Dto.GET.WorkerDetails;
import com.example.Worker.Worker.Dto.GET.WorkerDto;
import com.example.Worker.Worker.Dto.PATCH.PatchWorkerRequest;
import com.example.Worker.Worker.Dto.PUT.PutWorkerRequest;
import com.example.Worker.Worker.EntityClass.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface WorkerService {

    List<Worker> getAllWorkers();
    List<Worker> getWorkersByRestaurant(String name);
    void saveWorker(Worker worker);
    void deleteWorker(Worker worker);
    Worker getWorkerById(UUID worker_ID);
    Worker createWorkerRequest(PutWorkerRequest workerRequest, UUID restaurantID);
    Worker patchWorkerRequest(UUID id, PatchWorkerRequest workerRequest);
    boolean deleteWorkerRequest(UUID id);
    WorkerDetails convertToDto(Worker worker);
    GetWorkersResponse convertToDtoList(List<Worker> workers);
}
