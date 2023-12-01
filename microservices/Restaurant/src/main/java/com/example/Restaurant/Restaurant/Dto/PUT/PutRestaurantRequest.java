package com.example.Restaurant.Restaurant.Dto.PUT;

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
