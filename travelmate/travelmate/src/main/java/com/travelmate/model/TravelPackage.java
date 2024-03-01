package com.travelmate.model;

import java.util.List;

public class TravelPackage {
    private int id;
    private String name;
    private int passengerCapacity;
    private List<Destination> destinations;
    private List<Passenger> passengers;

    // Constructor


    public TravelPackage() {
    }

    public TravelPackage( String name, int passengerCapacity, List<Destination> destinations) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.destinations = destinations;
    }

    public TravelPackage(String name, int capacity) {

        this.name=name;
        this.passengerCapacity=capacity;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+------+----------------------+------------------+\n");
        sb.append("| ID   | Name                 | Passenger Capacity |\n");
        sb.append("+------+----------------------+------------------+\n");
        sb.append(String.format("| %-4d | %-20s | %-18d |\n", id, name, passengerCapacity));
        sb.append("+------+----------------------+------------------+\n");

        // Check if destinations list is not null
        if (destinations != null && !destinations.isEmpty()) {
            sb.append("Destinations:\n");
            sb.append("+------+----------------------+\n");
            sb.append("| ID   | Name                 |\n");
            sb.append("+------+----------------------+\n");
            for (Destination destination : destinations) {
                sb.append(String.format("| %-4d | %-20s |\n", destination.getId(), destination.getName()));
            }
            sb.append("+------+----------------------+\n");
        } else {
            sb.append("No destinations\n");
        }

        // Check if passengers list is not null
        if (passengers != null && !passengers.isEmpty()) {
            sb.append("Passengers:\n");
            sb.append("+------+----------------------+\n");
            sb.append("| ID   | Name                 |\n");
            sb.append("+------+----------------------+\n");
            for (Passenger passenger : passengers) {
                sb.append(String.format("| %-4d | %-20s |\n", passenger.getId(), passenger.getName()));
            }
            sb.append("+------+----------------------+\n");
        } else {
            sb.append("No passengers\n");
        }

        return sb.toString();
    }



}
