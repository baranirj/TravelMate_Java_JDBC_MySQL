package com.travelmate.service;

import com.travelmate.model.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> getAllPassengers();
    Passenger getPassengerById(int id);
    void addPassenger(Passenger passenger);
    void updatePassenger(Passenger passenger);
    void deletePassenger(int id);
}
