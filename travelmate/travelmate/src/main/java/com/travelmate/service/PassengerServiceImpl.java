package com.travelmate.service;

import com.travelmate.dao.PassengerDAO;
import com.travelmate.model.Passenger;

import java.util.List;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerDAO passengerDAO;

    public PassengerServiceImpl(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerDAO.getAllPassengers();
    }

    @Override
    public Passenger getPassengerById(int id) {
        return passengerDAO.getPassengerById(id);
    }

    @Override
    public void addPassenger(Passenger passenger) {
        passengerDAO.addPassenger(passenger);
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        passengerDAO.updatePassenger(passenger);
    }

    @Override
    public void deletePassenger(int id) {
        passengerDAO.deletePassenger(id);
    }
}
