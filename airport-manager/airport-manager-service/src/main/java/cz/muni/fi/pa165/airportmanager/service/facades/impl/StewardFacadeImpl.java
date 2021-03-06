package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
@Service
@Transactional
public class StewardFacadeImpl implements StewardFacade {

    private final StewardService stewardService;

    private final BeanMappingService mapper;

    @Autowired
    public StewardFacadeImpl(StewardService stewardService, BeanMappingService mapper) {
        this.stewardService = stewardService;
        this.mapper = mapper;
    }

    @Override
    public List<StewardDTO> getAllStewards() {
        return mapper.mapTo(stewardService.getAllStewards(), StewardDTO.class);
    }

    @Override
    public StewardDTO getStewardById(Long id) {
        return mapper.mapTo(stewardService.getStewardById(id), StewardDTO.class);
    }

    @Override
    public StewardDTO createSteward(StewardDTO steward) {
        return mapper.mapTo(stewardService.createSteward(mapper.mapTo(steward, StewardPO.class)), StewardDTO.class);
    }

    @Override
    public StewardDTO updateSteward(StewardDTO steward) {
        return mapper.mapTo(stewardService.updateSteward(mapper.mapTo(steward, StewardPO.class)), StewardDTO.class);
    }

    @Override
    public void deleteSteward(Long id) {
        stewardService.deleteSteward(id);
    }

    @Override
    public boolean isAvailableFromTo(Long id, LocalDateTime from, LocalDateTime to) {
        return stewardService.isAvailableFromTo(id, from, to);
    }

    @Override
    public List<FlightDTO> getFlightsOfSteward(Long id){
        return mapper.mapTo(stewardService.getFlightsOfSteward(id), FlightDTO.class);
    }

    @Override
    public List<StewardDTO> getAvailableStewardsFromTo(LocalDateTime from, LocalDateTime to) {
        return mapper.mapTo(stewardService.getAvailableStewardsFromTo(from, to), StewardDTO.class);
    }
}
