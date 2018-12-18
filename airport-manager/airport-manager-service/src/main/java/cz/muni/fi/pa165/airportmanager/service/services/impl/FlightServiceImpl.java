package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.FlightRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.exceptions.AirportManagerDataAccessException;
import cz.muni.fi.pa165.airportmanager.service.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepo;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    @Override
    public List<FlightPO> getAllFlights() {
        return flightRepo.findAll();
    }

    @Override
    public FlightPO getFlightById(Long id) {
        return flightRepo.findById(id).orElseThrow(() ->
                new AirportManagerDataAccessException("Flight with id: " + id + ", does not exist"));
    }

    @Override
    public FlightPO getFlightByFlightNumber(String flightNumber) {
        try {
            return flightRepo.findByFlightNumber(flightNumber);
        } catch (DataAccessException e) {
            throw new AirportManagerDataAccessException("Flight with flight number: " + flightNumber + ", does not exist");
        }
    }

    @Override
    public FlightPO createFlight(FlightPO flight) {
        if (flightRepo.findAllFlightsFromToWithAirplaneId(flight.getDepartureTime(), flight.getArrivalTime(),
                flight.getAirplane().getId()).isEmpty()) {
            for (StewardPO steward : flight.getStewards()) {
                for (FlightPO flightPO : steward.getFlights()) {
                    if ((flightPO.getDepartureTime().isBefore(flight.getArrivalTime()) &&
                            flightPO.getDepartureTime().isAfter(flight.getDepartureTime())) ||
                            flightPO.getArrivalTime().isBefore(flight.getArrivalTime()) &&
                                    flightPO.getArrivalTime().isAfter(flight.getDepartureTime()) ||
                            flightPO.getArrivalTime().isEqual(flight.getDepartureTime()) ||
                            flightPO.getDepartureTime().isEqual(flight.getArrivalTime()) ||
                            flightPO.getDepartureTime().isEqual(flight.getDepartureTime()) ||
                            flightPO.getArrivalTime().isEqual(flight.getArrivalTime())) {
                        throw new IllegalArgumentException("Steward " + steward.getName() + " " + steward.getSurname() +
                                " already has a flight at the time of this flight.");
                    }
                }
            }
            for (FlightPO f : flightRepo.findAll()) {
                if (f.getFlightNumber().equals(flight.getFlightNumber())) {
                    throw new IllegalArgumentException("Flight with this number already exists.");
                }
            }
            return flightRepo.save(flight);
        } else {
            throw new IllegalArgumentException("Specified airplane already has a flight at the time of this flight.");
        }
    }

    @Override
    public FlightPO updateFlight(FlightPO flight) {
        List<FlightPO> flights = flightRepo.findAllFlightsFromToWithAirplaneId(flight.getDepartureTime(),
                                            flight.getArrivalTime(), flight.getAirplane().getId());
        if (flights.isEmpty() || (flights.size() == 1 && flights.get(0).equals(flight))) {
            return flightRepo.save(flight);
        } else {
            throw new IllegalArgumentException("Specified airplane already has a flight at the time of this flight.");
        }
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepo.delete(flightRepo.findById(id).orElseThrow(() ->
                new AirportManagerDataAccessException("Flight with ID: " + id + " does not exist")));
    }

    @Override
    public void addSteward(StewardPO steward, FlightPO flight) {
        for (FlightPO flightPO : steward.getFlights()) {
            if ((flightPO.getDepartureTime().isBefore(flight.getArrivalTime()) &&
                    flightPO.getDepartureTime().isAfter(flight.getDepartureTime())) ||
                    flightPO.getArrivalTime().isBefore(flight.getArrivalTime()) &&
                            flightPO.getArrivalTime().isAfter(flight.getDepartureTime()) ||
                    flightPO.getArrivalTime().isEqual(flight.getDepartureTime()) ||
                    flightPO.getDepartureTime().isEqual(flight.getArrivalTime()) ||
                    flightPO.getDepartureTime().isEqual(flight.getDepartureTime()) ||
                    flightPO.getArrivalTime().isEqual(flight.getArrivalTime())) {
                throw new IllegalArgumentException("Steward already has a flight at the time of this flight.");
            }
        }
        Set<StewardPO> stewards = flight.getStewards();
        stewards.add(steward);
        flight.setStewards(stewards);
        flightRepo.save(flight);
    }

    @Override
    public void removeSteward(StewardPO steward, FlightPO flight) {
        Set<StewardPO> stewards = flight.getStewards();
        stewards.remove(steward);
        flight.setStewards(stewards);
        flightRepo.save(flight);
    }
}
