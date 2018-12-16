package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
public interface StewardFacade {

    /**
     * Get all stewards
     * @return list of all stewardDTOs
     */
    List<StewardDTO> getAllStewards();

    /**
     * Get a steward with specified id
     * @param id id of the desired steward
     * @return steward with specified id
     */
    StewardDTO getStewardById(Long id);

    /**
     * Creates steward entity in database
     * @param steward DTO of steward to be created
     * @return DTO of the created steward
     */
    StewardDTO createSteward(StewardDTO steward);

    /**
     * Update steward
     * @param steward DTO of the steward to be updated
     * @return updated stewardDTO
     */
    StewardDTO updateSteward(StewardDTO steward);

    /**
     * Delete steward with specified id
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
     * Get the list of flights of a certain steward
     * @param id id of the steward
     * @return a list of flights
     */
    List<FlightDTO> getFlightsOfSteward(Long id);
}
