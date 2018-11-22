package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-24
 */
public interface FlightRepository extends JpaRepository<FlightPO, Long> {
}
