package com.example.Worker.Restaurant.Dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class RestaurantDto {

    private UUID id;

    private String name;

    private int numberOfSits;
}
