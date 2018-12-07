package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
public interface StewardFacade {

    List<StewardDTO> getAllStewards();

    StewardDTO getStewardById(Long id);

    StewardDTO createSteward(StewardDTO steward);

    StewardDTO updateSteward(StewardDTO steward);

    void deleteSteward(Long id);

    boolean isAvailableFromTo(Long id, LocalDateTime from, LocalDateTime to);
}
