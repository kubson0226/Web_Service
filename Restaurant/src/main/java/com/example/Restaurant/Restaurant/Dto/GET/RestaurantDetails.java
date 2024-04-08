package com.example.Restaurant.Restaurant.Dto.GET;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class RestaurantDetails {

    private UUID id;

    private String name;

    private int numberOfSits;
}
