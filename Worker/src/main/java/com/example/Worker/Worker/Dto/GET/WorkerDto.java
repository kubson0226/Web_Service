package com.example.Worker.Worker.Dto.GET;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class WorkerDto {

    private UUID ID;

    private String name;

}

