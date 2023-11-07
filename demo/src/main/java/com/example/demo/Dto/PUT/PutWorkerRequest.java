package com.example.demo.Dto.PUT;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutWorkerRequest {

    private String name;

    private int age;

}
