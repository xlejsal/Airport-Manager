package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;

import java.util.List;

/**
 * TODO:javadoc
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public interface DestinationFacade {

    List<DestinationDTO> getAllDestinations();

    DestinationDTO getDestinationById(Long Id);

    DestinationDTO createDestination(DestinationDTO destination);

    void deleteDestination(Long Id);
}
