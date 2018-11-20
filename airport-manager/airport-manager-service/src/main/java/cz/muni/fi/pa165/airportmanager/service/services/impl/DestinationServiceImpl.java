package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.DestinationRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.service.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: check exceptions when retrieving shit?
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepo;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepo) {
        this.destinationRepo = destinationRepo;
    }

    @Override
    public List<DestinationPO> getAllDestinations() {
        List<DestinationPO> destinations = new ArrayList<DestinationPO>();
        destinationRepo.findAll().forEach(destinations::add);
        return destinations;
    }

    @Override
    public DestinationPO getDestinationById(Long id) { return destinationRepo.findById(id).orElse(null); }

    @Override
    public DestinationPO createDestination(DestinationPO destination) { return destinationRepo.save(destination); }

    @Override
    public void deleteDestination(Long id) {
        destinationRepo.delete(destinationRepo.findById(id).orElse(null));
    }
}
