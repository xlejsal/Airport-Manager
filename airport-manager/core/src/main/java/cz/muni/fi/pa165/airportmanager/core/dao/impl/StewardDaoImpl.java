package cz.muni.fi.pa165.airportmanager.core.dao.impl;

import cz.muni.fi.pa165.airportmanager.core.dao.StewardDao;
import cz.muni.fi.pa165.airportmanager.core.repositories.StewardRepository;
import cz.muni.fi.pa165.airportmanager.core.repositories.models.StewardPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @see cz.muni.fi.pa165.airportmanager.core.dao.StewardDao
 *
 * @author Stepan Benes
 * Created on 2018-10-25
 */

@Repository
public class StewardDaoImpl implements StewardDao {

    @Autowired
    private StewardRepository repository;

    @Override
    public void create(StewardPO s){
        repository.save(s);
    }

    @Override
    public StewardPO findById(Long id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<StewardPO> findAll(){
        List<StewardPO> stewards = new LinkedList<>();
        repository.findAll().forEach(stewards::add);
        return stewards;
    }

    @Override
    public void updateGender(Long id, String g){
        StewardPO s = findById(id);
        s.setGender(g);
        repository.save(s);
    }

    @Override
    public void remove(StewardPO s){
        repository.delete(s);
    }
}
