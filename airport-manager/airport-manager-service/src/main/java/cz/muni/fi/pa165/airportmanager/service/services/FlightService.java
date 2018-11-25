package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;

import java.util.List;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
public interface FlightService {

    /**
     * Get all flights
     * @return list of all flight entities
     */
    List<FlightPO> getAllFlights();

    /**
     * Get flight entity with set id
     * @param id id of the desired flight
     * @return flight with desired id or null if it doe not exist
     */
    FlightPO getFlightById(Long id);

    /**
     * Get flight entity with set flightNumber
     * @param flightNumber flightNumber of desired flight
     * @return flight with desired flightNumber
     */
    FlightPO getFlightByFlightNumber(String flightNumber);

    /**
     * Create flight entity
     * @param flight flight entity to be created
     */
    void createFlight(FlightPO flight);

    /**
     * Delete flight with set id
     * @param id id of the flight to be deleted
     */
    void deleteFlight(Long id);
}