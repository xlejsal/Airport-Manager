package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.FlightRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

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
        if (flightRepo.findAllFlightsFromToWithAirplaneId(flight.getDepartureTime(), flight.getArrivalTime(),
                flight.getAirplane().getId()).isEmpty()) {
            flightRepo.save(flight);
        } else {
            throw new IllegalArgumentException("Specified airplane already has a flight at the time of this flight.");
        }
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepo.delete(flightRepo.findById(id).orElse(null));
    }

    @Override
    public void addSteward(StewardPO steward, FlightPO flight) {
        for (FlightPO flightPO : steward.getFlights()) {
            if (flightPO.getDepartureTime().isAfter(flight.getArrivalTime()) &&
                    flightPO.getArrivalTime().isBefore(flight.getDepartureTime())) {
                continue;
            } else {
                throw new IllegalArgumentException("Steward already has a flight at the time of this flight.");
            }
        }
        Set<StewardPO> stewards = flight.getStewards();
        stewards.add(steward);
        flight.setStewards(stewards);
    }

    @Override
    public void removeSteward(StewardPO steward, FlightPO flight) {
        Set<StewardPO> stewards = flight.getStewards();
        stewards.remove(steward);
        flight.setStewards(stewards);
    }
}
