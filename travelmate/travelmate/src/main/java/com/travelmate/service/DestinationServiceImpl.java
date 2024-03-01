package com.travelmate.service;

import com.travelmate.dao.DestinationDAO;
import com.travelmate.model.Destination;

import java.sql.SQLException;
import java.util.List;

public class DestinationServiceImpl implements DestinationService {

    private final DestinationDAO destinationDAO;

    public DestinationServiceImpl(DestinationDAO destinationDAO) {
        this.destinationDAO = destinationDAO;
    }

    @Override
    public List<Destination> getDestinationsByTravelPackageId(int travelPackageId) throws SQLException {
        return destinationDAO.getDestinationsByTravelPackageId(travelPackageId);
    }

    @Override
    public Destination getDestinationById(int id) {
        return destinationDAO.getDestinationById(id);
    }

    @Override
    public void addDestination(Destination destination) {
        destinationDAO.addDestination(destination);
    }

    @Override
    public void updateDestination(Destination destination) {
        destinationDAO.updateDestination(destination);
    }

    @Override
    public void deleteDestination(int id) {
        destinationDAO.deleteDestination(id);
    }

    @Override
    public List<Destination> getAllDestinations() {
        return destinationDAO.getAllDestination();
    }
}
