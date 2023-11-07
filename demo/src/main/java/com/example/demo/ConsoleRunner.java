package com.example.demo;

import com.example.demo.DataInitializer.DataInitializer;
import com.example.demo.EntityClasses.Restaurant;
import com.example.demo.EntityClasses.Worker;
import com.example.demo.Service.RestaurantService;
import com.example.demo.Service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {

            displayMenu();

            int command = scanner.nextInt();
            scanner.nextLine();

            switch(command) {
                case 1:
                    printRestaurants();
                    break;
                case 2:
                    printWorkers();
                    break;
                case 3:
                    addWorker(scanner);
                    break;
                case 4:
                    deleteWorker(scanner);
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Goodbye and see you soon");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong command, try again");
            }

        }
    }

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private WorkerService workerService;

    public void displayMenu() {

        System.out.println("Available commands:");
        System.out.println("1. List all restaurants");
        System.out.println("2. List all workers");
        System.out.println("3. Add worker");
        System.out.println("4. Delete worker");
        System.out.println("5. Exit");

        System.out.println();
        System.out.println("Enter a command (1-5): ");
    }

    public void printRestaurants() {
        List<Restaurant> restaurants = restaurantService.retrieveAllRestaurants();
        restaurants.forEach(restaurant -> System.out.println("ID: " + restaurant.getID() + " Name: " + restaurant.getName()));
    }

    public void printWorkers() {
        List <Worker> workers = workerService.getAllWorkers();
        workers.forEach(worker -> System.out.println("ID: " + worker.getID()
                + " Name: " + worker.getName()
                + " Age: " + worker.getAge()
                + " Restaurant: " + worker.getRestaurant().getName()));
    }

    public void addWorker(Scanner scanner) {
        System.out.println("Type in name of the worker");
        String worker_name = scanner.nextLine();
        System.out.println("Type in the age of the worker");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Available restaurants for the worker: ");
        List<Restaurant> availableRestaurants = restaurantService.retrieveAllRestaurants();
        availableRestaurants.forEach(restaurant -> System.out.println("ID: " + restaurant.getID()
                + " Name: " + restaurant.getName()));
        System.out.println();
        System.out.println("Type in the ID of the restaurant that the worker is in.");
        String restaurant_ID_input = scanner.nextLine();

        UUID restaurant_ID;

        try {
            restaurant_ID = UUID.fromString(restaurant_ID_input);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid restaurant ID format. Worker not added.");
            return;
        }

        Restaurant selectedRestaurant = restaurantService.getRestaurantByID(restaurant_ID);
        if(selectedRestaurant == null) {
            System.out.println("Invalid restaurant. Worker not added");
        }
        else {
            Worker newWorker = new Worker(UUID.randomUUID(), worker_name, age, selectedRestaurant);
            workerService.saveWorker(newWorker);
            System.out.println("Worker added successfully.");
        }
    }

    public void deleteWorker(Scanner scanner) {
        System.out.println("Here are available workers: ");
        List<Worker> allWorkers = workerService.getAllWorkers();
        allWorkers.forEach(worker -> System.out.println("ID: " + worker.getID()
                + " Name: " + worker.getName()
                + " Age: " + worker.getAge()
                + " Restaurant: " + worker.getRestaurant().getName()));

        System.out.println();
        System.out.println("Type in the ID of the worker that you would like to delete.");
        String worker_ID_input = scanner.nextLine();
        UUID worker_ID = UUID.fromString(worker_ID_input);
        try {
            Worker selectedWorker = workerService.getWorkerById(worker_ID);
            workerService.deleteWorker(selectedWorker);
            System.out.println("Worker deleted successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid worker_ID format. Worker not deleted");
        }
    }
}
