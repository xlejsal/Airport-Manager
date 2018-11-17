package cz.muni.fi.pa165.airportmanager.core.dao.impl;

import cz.muni.fi.pa165.airportmanager.core.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.core.repositories.AirplaneRepository;
import cz.muni.fi.pa165.airportmanager.core.repositories.models.AirplanePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @see cz.muni.fi.pa165.airportmanager.core.dao.AirplaneDao
 *
 * @author Stepan Benes
 * Created on 2018-10-25
 */

@Repository
public class AirplaneDaoImpl implements AirplaneDao {

    @Autowired
    private AirplaneRepository repository;

    @Override
    public AirplanePO create(AirplanePO airplane){
        return repository.save(airplane);
    }

    @Override
    public AirplanePO findById(Long id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<AirplanePO> findAll(){
        List<AirplanePO> airplanes = new LinkedList<>();
        repository.findAll().forEach(airplanes::add);
        return airplanes;
    }

    @Override
    public AirplanePO findByName(String name){
        return repository.findFirstByName(name);
    }

    @Override
    public AirplanePO update(AirplanePO airplane){
        return repository.save(airplane);
    }

    @Override
    public void delete(AirplanePO airplane){
        repository.delete(airplane);
    }
}
