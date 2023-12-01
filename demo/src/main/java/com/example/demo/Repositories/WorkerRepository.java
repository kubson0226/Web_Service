package com.example.demo.Repositories;

import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.EntityClasses.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    @Query("select worker from Worker worker where worker.restaurant.name = :restaurant_name")
    List<Worker> getWorkerByRestaurant(
            @Param("restaurant_name") String name
    );

}
