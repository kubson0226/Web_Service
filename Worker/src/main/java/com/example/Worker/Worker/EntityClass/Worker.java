package com.example.Worker.Worker.EntityClass;

import com.example.Worker.Restaurant.EntityClass.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "workers")
public class Worker implements Serializable {
    @Id
    private UUID ID;

    @Column(name = "worker_name")
    private String name;

    @Column(name = "worker_age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "restaurant")
    @EqualsAndHashCode.Exclude private Restaurant restaurant;

    @Override
    public String toString() {
        String str_representation = ("Worker name: " + this.name + " age: " + this.age +
                " restaurant: " + this.restaurant.getName());
        return str_representation;
    }
}
