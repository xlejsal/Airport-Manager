package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the DestinationFacade interface
 * @author Stepan Benes
 * Created on 2018-11-20
 */

@Service
@Transactional
public class DestinationFacadeImpl implements DestinationFacade {

    private DestinationService destinationService;
    private BeanMappingService beanMapper;

    @Autowired
    public DestinationFacadeImpl(DestinationService service, BeanMappingService mapper){
        destinationService = service;
        beanMapper = mapper;
    }

    @Override
    public List<DestinationDTO> getAllDestinations(){
        return beanMapper.mapTo(destinationService.getAllDestinations(), DestinationDTO.class);
    }

    @Override
    public DestinationDTO getDestinationById(Long Id){
        return beanMapper.mapTo(destinationService.getDestinationById(Id), DestinationDTO.class);
    }

    @Override
    public DestinationDTO findDestinationByAirportCode(String airportCode) {
        return beanMapper.mapTo(destinationService.findByAirportCode(airportCode), DestinationDTO.class);
    }

    @Override
    public DestinationDTO createDestination(DestinationDTO destination){
        return beanMapper.mapTo(destinationService.createDestination(beanMapper.mapTo(destination, DestinationPO.class)),
                DestinationDTO.class);
    }

    @Override
    public DestinationDTO updateDestination(DestinationDTO destination){
        return beanMapper.mapTo(destinationService.updateDestination(beanMapper.mapTo(destination, DestinationPO.class)),
                DestinationDTO.class);
    }

    @Override
    public void deleteDestination(Long Id){
        destinationService.deleteDestination(Id);
    }

    @Override
    public List<DestinationDTO> findCityDestinations(String city){
        return beanMapper.mapTo(destinationService.findCityDestinations(city), DestinationDTO.class);
    }

    @Override
    public List<DestinationDTO> findCountryDestinations(String country){
        return beanMapper.mapTo(destinationService.findCountryDestinations(country), DestinationDTO.class);
    }
}
