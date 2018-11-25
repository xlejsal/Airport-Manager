package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;

import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public interface AirplaneFacade {

    /**
     * Get all of the airplanes
     * @return - a list of airplane DTOs
     */
    List<AirplaneDTO> getAllAirplanes();

    /**
     * Get a single airplane by its Id
     * @param Id - Id of the desired airplane
     * @return - a single airplane DTO
     */
    AirplaneDTO getAirplaneById(Long Id);

    /**
     * Create an airplane in the database
     * @param airplane - DTO of an airplane to be created
     */
    void createAirplane(AirplaneDTO airplane);

    /**
     * Delete an airplane for the database
     * @param Id - Id of the airplane to be deleted
     */
    void deleteAirplane(Long Id);

    /**
     * Find a single airplane by its unique name
     * @param name - name of the airplane to be found
     * @return - the desired airplane
     */
    AirplaneDTO findAirplaneByName(String name);

    /**
     * Find the list of airplanes belonging to a company
     * @param company - desired company
     * @return - a list of all the airplanes
     */
    List<AirplaneDTO> findCompanyAirplanes(String company);
}
