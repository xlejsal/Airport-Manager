package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.StewardRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.exceptions.AirportManagerDataAccessException;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
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
        return stewardRepo.findAll();
    }

    @Override
    public StewardPO getStewardById(Long id) {
        return stewardRepo.findById(id).orElseThrow(() ->
                new AirportManagerDataAccessException("Steward with specified id: " + id + ", does not exist"));
    }

    @Override
    public StewardPO createSteward(StewardPO steward) {
        try{
            return stewardRepo.save(steward);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot create Steward", e);
        }
    }

    @Override
    public StewardPO updateSteward(StewardPO steward) {
        try{
            return stewardRepo.save(steward);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot update Steward", e);
        }
    }

    @Override
    public void deleteSteward(Long id) {
        stewardRepo.delete(stewardRepo.findById(id).orElseThrow(() ->
                new AirportManagerDataAccessException("Steward with id: " + id + ", does not exist")));
    }

    @Override
    public boolean isAvailableFromTo(Long id, LocalDateTime from, LocalDateTime to) {
        StewardPO steward = stewardRepo.findById(id).orElseThrow(() ->
                new AirportManagerDataAccessException("Steward with id: " + id + ", does not exist"));
        for (FlightPO flight : steward.getFlights()) {
            LocalDateTime depTime = flight.getDepartureTime();
            LocalDateTime arrTime = flight.getArrivalTime();
            if (depTime.isAfter(from) && depTime.isBefore(to) || arrTime.isAfter(from) && arrTime.isBefore(to)
                    || depTime.isBefore(from) && arrTime.isAfter(to) || depTime.isEqual(from) || depTime.isEqual(to)
                    || arrTime.isEqual(from) || arrTime.isEqual(to)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<FlightPO> getFlightsOfSteward(Long id){
        return new ArrayList<FlightPO>(getStewardById(id).getFlights());
    }

    @Override
    public List<StewardPO> getAvailableStewardsFromTo(LocalDateTime from, LocalDateTime to) {
        List<StewardPO> availableStewards = new LinkedList<>();
        for (StewardPO steward : stewardRepo.findAll()) {
            if (isAvailableFromTo(steward.getId(), from, to)) {
                availableStewards.add(steward);
            }
        }
        return availableStewards;
    }
}
