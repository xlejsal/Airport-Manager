package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;

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
    FlightPO createFlight(FlightPO flight);

    /**
     * Update flight
     * @param flight flight entity to be updated
     * @return
     */
    FlightPO updateFlight(FlightPO flight);

    /**
     * Delete flight with set id
     * @param id id of the flight to be deleted
     */
    void deleteFlight(Long id);

    /**
     * Add steward to flight
     * @param steward steward to be added
     * @param flight flight to add steward to
     */
    void addSteward(StewardPO steward, FlightPO flight);

    /**
     * Remove steward from flight
     * @param steward steward to be removed
     * @param flight flight from which steward is to be removed
     */
    void removeSteward(StewardPO steward, FlightPO flight);
}
