package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.AirplaneRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.service.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: check exceptions when retrieving shit?
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepo;

    @Autowired
    public AirplaneServiceImpl(AirplaneRepository airplaneRepo) {
        this.airplaneRepo = airplaneRepo;
    }

    @Override
    public List<AirplanePO> getAllAirplanes() {
        List<AirplanePO> airplanes = new ArrayList<AirplanePO>();
        airplaneRepo.findAll().forEach(airplanes::add);
        return airplanes;
    }

    @Override
    public AirplanePO getAirplaneById(Long id) {
        return airplaneRepo.findById(id).orElse(null);
    }

    @Override
    public AirplanePO createAirplane(AirplanePO airplane) { return airplaneRepo.save(airplane); }

    @Override
    public void deleteAirplane(Long id) {
        airplaneRepo.delete(airplaneRepo.findById(id).orElse(null));
    }

    @Override
    public AirplanePO findAirplaneByName(String name) { return airplaneRepo.findByName(name); }
}
