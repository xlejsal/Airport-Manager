package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import cz.muni.fi.pa165.airportmanager.persistance.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public StewardFacadeImpl(StewardService stewardService) {
        this.stewardService = stewardService;
    }

    @Override
    public List<StewardDTO> getAllStewards() {
        return stewardService.getAllStewards()
                .stream()
                .map(StewardPO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StewardDTO getStewardById(Long id) {
        return stewardService.getStewardById(id).toDTO();
    }

    @Override
    public StewardDTO createSteward(StewardDTO steward) {
        return stewardService.createSteward(StewardPO.of(steward)).toDTO();
    }

    @Override
    public void deleteSteward(Long id) {
        stewardService.deleteSteward(id);
    }
}
