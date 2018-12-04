package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-11-20
 */

@Service
public interface AirplaneService {

    /**
     * Gets all the airplanes
     * @return - a List of Airplane entities
     */
    List<AirplanePO> getAllAirplanes();

    /**
     * Gets an Airplane entity by its Id
     * @param Id - Id of the desired airplane
     * @return - desired airplane or null
     */
    AirplanePO getAirplaneById(Long Id);

    /**
     * Creates an Airplane entity
     * @param airplane - Airplane entity to be created and stored
     * @return
     */
    AirplanePO createAirplane(AirplanePO airplane);

    /**
     * Update an airplane
     * @param airplane - updated airplane
     * @return
     */
    AirplanePO updateAirplane(AirplanePO airplane);

    /**
     * Deletes an Airplane entity
     * @param Id - Id of the airplane to be deleted
     */
    void deleteAirplane(Long Id);

    /**
     * Finds an airplane by its unique name
     * @param name - unique name of the desired airplane
     * @return - Airplane entity of given name
     */
    AirplanePO findAirplaneByName(String name);

    /**
     * Finds all the airplanes belonging to the given company name
     * @param company - company name
     * @return - list of all the airplanes belonging to the given company
     */
    List<AirplanePO> findCompanyAirplanes(String company);
}
