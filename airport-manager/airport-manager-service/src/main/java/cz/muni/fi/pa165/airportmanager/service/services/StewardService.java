package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistance.repositories.models.StewardPO;

import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
public interface StewardService {

    List<StewardPO> getAllStewards();

    StewardPO getStewardById(Long id);

    StewardPO createSteward(StewardPO steward);

    void deleteSteward(Long id);
}
