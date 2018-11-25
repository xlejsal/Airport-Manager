package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import org.dozer.Mapper;
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

    private final Mapper mapper;

    @Autowired
    public StewardFacadeImpl(StewardService stewardService, Mapper mapper) {
        this.stewardService = stewardService;
        this.mapper = mapper;
    }

    @Override
    public List<StewardDTO> getAllStewards() {
        return stewardService.getAllStewards()
                .stream()
                .map(s -> mapper.map(s, StewardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StewardDTO getStewardById(Long id) {
        return mapper.map(stewardService.getStewardById(id), StewardDTO.class);
    }

    @Override
    public StewardDTO createSteward(StewardDTO steward) {
        return mapper.map(stewardService.createSteward(mapper.map(steward, StewardPO.class)), StewardDTO.class);
    }

    @Override
    public void deleteSteward(Long id) {
        stewardService.deleteSteward(id);
    }

    @Override
    public boolean isAvailableFromTo(Long id, LocalDateTime from, LocalDateTime to) {
        return stewardService.isAvailableFromTo(id, from, to);
    }
}
