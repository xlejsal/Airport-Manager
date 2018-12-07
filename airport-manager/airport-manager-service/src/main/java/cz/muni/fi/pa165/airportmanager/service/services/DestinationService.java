package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-11-20
 */

@Service
public interface DestinationService {

    /**
     * Gets all the destinations
     * @return - a List of Destination entitites
     */
    List<DestinationPO> getAllDestinations();

    /**
     * Gets a Destination entity by its Id
     * @param Id - Id of the desired destination
     * @return - desired destination or null
     */
    DestinationPO getDestinationById(Long Id);

    /**
     * Creates a Destination entity
     * @param destination - Destination entity to be created and stored
     * @return
     */
    DestinationPO createDestination(DestinationPO destination);

    /**
     * Update a destination
     * @param destination - Destination to be updated
     * @return
     */
    DestinationPO updateDestination(DestinationPO destination);

    /**
     * Deletes a Destination entity
     * @param Id - Id of the destination to be deleted
     */
    void deleteDestination(Long Id);

    /**
     * Finds all the destinations belonging to a single city
     * @param city - desired city
     * @return - list of all the destinations
     */
    List<DestinationPO> findCityDestinations(String city);

    /**
     * Finds all the destinations belonging to a single country
     * @param country - desired country
     * @return - list of all the destinations
     */
    List<DestinationPO> findCountryDestinations(String country);
}
