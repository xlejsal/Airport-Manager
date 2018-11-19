package cz.muni.fi.pa165.airportmanager.persistance.dao.impl;

import cz.muni.fi.pa165.airportmanager.persistance.dao.FlightDao;
import cz.muni.fi.pa165.airportmanager.persistance.repositories.FlightRepository;
import cz.muni.fi.pa165.airportmanager.persistance.repositories.models.FlightPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-28
 */
@Repository
public class FlightDaoImpl implements FlightDao {

    @Autowired
    private FlightRepository repository;

    @Override
    public FlightPO findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public FlightPO create(FlightPO flight) {
        return repository.save(flight);
    }

    @Override
    public FlightPO update(FlightPO flight) {
        return repository.save(flight);
    }

    @Override
    public void delete(FlightPO flight) {
        repository.delete(flight);
    }

    @Override
    public List<FlightPO> findAll() {
        List<FlightPO> allFlights = new LinkedList<>();
        repository.findAll().forEach(allFlights::add);
        return allFlights;
    }
}
