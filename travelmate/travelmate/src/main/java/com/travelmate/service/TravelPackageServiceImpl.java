package com.travelmate.service;

import com.travelmate.dao.TravelPackageDAO;
import com.travelmate.model.TravelPackage;

import java.util.List;

public class TravelPackageServiceImpl implements TravelPackageService {

    private final TravelPackageDAO travelPackageDAO;

    public TravelPackageServiceImpl(TravelPackageDAO travelPackageDAO) {
        this.travelPackageDAO = travelPackageDAO;
    }

    @Override
    public void createTravelPackage(TravelPackage travelPackage) {
        travelPackageDAO.addTravelPackage(travelPackage);
    }

    @Override
    public void updateTravelPackage(TravelPackage travelPackage) {
        travelPackageDAO.updateTravelPackage(travelPackage);
    }

    @Override
    public void deleteTravelPackage(int travelPackageId) {
        travelPackageDAO.deleteTravelPackage(travelPackageId);
    }

    @Override
    public TravelPackage getTravelPackageById(int travelPackageId) {
        return travelPackageDAO.getTravelPackageById(travelPackageId);
    }

    @Override
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageDAO.getAllTravelPackages();
    }

    @Override
    public void printItinerary(int travelPackageId) {
        // Implement logic to print itinerary
    }

    @Override
    public void printPassengerList(int travelPackageId) {
        // Implement logic to print passenger list
    }
}
