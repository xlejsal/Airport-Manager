package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author kotrc
 * Created on 23.11.2018
 */
public class FlightFacadeImpl implements FlightFacade {

    private FlightService flightService;
    private BeanMappingService beanMappingService;

    @Autowired
    public FlightFacadeImpl(FlightService flightService, BeanMappingService beanMappingService) {
        this.flightService = flightService;
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
    public void createFlight(FlightDTO flight) {
        flightService.createFlight(beanMappingService.mapTo(flight, FlightPO.class));
    }

    @Override
    public void deleteFlight(Long id) {
        flightService.deleteFlight(id);
    }
}
