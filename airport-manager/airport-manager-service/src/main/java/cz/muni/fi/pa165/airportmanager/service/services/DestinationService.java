package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;

import java.util.List;

/**
 * TODO:javadoc
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public interface DestinationService {

    List<DestinationPO> getAllDestinations();

    DestinationPO getDestinationById(Long Id);

    DestinationPO createDestination(DestinationPO destination);

    void deleteDestination(Long Id);
}
