package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
public interface StewardService {

    /**
     * Get all the stewards
     * @return list of all steward entities
     */
    List<StewardPO> getAllStewards();

    /**
     * Gets steward entity by its id
     * @param id id of the desired steward
     * @return desired steward or null
     */
    StewardPO getStewardById(Long id);

    /**
     * Creates steward entity
     * @param steward steward entity to be created
     * @return created steward
     */
    StewardPO createSteward(StewardPO steward);

    /**
     * Updates steward
     * @param steward updated steward
     * @return updated steward
     */
    StewardPO updateSteward(StewardPO steward);

    /**
     * Deletes steward entity
     * @param id id of the steward to be deleted
     */
    void deleteSteward(Long id);

    /**
     * Checks if the steward is available in the specified time frame
     * @param id id of the steward
     * @param from the time when he should be available from
     * @param to the time to which he should be available
     * @return true if steward is available in set time, false if not
     */
    boolean isAvailableFromTo(Long id, LocalDateTime from, LocalDateTime to);

    /**
     * Get the list of flight for a certain steward
     * @param id - id of the steward
     * @return - a list of flights
     */
    List<FlightPO> getFlightsOfSteward(Long id);
}
