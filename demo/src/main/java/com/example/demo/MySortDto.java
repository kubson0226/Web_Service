package com.example.demo;

import com.example.demo.Dto.GET.WorkerDto;

import java.util.Comparator;

class MySortDto implements Comparator<WorkerDto> {
    @Override
    public int compare(WorkerDto o1, WorkerDto o2) {
        if(o1.getAge() - o2.getAge() != 0) {
            return o1.getAge() - o2.getAge();
        }
        else {
            return o1.getName().compareTo(o2.getName()) + (o1.getAge() - o2.getAge());
        }
    }
}