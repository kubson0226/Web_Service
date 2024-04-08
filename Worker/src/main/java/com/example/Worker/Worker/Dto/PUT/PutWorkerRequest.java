package com.example.Worker.Worker.Dto.PUT;

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
