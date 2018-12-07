package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
public interface StewardService {

    List<StewardPO> getAllStewards();

    StewardPO getStewardById(Long id);

    StewardPO createSteward(StewardPO steward);

    StewardPO updateSteward(StewardPO steward);

    void deleteSteward(Long id);

    boolean isAvailableFromTo(Long id, LocalDateTime from, LocalDateTime to);
}
