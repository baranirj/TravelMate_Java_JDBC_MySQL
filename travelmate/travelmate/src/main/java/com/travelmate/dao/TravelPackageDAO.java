package com.travelmate.dao;


import com.travelmate.model.TravelPackage;

import java.util.List;

public interface TravelPackageDAO {
    List<TravelPackage> getAllTravelPackages();
    TravelPackage getTravelPackageById(int id);
    void addTravelPackage(TravelPackage travelPackage);
    void updateTravelPackage(TravelPackage travelPackage);
    void deleteTravelPackage(int id);
}
