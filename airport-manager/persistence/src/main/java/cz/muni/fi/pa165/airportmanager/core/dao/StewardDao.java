package cz.muni.fi.pa165.airportmanager.core.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.StewardPO;

import java.util.List;

/**
 * Simple DAO of Steward entity, allows
 * creation, removal, updating and retrieval.
 *
 * @author Stepan Benes
 * Created on 2018-10-25
 */

public interface StewardDao {
    /**
     * Create a Steward record
     * @param steward - given Steward
     */
    public StewardPO create(StewardPO steward);

    /**
     * Find a Steward by given Id
     * @param id - the Id of the sought after Steward
     * @return found Steward
     */
    public StewardPO findById(Long id);

    /**
     * Find and return all Stewards
     * @return a list of all Stewards
     */
    public List<StewardPO> findAll();

    /**
     * Update a Steward in the database with new given data
     * @param steward - the Steward being updated
     */
    public StewardPO update(StewardPO steward);

    /**
     * Remove the record of a Steward
     * @param steward - Steward that's to be removed
     */
    public void remove(StewardPO steward);
}
