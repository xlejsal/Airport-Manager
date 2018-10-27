package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Steward;

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
     * @param s - given Steward
     */
    public void create(Steward s);

    /**
     * Find a Steward by given Id
     * @param id - the Id of the sought after Steward
     * @return found Steward
     */
    public Steward findById(Long id);

    /**
     * Find and return all Stewards
     * @return a list of all Stewards
     */
    public List<Steward> findAll();

    /**
     * A method for all the genderfluid folk
     * @param id - the Id of the Steward that's subject to a change
     * @param g - new gender to assign
     */
    public void updateGender(Long id, String g);

    /**
     * Remove the record of a Steward
     * @param id - Id of the Steward that's to be removed
     */
    public void remove(Long id);
}
