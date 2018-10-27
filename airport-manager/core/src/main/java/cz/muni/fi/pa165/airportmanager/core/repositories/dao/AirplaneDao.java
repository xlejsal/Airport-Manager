package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Airplane;

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
     * @param a - given Airplane
     */
    public void create(Airplane a);

    /**
     * Find an Airplane by its Id
     * @param id - the id of the desired Airplane
     * @return found Airplane
     */
    public Airplane findById(Long id);

    /**
     * Find and return all of the Airplanes
     * @return - a list of all Airplanes
     */
    public List<Airplane> findAll();

    /**
     * Find an Airplane by its unique name
     * @param n - the sought after name
     * @return found Airplane
     */
    public Airplane findByName(String n);

    /**
     * Airplanes sometimes change hands,
     * this method reflects that
     * @param id - id of the Airplane that is being traded
     * @param c the name of the new owner company
     */
    public void updateCompany(Long id, String c);

    /**
     * Remove an Airplane record
     * @param id - the id of the Airplane to be removed
     */
    public void remove(Long id);
}
