package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-24
 */
public interface FlightRepository extends CrudRepository<FlightPO, Long> {
}
