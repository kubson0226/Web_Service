package com.example.Restaurant.Restaurant.Repository;
import com.example.Restaurant.Restaurant.EntityClass.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
    public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
        @Query("select restaurant from Restaurant restaurant where restaurant.name = :name")
        Restaurant getRestaurantByName(
                @Param("name") String name
        );

    }
