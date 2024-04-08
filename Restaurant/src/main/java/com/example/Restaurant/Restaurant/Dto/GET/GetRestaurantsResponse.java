package com.example.Restaurant.Restaurant.Dto.GET;


import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetRestaurantsResponse {

    private List<RestaurantDto> restaurants;

}
