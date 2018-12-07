package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;

import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public interface DestinationFacade {

    /**
     * Get all the destinations
     * @return - a list of destination DTOs
     */
    List<DestinationDTO> getAllDestinations();

    /**
     * Find a specific destination by its Id
     * @param Id - Id of the desired destination
     * @return - DTO of the destination
     */
    DestinationDTO getDestinationById(Long Id);

    /**
     * Create a destination entity in the database
     * @param destination - DTO of a destination to be created
     * @return - DTO of the created destination
     */
    DestinationDTO createDestination(DestinationDTO destination);

    /**
     * Update a destination
     * @param destination - DTO of the destination to be updated
     * @return - updated DTO
     */
    DestinationDTO updateDestination(DestinationDTO destination);

    /**
     * Delete a destination from the database
     * @param Id - Id of the destination to be deleted
     */
    void deleteDestination(Long Id);

    /**
     * Find a list of destinations in a single city
     * @param city - desired city
     * @return - a list of destination DTOs
     */
    List<DestinationDTO> findCityDestinations(String city);

    /**
     * Find a list of destination in a single country
     * @param country - desired country
     * @return - a list of destination DTOs
     */
    List<DestinationDTO> findCountryDestinations(String country);
}
