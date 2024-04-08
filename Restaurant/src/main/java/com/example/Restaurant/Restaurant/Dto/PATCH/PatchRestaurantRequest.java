package com.example.Restaurant.Restaurant.Dto.PATCH;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchRestaurantRequest {

    private String name;

    private int numberOfSits;
}
