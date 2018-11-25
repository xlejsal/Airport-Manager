package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.FlightRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.service.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepo;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    @Override
    public List<FlightPO> getAllFlights() {
        List<FlightPO> flights = flightRepo.findAll();
        return flights;
    }

    @Override
    public FlightPO getFlightById(Long id) {
        return flightRepo.findById(id).orElse(null);
    }

    @Override
    public FlightPO getFlightByFlightNumber(String flightNumber) {
        return flightRepo.findByFlightNumber(flightNumber);
    }

    @Override
    public void createFlight(FlightPO flight) {
        flightRepo.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepo.delete(flightRepo.findById(id).orElse(null));
    }
}
