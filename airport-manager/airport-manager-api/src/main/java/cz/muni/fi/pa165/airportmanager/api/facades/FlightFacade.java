package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;

import java.util.List;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
public interface FlightFacade {

    List<FlightDTO> getAllFlights();

    FlightDTO getFlightById(Long Id);

    FlightDTO getFlightByFlightNumber(String flightNumber);

    FlightDTO createFlight(FlightDTO flight);

    void deleteFlight(Long Id);
}
