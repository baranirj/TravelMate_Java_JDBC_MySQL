package com.travelmate.dao;

import com.travelmate.model.Passenger;

import java.util.List;
public interface PassengerDAO {
    List<Passenger> getPassengersByTravelPackageId(int travelPackageId);

    List<Passenger> getAllPassengers();
    Passenger getPassengerById(int id);
    void addPassenger(Passenger passenger);
    void updatePassenger(Passenger passenger);
    void deletePassenger(int id);
}
