package cz.muni.fi.pa165.airportmanager.core.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.AirplanePO;

import java.util.List;

/**
 * Simple DAO of Airplane entity, allows
 * creation, removal, updating and retrieval.
 *
 * @author Stepan Benes
 * Created on 2018-10-25
 */

public interface AirplaneDao {
    /**
     * Create an Airplane record
     * @param airplane - given Airplane
     */
    public AirplanePO create(AirplanePO airplane);

    /**
     * Find an Airplane by its Id
     * @param id - the id of the desired Airplane
     * @return found Airplane
     */
    public AirplanePO findById(Long id);

    /**
     * Find and return all of the Airplanes
     * @return - a list of all Airplanes
     */
    public List<AirplanePO> findAll();

    /**
     * Find an Airplane by its unique name
     * @param name - the sought after name
     * @return found Airplane
     */
    public AirplanePO findByName(String name);

    /**
     * Update an Airplane in the database with new given data
     * @param airplane - the Airplane that is being updated
     */
    public AirplanePO update(AirplanePO airplane);

    /**
     * Remove an Airplane record
     * @param airplane - the Airplane to be removed
     */
    public void delete(AirplanePO airplane);
}
