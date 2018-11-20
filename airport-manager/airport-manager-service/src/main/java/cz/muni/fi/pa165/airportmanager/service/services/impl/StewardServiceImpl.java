package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.StewardRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */
@Service
public class StewardServiceImpl implements StewardService {

    private final StewardRepository stewardRepo;

    @Autowired
    public StewardServiceImpl(StewardRepository stewardRepo) {
        this.stewardRepo = stewardRepo;
    }

    @Override
    public List<StewardPO> getAllStewards() {
        List<StewardPO> stewards = new ArrayList<StewardPO>();
        stewardRepo.findAll().forEach(stewards::add);
        return stewards;
    }

    @Override
    public StewardPO getStewardById(Long id) {
        return stewardRepo.findById(id).orElse(null);
    }

    @Override
    public StewardPO createSteward(StewardPO steward) {
        return stewardRepo.save(steward);
    }

    @Override
    public void deleteSteward(Long id) { stewardRepo.delete(stewardRepo.findById(id).orElse(null)); }
}
