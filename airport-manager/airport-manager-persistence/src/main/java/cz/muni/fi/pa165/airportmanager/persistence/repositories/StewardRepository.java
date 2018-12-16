package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Repository class to enable CRUD operations and more on Steward entities
 *
 * @author Stepan Benes
 * Created on 2018-10-28
 */
public interface StewardRepository extends JpaRepository<StewardPO, Long> {

}