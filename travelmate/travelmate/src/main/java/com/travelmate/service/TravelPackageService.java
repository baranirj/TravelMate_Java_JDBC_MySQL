package com.travelmate.service;

import com.travelmate.model.TravelPackage;

import java.util.List;

public interface TravelPackageService {

    void createTravelPackage(TravelPackage travelPackage);

    void updateTravelPackage(TravelPackage travelPackage);

    void deleteTravelPackage(int travelPackageId);

    TravelPackage getTravelPackageById(int travelPackageId);

    List<TravelPackage> getAllTravelPackages();

    void printItinerary(int travelPackageId);

    void printPassengerList(int travelPackageId);
}
