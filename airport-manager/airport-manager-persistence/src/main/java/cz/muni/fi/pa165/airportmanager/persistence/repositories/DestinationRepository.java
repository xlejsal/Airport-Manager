package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kotrc
 * Created on 28.10.2018
 */
public interface DestinationRepository extends CrudRepository<DestinationPO, Long> {
}
