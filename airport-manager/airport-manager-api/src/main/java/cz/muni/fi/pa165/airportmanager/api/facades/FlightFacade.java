package cz.muni.fi.pa165.airportmanager.api.facades;

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
    void createFlight(FlightDTO flight);

    /**
     * Delete flight from database
     * @param id id of the flight to be deleted
     */
    void deleteFlight(Long id);

    void addSteward(StewardDTO steward, FlightDTO flight);

    void removeSteward(StewardDTO steward, FlightDTO flight);
}
