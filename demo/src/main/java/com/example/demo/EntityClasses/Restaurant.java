package com.example.demo.EntityClasses;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {
    @Id
    @Column(name = "restaurant_ID")
    private UUID ID;

    @Column(name = "restaurant_name")
    private String name;

    @Column
    private int numberOfSits;

    @OneToMany(mappedBy = "restaurant")
    private List<Worker> workers;
}
