package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Stepan Benes
 * Created on 2018-10-28
 */
public interface AirplaneRepository extends CrudRepository<AirplanePO, Long> {
    public AirplanePO findFirstByName(String name);
}
