package com.example.demo.Controllers;

import com.example.demo.Dto.GET.GetWorkersResponse;
import com.example.demo.Dto.GET.WorkerDto;
import com.example.demo.Dto.PATCH.PatchWorkerRequest;
import com.example.demo.Dto.PUT.PutWorkerRequest;
import com.example.demo.EntityClasses.Worker;
import com.example.demo.ServiceImpl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    private WorkerServiceImpl workerService;

    @Autowired
    public WorkerController(WorkerServiceImpl workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public ResponseEntity<List<GetWorkersResponse>> getAllWorkers() {
        List<Worker> workers = workerService.getAllWorkers();
        List<GetWorkersResponse> convertedWorkers = workerService.convertToDtoList(workers);
        return new ResponseEntity<>(convertedWorkers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDto> getSpecificWorker(@PathVariable UUID id) {
        Worker worker = workerService.getWorkerById(id);
        if(worker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            WorkerDto convertedWorker = workerService.convertToDto(worker);
            return new ResponseEntity<>(convertedWorker, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<WorkerDto> createWorker(@RequestBody PutWorkerRequest workerRequest, @RequestParam UUID restaurantID) {
        Worker worker = workerService.createWorkerRequest(workerRequest, restaurantID);
        if (worker == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            WorkerDto convertedWorker = workerService.convertToDto(worker);
            return new ResponseEntity<>(convertedWorker, HttpStatus.CREATED);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkerDto> updateWorker(@PathVariable UUID id, @RequestBody PatchWorkerRequest workerRequest) {
        Worker worker = workerService.patchWorkerRequest(id, workerRequest);
        if(worker == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            WorkerDto convertedWorker = workerService.convertToDto(worker);
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
