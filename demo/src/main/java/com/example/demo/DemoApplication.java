package com.example.demo;

import ch.qos.logback.classic.joran.SerializedModelConfigurator;
import ch.qos.logback.classic.spi.Configurator;
import com.example.demo.DataInitializer.DataInitializer;
import com.example.demo.Repositories.RestaurantRepository;
import com.example.demo.Service.RestaurantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DemoApplication.class, args);
	}
}
