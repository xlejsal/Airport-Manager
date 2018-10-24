package cz.muni.fi.pa165.airportmanager.core.repositories;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.FlightDAO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-24
 */
public interface FlightRepository extends CrudRepository<FlightDAO, Long> {
}
