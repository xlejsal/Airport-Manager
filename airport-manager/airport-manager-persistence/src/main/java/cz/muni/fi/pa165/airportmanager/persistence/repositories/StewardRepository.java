package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Stepan Benes
 * Created on 2018-10-28
 */
public interface StewardRepository extends CrudRepository<StewardPO, Long> {

}