package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author kotrc
 * Created on 28.10.2018
 */
public interface DestinationRepository extends JpaRepository<DestinationPO, Long> {
    public List<DestinationPO> findByCity(String city);
    public List<DestinationPO> findByCountry(String country);
}
