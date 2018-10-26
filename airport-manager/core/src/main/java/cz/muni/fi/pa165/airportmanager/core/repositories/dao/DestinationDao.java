package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Destination;

import java.util.List;

/**
 * @author kotrc
 * Created on 25.10.2018
 */

public interface DestinationDao {

    /**
     * Creates destination
     * @param destination
     */
    public void create(Destination destination);

    /**
     * Removes destination with param id
     * @param id
     */
    public void remove(Long id);

    /**
     * Finds destination with param id
     * @param id
     * @return destination with param id or null if there is no destination with param id
     */
    public Destination findById(Long id);

    /**
     * Finds all destinations
     * @return all destinations
     */
    public List<Destination> findAll();
}
