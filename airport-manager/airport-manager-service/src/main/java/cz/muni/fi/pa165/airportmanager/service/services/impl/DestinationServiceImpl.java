package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.DestinationRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.service.exceptions.AirportManagerDataAccessException;
import cz.muni.fi.pa165.airportmanager.service.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: check exceptions when retrieving shit?
 * @author Stepan Benes
 * Created on 2018-11-20
 */

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepo;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepo) {
        this.destinationRepo = destinationRepo;
    }

    @Override
    public List<DestinationPO> getAllDestinations() {
        List<DestinationPO> destinations = destinationRepo.findAll();
        return destinations;
    }

    @Override
    public DestinationPO getDestinationById(Long id) { return destinationRepo.findById(id).orElseThrow(() -> new AirportManagerDataAccessException("Destination of the ID " + id + " does not exist")); }

    @Override
    public DestinationPO createDestination(DestinationPO destination) {
        try{
            return destinationRepo.save(destination);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot create Destination", e);
        }
    }

    @Override
    public void deleteDestination(Long id) {
        destinationRepo.delete(destinationRepo.findById(id).orElseThrow(() -> new AirportManagerDataAccessException("Destination of the ID " + id + " does not exist")));
    }

    @Override
    public List<DestinationPO> findCityDestinations(String city){
        try{
            return destinationRepo.findByCity(city);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot find Destinations in the city " + city, e);
        }
    }

    @Override
    public List<DestinationPO> findCountryDestinations(String country){
        try{
            return destinationRepo.findByCountry(country);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot find Destination in the country " + country, e);
        }
    }
}
