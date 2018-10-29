package cz.muni.fi.pa165.airportmanager.core.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.FlightPO;

import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-28
 */
public interface FlightDao {

    /**
     * Finds the flight with the specified id in the database.
     * @param id Flight's id.
     * @return Returns flight with specified id.
     */
    FlightPO findById(long id);

    /**
     * Saves the flight to the database.
     * @param flight Flight to be saved.
     * @return Returns the saved flight.
     */
    FlightPO create(FlightPO flight);

    /**
     * Updates the flight with the same id in the database with the data of the given flight.
     * @param flight To be updated.
     * @return Returns the updated flight.
     */
    FlightPO update(FlightPO flight);

    /**
     * Deletes the flight from the database.
     * @param flight Flight to be deleted.
     */
    void delete(FlightPO flight);

    /**
     * Finds all flights saved in the database.
     * @return Returns list of all flights.
     */
    List<FlightPO> findAll();
}
