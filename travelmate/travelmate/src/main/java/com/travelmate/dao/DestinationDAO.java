package com.travelmate.dao;


import com.travelmate.model.Destination;

import java.sql.SQLException;
import java.util.List;

public interface DestinationDAO {
    List<Destination> getDestinationsByTravelPackageId(int travelPackageId) throws SQLException;
    Destination getDestinationById(int id);
    void addDestination(Destination destination);
    void updateDestination(Destination destination);
    void deleteDestination(int id);

    List<Destination> getAllDestination();
}
