package cz.muni.fi.pa165.airportmanager.persistance.dao.impl;

import cz.muni.fi.pa165.airportmanager.persistance.repositories.DestinationRepository;
import cz.muni.fi.pa165.airportmanager.persistance.dao.DestinationDao;
import cz.muni.fi.pa165.airportmanager.persistance.repositories.models.DestinationPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kotrc
 * Created on 25.10.2018
 */
@Repository
public class DestinationDaoImpl implements DestinationDao {

    @Autowired
    private DestinationRepository repository;

    @Override
    public DestinationPO create(DestinationPO destinationPO) {
        return repository.save(destinationPO);
    }

    @Override
    public void delete(DestinationPO destinationPO) {
        repository.delete(destinationPO);
    }

    @Override
    public DestinationPO update(DestinationPO destinationPO) {
        return repository.save(destinationPO);
    }

    @Override
    public DestinationPO findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DestinationPO> findAll() {
        List<DestinationPO> allDestinations = new LinkedList<>();
        repository.findAll().forEach(allDestinations::add);
        return allDestinations;
    }
}
