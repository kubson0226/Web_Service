package com.example.Worker.Restaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
    public interface RestaurantRepository extends JpaRepository<com.example.Worker.Restaurant.EntityClass.Restaurant, UUID> {
        @Query("select restaurant from Restaurant restaurant where restaurant.name = :name")
        com.example.Worker.Restaurant.EntityClass.Restaurant getRestaurantByName(
                @Param("name") String name
        );

    }
