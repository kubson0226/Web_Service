package com.example.Worker.Worker.Controller;

import com.example.Worker.Worker.Dto.GET.GetWorkersResponse;
import com.example.Worker.Worker.Dto.GET.WorkerDetails;
import com.example.Worker.Worker.Dto.GET.WorkerDto;
import com.example.Worker.Worker.Dto.PATCH.PatchWorkerRequest;
import com.example.Worker.Worker.Dto.PUT.PutWorkerRequest;
import com.example.Worker.Worker.EntityClass.Worker;
import com.example.Worker.Worker.Service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    private WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public ResponseEntity<GetWorkersResponse> getAllWorkers() {
        List<Worker> workers = workerService.getAllWorkers();
        GetWorkersResponse convertedWorkers = workerService.convertToDtoList(workers);
        return new ResponseEntity<>(convertedWorkers, HttpStatus.OK);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<GetWorkersResponse> getWorkersFromRestaurant(@RequestParam String restaurantName) {
        List<Worker> workers = workerService.getWorkersByRestaurant(restaurantName);
        GetWorkersResponse convertedWorkers = workerService.convertToDtoList(workers);
        return new ResponseEntity<>(convertedWorkers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDetails> getSpecificWorker(@PathVariable UUID id) {
        Worker worker = workerService.getWorkerById(id);
        if(worker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            WorkerDetails convertedWorker = workerService.convertToDto(worker);
            return new ResponseEntity<>(convertedWorker, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<WorkerDetails> createWorker(@RequestBody PutWorkerRequest workerRequest, @RequestParam UUID restaurantID) {
        Worker worker = workerService.createWorkerRequest(workerRequest, restaurantID);
        if (worker == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            WorkerDetails convertedWorker = workerService.convertToDto(worker);
            return new ResponseEntity<>(convertedWorker, HttpStatus.CREATED);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkerDetails> updateWorker(@PathVariable UUID id, @RequestBody PatchWorkerRequest workerRequest) {
        Worker worker = workerService.patchWorkerRequest(id, workerRequest);
        if(worker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            WorkerDetails convertedWorker = workerService.convertToDto(worker);
            return new ResponseEntity<>(convertedWorker, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WorkerDto> deleteWorker(@PathVariable UUID id) {
        boolean isDeleted = workerService.deleteWorkerRequest(id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
