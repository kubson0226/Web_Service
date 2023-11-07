package com.example.demo.Repositories;
import com.example.demo.EntityClasses.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
    public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    }
