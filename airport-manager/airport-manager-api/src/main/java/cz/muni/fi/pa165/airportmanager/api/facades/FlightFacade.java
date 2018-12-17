package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;

import java.util.List;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
public interface FlightFacade {

    /**
     * Get all flights
     * @return list of all flightDTOs
     */
    List<FlightDTO> getAllFlights();

    /**
     * Get flight with set id
     * @param id the id of flight we are searching for
     * @return flightDTO with desired id or null if it does not exist
     */
    FlightDTO getFlightById(Long id);

    /**
     * Get flight with set flightNumber
     * @param flightNumber the flightNumber of flight we are searching for
     * @return flightDTO with desired flightNumber
     */
    FlightDTO getFlightByFlightNumber(String flightNumber);

    /**
     * Create flight entity in database
     * @param flight DTO of flight to be created
     */
    FlightDTO createFlight(FlightCreateDTO flight);

    /**
     * Update a flight
     * @param flight DTO of the flight to be updated
     * @return
     */
    FlightDTO updateFlight(FlightDTO flight);

    /**
     * Delete flight from database
     * @param id id of the flight to be deleted
     */
    void deleteFlight(Long id);

    /**
     * Add steward to flight
     * @param steward steward to be added
     * @param flight flight to add steward to
     */
    void addSteward(StewardDTO steward, FlightDTO flight);

    /**
     * Remove steward from flight
     * @param steward steward to be removed
     * @param flight flight from which steward is to be removed
     */
    void removeSteward(StewardDTO steward, FlightDTO flight);
}
