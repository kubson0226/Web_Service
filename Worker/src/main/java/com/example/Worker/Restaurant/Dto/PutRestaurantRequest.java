package com.example.Worker.Restaurant.Dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutRestaurantRequest {

    private String name;

    private int numberOfSits;
}
