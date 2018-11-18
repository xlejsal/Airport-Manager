package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistance.dao.StewardDao;
import cz.muni.fi.pa165.airportmanager.persistance.repositories.StewardRepository;
import cz.muni.fi.pa165.airportmanager.persistance.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
@Service
public class StewardServiceImpl implements StewardService {

    private final StewardDao stewardDao;

    @Autowired
    public StewardServiceImpl(StewardDao stewardDao) {
        this.stewardDao = stewardDao;
    }

    @Override
    public List<StewardPO> getAllStewards() {
        return stewardDao.findAll();
    }

    @Override
    public StewardPO getStewardById(Long id) {
        return stewardDao.findById(id);
    }

    @Override
    public StewardPO createSteward(StewardPO steward) {
        return stewardDao.create(steward);
    }

    @Override
    public void deleteSteward(Long id) {
        stewardDao.delete(stewardDao.findById(id));
    }
}
