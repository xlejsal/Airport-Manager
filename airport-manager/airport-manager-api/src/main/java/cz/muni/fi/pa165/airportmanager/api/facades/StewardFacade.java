package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;

import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
public interface StewardFacade {

    List<StewardDTO> getAllStewards();

    StewardDTO getStewardById(Long id);

    StewardDTO createSteward(StewardDTO steward);

    void deleteSteward(Long id);
}