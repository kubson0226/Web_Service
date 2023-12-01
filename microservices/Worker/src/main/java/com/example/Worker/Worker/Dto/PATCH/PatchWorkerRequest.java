package com.example.Worker.Worker.Dto.PATCH;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchWorkerRequest {

    private String name;

    private int age;
}
