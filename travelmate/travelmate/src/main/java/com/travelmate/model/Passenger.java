package com.travelmate.model;

public class Passenger {
    private int id;
    private String name;
    private int passengerNumber;
    private PassengerType passengerType;
    private double balance;
    private int travelPackageId;

    // Constructors
    public Passenger() {
    }

    public Passenger(String name, int passengerNumber, PassengerType passengerType, double balance, int travelPackageId) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.passengerType = passengerType;
        this.balance = balance;
        this.travelPackageId = travelPackageId;
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

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getTravelPackageId() {
        return travelPackageId;
    }

    public void setTravelPackageId(int travelPackageId) {
        this.travelPackageId = travelPackageId;
    }

    // Override toString() method for logging purposes
    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passengerNumber=" + passengerNumber +
                ", passengerType=" + passengerType +
                ", balance=" + balance +
                ", travelPackageId=" + travelPackageId +
                '}';
    }
}
