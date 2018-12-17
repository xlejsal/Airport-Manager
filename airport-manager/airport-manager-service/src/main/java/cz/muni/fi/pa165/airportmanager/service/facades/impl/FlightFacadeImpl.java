package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
@Service
@Transactional
public class FlightFacadeImpl implements FlightFacade {

    private FlightService flightService;
    private AirplaneService airplaneService;
    private DestinationService destinationService;
    private StewardService stewardService;
    private BeanMappingService beanMappingService;

    @Autowired
    public FlightFacadeImpl(FlightService flightService, BeanMappingService beanMappingService,
                            AirplaneService airplaneService, DestinationService destinationService,
                            StewardService stewardService) {
        this.flightService = flightService;
        this.airplaneService = airplaneService;
        this.destinationService = destinationService;
        this.stewardService = stewardService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return beanMappingService.mapTo(flightService.getAllFlights(), FlightDTO.class);
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return beanMappingService.mapTo(flightService.getFlightById(id), FlightDTO.class);
    }

    @Override
    public FlightDTO getFlightByFlightNumber(String flightNumber) {
        return beanMappingService.mapTo(flightService.getFlightByFlightNumber(flightNumber), FlightDTO.class);
    }

    @Override
    public FlightDTO createFlight(FlightCreateDTO flight) {
        FlightPO mappedFlight = beanMappingService.mapTo(flight, FlightPO.class);
        mappedFlight.setAirplane(airplaneService.getAirplaneById(flight.getAirplaneId()));
        mappedFlight.setOrigin(destinationService.getDestinationById(flight.getOriginId()));
        mappedFlight.setDestination(destinationService.getDestinationById(flight.getDestinationId()));
        Set<StewardPO> stewards = new HashSet<>();
        for (Long id : flight.getStewardIds()) {
            stewards.add(stewardService.getStewardById(id));
        }
        mappedFlight.setStewards(stewards);
        FlightPO newFlight = flightService.createFlight(mappedFlight);
        return beanMappingService.mapTo(newFlight, FlightDTO.class);
    }

    @Override
    public FlightDTO updateFlight(FlightDTO flight) {
        return beanMappingService.mapTo(flightService.updateFlight(beanMappingService.mapTo(flight, FlightPO.class)),
                FlightDTO.class);
    }

    @Override
    public void deleteFlight(Long id) {
        flightService.deleteFlight(id);
    }

    @Override
    public void addSteward(StewardDTO steward, FlightDTO flight) {
        flightService.addSteward(beanMappingService.mapTo(steward, StewardPO.class),
                beanMappingService.mapTo(flight, FlightPO.class));
    }

    @Override
    public void removeSteward(StewardDTO steward, FlightDTO flight) {
        flightService.removeSteward(beanMappingService.mapTo(steward, StewardPO.class),
                beanMappingService.mapTo(flight, FlightPO.class));
    }
}
