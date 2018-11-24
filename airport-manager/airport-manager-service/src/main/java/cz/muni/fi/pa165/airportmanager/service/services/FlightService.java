package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;

import java.util.List;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
public interface FlightService {

    List<FlightPO> getAllFlights();

    FlightPO getFlightById(Long id);

    FlightPO getFlightByFlightNumber(String flightNumber);

    FlightPO createFlight(FlightPO flight);

    void deleteFlight(Long id);
}
