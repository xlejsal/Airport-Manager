package cz.muni.fi.pa165.airportmanager.persistance.dao;

import cz.muni.fi.pa165.airportmanager.persistance.repositories.models.DestinationPO;

import java.util.List;

/**
 * @author kotrc
 * Created on 25.10.2018
 */

public interface DestinationDao {

    /**
     * Creates destinationPO and saves it to database
     * @param destinationPO
     * @return the saved destination
     * */
    DestinationPO create(DestinationPO destinationPO);

    /**
     * Deletes destination from database
     * @param destinationPO
     */
    void delete(DestinationPO destinationPO);

    /**
     * Updates the destination in database
     * @param destinationPO
     * @return updated destination
     */
    DestinationPO update(DestinationPO destinationPO);

    /**
     * Finds destination with param id in database
     * @param id
     * @return destination with param id or null if there is no destination with param id
     */
    DestinationPO findById(Long id);

    /**
     * Finds all destinations in database
     * @return all destinations
     */
    List<DestinationPO> findAll();
}
