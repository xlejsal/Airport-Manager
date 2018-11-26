package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.AirplaneRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.service.exceptions.AirportManagerDataAccessException;
import cz.muni.fi.pa165.airportmanager.service.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-11-20
 */

@Service
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepo;

    @Autowired
    public AirplaneServiceImpl(AirplaneRepository airplaneRepo) {
        this.airplaneRepo = airplaneRepo;
    }

    @Override
    public List<AirplanePO> getAllAirplanes() {
        List<AirplanePO> airplanes = airplaneRepo.findAll();
        return airplanes;
    }

    @Override
    public AirplanePO getAirplaneById(Long id) {
        return airplaneRepo.findById(id).orElseThrow(() -> new AirportManagerDataAccessException("Airplane of ID " + id + " does not exist"));
    }

    @Override
    public AirplanePO createAirplane(AirplanePO airplane) {
        try{
            return airplaneRepo.save(airplane);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot create Airplane", e);
        }
    }

    @Override
    public void deleteAirplane(Long id) {
        airplaneRepo.delete(airplaneRepo.findById(id).orElseThrow(() -> new AirportManagerDataAccessException("Airplane of ID " + id + " does not exist")));
    }

    @Override
    public AirplanePO findAirplaneByName(String name) {
        try{
            return airplaneRepo.findByName(name);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot find an Airplane by the name of " + name, e);
        }
    }

    @Override
    public List<AirplanePO> findCompanyAirplanes(String company){
        try{
            return airplaneRepo.findByCompany(company);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot find any Airplanes belonging to the Company " + company, e);
        }
    }
}
