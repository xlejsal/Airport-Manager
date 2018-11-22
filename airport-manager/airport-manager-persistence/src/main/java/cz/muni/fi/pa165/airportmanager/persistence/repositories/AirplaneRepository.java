package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository class to enable CRUD operations and more on Airplane entities
 *
 * @author Stepan Benes
 * Created on 2018-10-28
 */
public interface AirplaneRepository extends JpaRepository<AirplanePO, Long> {
    public AirplanePO findByName(String name);
    public List<AirplanePO> findByCompany(String company);
}
