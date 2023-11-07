package com.example.demo;

import com.example.demo.EntityClasses.Worker;

import java.util.Comparator;

public class MySort implements Comparator<Worker> {
    @Override
    public int compare(Worker o1, Worker o2) {
        if(o1.getAge() - o2.getAge() != 0) {
            return o1.getAge() - o2.getAge();
        }
        else {
            return o1.getName().compareTo(o2.getName()) + (o1.getAge() - o2.getAge());
        }
    }
}
