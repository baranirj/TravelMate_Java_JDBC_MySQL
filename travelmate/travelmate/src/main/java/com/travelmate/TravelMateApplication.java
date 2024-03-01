package com.travelmate;

import com.travelmate.dao.*;
import com.travelmate.model.*;
import com.travelmate.service.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelMateApplication {



    private final TravelPackageService travelPackageService;
    private final DestinationService destinationService;
    private final ActivityService activityService;
    private final PassengerService passengerService;

    Scanner scanner = new Scanner(System.in);

    public TravelMateApplication() {
        this.travelPackageService = new TravelPackageServiceImpl(new TravelPackageDAOImpl());
        this.destinationService = new DestinationServiceImpl(new DestinationDAOImpl());
        this.activityService = new ActivityServiceImpl(new ActivityDAOImpl());
        this.passengerService = new PassengerServiceImpl(new PassengerDAOImpl());
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Travel Agency Menu =====");
            System.out.println("1. Passenger Module");
            System.out.println("2. Package Module");
            System.out.println("3. Destination Module");
            System.out.println("4. Activities Module");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int moduleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (moduleChoice) {
                case 1:
                    runPassengerModule();
                    break;
                case 2:
                    runPackageModule();
                    break;
                case 3:
                    runDestinationModule();
                    break;
                case 4:
                    runActivitiesModule();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close(); // Close scanner before exiting
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void storeDefaultData() {

    }

    private void runPackageModule() {
        while (true) {
            System.out.println("\n===== Package Module =====");
            System.out.println("1. Add a new travel package");
            System.out.println("2. View travel packages");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewTravelPackage(scanner);
                    break;
                case 2:
                    viewTravelPackages();
                    break;
                case 3:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void addNewTravelPackage(Scanner scanner) {
        System.out.println("Enter the name of the travel package:");
        String name = scanner.nextLine();
        System.out.println("Enter the passenger capacity of the travel package:");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Do you want to manually create destinations or use existing ones?");
        System.out.println("1. Manually create destinations");
        System.out.println("2. Use existing destinations (enter IDs)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Destination> destinations = new ArrayList<>();
        if (choice == 1) {
            // Manually create destinations
            System.out.println("Enter the number of destinations:");
            int numDestinations = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numDestinations; i++) {
                System.out.println("Enter the name of destination " + (i + 1) + ":");
                String destinationName = scanner.nextLine();
                System.out.println("Enter the description of destination " + (i + 1) + ":");
                String description = scanner.nextLine();
                System.out.println("Enter the number of activities available at destination " + (i + 1) + ":");
                int numActivities = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                List<Activity> activities = new ArrayList<>();
                for (int j = 0; j < numActivities; j++) {
                    System.out.println("Enter the name of activity " + (j + 1) + " at destination " + (i + 1) + ":");
                    String activityName = scanner.nextLine();
                    // Prompt for other activity details like cost, capacity, etc.

                    // Create the activity object and add it to the list
                    Activity activity = new Activity(activityName, description, 0.0, 0);
                    activities.add(activity);
                }

                // Create the destination object with activities and add it to the list
                Destination destination = new Destination(destinationName, activities);
                destinations.add(destination);
            }
        } else if (choice == 2) {
            // Use existing destinations
            System.out.println("Enter the IDs of existing destinations (comma-separated):");
            String destinationIdsStr = scanner.nextLine();
            String[] destinationIds = destinationIdsStr.split(",");
            for (String destinationIdStr : destinationIds) {
                int destinationId = Integer.parseInt(destinationIdStr.trim());
                Destination destination = destinationService.getDestinationById(destinationId);
                if (destination != null) {
                    destinations.add(destination);
                } else {
                    System.out.println("Destination with ID " + destinationId + " not found.");
                }
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        TravelPackage travelPackage = new TravelPackage(name, capacity, destinations);
        travelPackageService.createTravelPackage(travelPackage);
        System.out.println("Travel package added successfully!");
    }


    private void viewTravelPackages() {
        System.out.println("===== Travel Packages =====");
        List<TravelPackage> travelPackages = travelPackageService.getAllTravelPackages();
        for (TravelPackage travelPackage : travelPackages) {
            System.out.println(travelPackage);
        }
    }


    private void runPassengerModule() {
        while (true) {
            System.out.println("\n===== Passenger Module =====");
            System.out.println("1. Add a new passenger");
            System.out.println("2. View passengers");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewPassenger(scanner);
                    break;
                case 2:
                    viewPassengers();
                    break;
                case 3:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }





    private void addNewPassenger(Scanner scanner) {
        System.out.println("Enter the name of the passenger:");
        String name = scanner.nextLine();
        System.out.println("Enter the passenger number:");
        int passengerNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the type of the passenger (STANDARD, GOLD, PREMIUM):");
        String passengerTypeStr = scanner.nextLine().toUpperCase();
        PassengerType passengerType = PassengerType.valueOf(passengerTypeStr);
        double balance = 0;
        int travelPackageId = 0;
        if (passengerType != PassengerType.PREMIUM) {
            System.out.println("Enter the balance of the passenger:");
            balance = scanner.nextDouble();
            scanner.nextLine();
        }
        System.out.println("Enter the travel package ID of the passenger:");
        travelPackageId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Passenger passenger = new Passenger(name, passengerNumber, passengerType, balance, travelPackageId);
        passengerService.addPassenger(passenger);
        System.out.println("Passenger added successfully!");
    }



    private void viewPassengers() {
        System.out.println("===== Passengers =====");
        List<Passenger> passengers = passengerService.getAllPassengers();
        for (Passenger passenger : passengers) {
            System.out.println(passenger);
        }
    }

    private void runDestinationModule() {
        while (true) {
            System.out.println("\n===== Destination Module =====");
            System.out.println("1. Add a new destination");
            System.out.println("2. View destinations");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewDestination(scanner);
                    break;
                case 2:
                    viewDestinations();
                    break;
                case 3:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //only use when package name id is already generated.
    private void addNewDestination(Scanner scanner) {
        System.out.println("Enter the name of the destination:");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Destination name cannot be empty. Please try again.");
            return; // Exit method if name is empty
        }

        //Ask user for the package id

        System.out.println("Enter the Package ID:");
        int packageId = scanner.nextInt();


        // Ask user for the number of activities
        System.out.println("Enter the number of activities for this destination:");
        int numActivities = scanner.nextInt();
        scanner.nextLine();


        // Create an ArrayList to store activities
        List<Activity> activities = new ArrayList<>();

        // Create the destination object with the provided name and activities
        Destination destination = new Destination(name);

        destination.setName(name);
        TravelPackage travelPackage = travelPackageService.getTravelPackageById(packageId);
        if(travelPackage.getName()!=null){

            destination.setPackageId(packageId);


        }
        else{
            System.out.println("wrong package id! try again ");
            addNewDestination(scanner);
        }

// Loop to get details of each activity
        for (int i = 0; i < numActivities; i++) {
            System.out.println("Enter the details for activity " + (i + 1) + ":");
            System.out.println("Enter the name of the activity:");
            String activityName = scanner.nextLine();
            System.out.println("Enter the description of the activity:");
            String description = scanner.nextLine();
            System.out.println("Enter the cost of the activity:");
            double cost = scanner.nextDouble();
            System.out.println("Enter the capacity of the activity:");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Create the activity object with the provided details and add it to the list
            Activity activity = new Activity(activityName, description, cost, capacity,destination.getId());
            activities.add(activity);
        }



        destination.setActivities(activities);

        // Add the destination with its associated activities
        destinationService.addDestination(destination);
        System.out.println("Destination added successfully!");
    }


    private void viewDestinations() {
        System.out.println("===== Destinations =====");
        List<Destination> destinations = destinationService.getAllDestinations();
        for (Destination destination : destinations) {
            System.out.println(destination);
        }
    }


    private void runActivitiesModule() {
        while (true) {
            System.out.println("\n===== Activities Module =====");
            System.out.println("1. Add a new activity");
            System.out.println("2. View activities");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewActivity(scanner);
                    break;
                case 2:
                    viewActivities();
                    break;
                case 3:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addNewActivity(Scanner scanner) {
        System.out.println("Enter the name of the activity:");
        String name = scanner.nextLine();
        System.out.println("Enter the description of the activity:");
        String description = scanner.nextLine();
        System.out.println("Enter the cost of the activity:");
        double cost = scanner.nextDouble();
        System.out.println("Enter the capacity of the activity:");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the destination ID of the activity:");
        int destinationId = scanner.nextInt();
        scanner.nextLine();

        // Retrieve the destination based on the entered ID
        Destination destination = destinationService.getDestinationById(destinationId);
        if (destination == null) {
            System.out.println("Destination with ID " + destinationId + " not found.");
            return;
        }

        // Create the activity object and add it to the destination
        Activity activity = new Activity(name, description, cost, capacity, destination.getId());
        activityService.addActivity(activity);
        System.out.println("Activity added successfully!");
    }


    private void viewActivities() {
        System.out.println("===== Activities =====");
        List<Activity> activities = activityService.getAllActivities();
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }

    public  void createPredefinedData() {

        // Define travel packages
        TravelPackage travelPackage1 = new TravelPackage("Golden Triangle", 20);
        TravelPackage travelPackage2 = new TravelPackage("Backwaters and Heritage", 15);



        int packageId1 = 1;
        int packageId2 = 2;

        List<Destination> destinations = new ArrayList<>();

        // Define activities for each destination and persist them

        // Destination 1 - Goa
        Destination destination1 = new Destination("Goa");
        destination1.setName("Goa");
        destination1.setPackageId(packageId1);
        destinations.add(destination1);
//        destinationService.addDestination(destination1);


        // Destination 2 - Jaipur
        Destination destination2 = new Destination("Jaipur");
        destination1.setName("Jaipur");

        destination2.setPackageId(packageId1);
        destinations.add(destination2);


        // Destination 3 - Kerala
        Destination destination3 = new Destination("Kerala");
        destination1.setName("kerala");
        destination3.setPackageId(packageId1);
        destinations.add(destination3);

        //persist destinations

        destinationService.addDestination(destination1);
        destinationService.addDestination(destination2);
        destinationService.addDestination(destination3);

        travelPackage1.setDestinations(destinations);



        travelPackageService.createTravelPackage(travelPackage1);
        // Repeat the above steps for travelPackage2 and its destinations with activities


    }

    public void storeActivities(){
        // Activities for Destination 1
        activityService.addActivity(new Activity("Visit Calangute Beach", "Relax on the beautiful beach", 500, 20, 1));
        activityService.addActivity(new Activity("Explore Dudhsagar Falls", "Take a trip to the majestic Dudhsagar Falls", 800, 15, 1));
        activityService.addActivity(new Activity("Enjoy Water Sports at Baga Beach", "Experience thrilling water sports at Baga Beach", 1000, 10, 1));
        activityService.addActivity(new Activity("Visit Fort Aguada", "Explore the historic Fort Aguada", 300, 30, 1));
        activityService.addActivity(new Activity("Shop at Anjuna Flea Market", "Discover unique items at Anjuna Flea Market", 200, 50, 1));

        // Activities for Destination 2
        activityService.addActivity(new Activity("Visit Amber Fort", "Explore the majestic Amber Fort", 400, 25, 2));
        activityService.addActivity(new Activity("Explore City Palace", "Discover the architectural beauty of City Palace", 300, 30, 2));
        activityService.addActivity(new Activity("Shop at Johari Bazaar", "Experience the vibrant Johari Bazaar", 200, 50, 2));
        activityService.addActivity(new Activity("Visit Jantar Mantar", "Explore the ancient astronomical observatory", 250, 40, 2));
        activityService.addActivity(new Activity("Enjoy Elephant Ride at Amer Fort", "Take a thrilling elephant ride at Amer Fort", 600, 15, 2));

        // Activities for Destination 3
        activityService.addActivity(new Activity("Cruise in Backwaters", "Experience a serene backwater cruise", 700, 20, 3));
        activityService.addActivity(new Activity("Visit Periyar Wildlife Sanctuary", "Explore the diverse wildlife at Periyar Wildlife Sanctuary", 900, 15, 3));
        activityService.addActivity(new Activity("Enjoy Kathakali Dance Performance", "Witness the captivating Kathakali dance", 400, 30, 3));
        activityService.addActivity(new Activity("Visit Tea Gardens in Munnar", "Take a tour of lush green tea gardens in Munnar", 300, 40, 3));
        activityService.addActivity(new Activity("Relax at Varkala Beach", "Unwind at the scenic Varkala Beach", 600, 25, 3));

    }




    public static void main(String[] args) throws SQLException {

        // Initialize the database schema
        DatabaseManager.establishConnection();

        // Create tables for travel package, destination, activity, and passenger
        DatabaseInitializer.createTravelPackageTable();
        DatabaseInitializer.createDestinationTable();
        DatabaseInitializer.createActivityTable();
        DatabaseInitializer.createPassengerTable();

        TravelMateApplication app = new TravelMateApplication();

        app.createPredefinedData();

        app.run();
    }
}

