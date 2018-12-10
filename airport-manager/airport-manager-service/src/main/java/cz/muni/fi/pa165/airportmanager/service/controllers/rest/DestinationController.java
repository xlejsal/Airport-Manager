package cz.muni.fi.pa165.airportmanager.service.controllers.rest;

import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/pa165/rest")
public class DestinationController {

    private final DestinationFacade facade;

    @Autowired
    public DestinationController(DestinationFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public DestinationDTO create(@RequestBody DestinationDTO destination) {
        return facade.createDestination(destination);
    }

    @PutMapping
    public DestinationDTO update(@RequestBody DestinationDTO destination) {
        return facade.updateDestination(destination);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facade.deleteDestination(id);
    }

    @GetMapping("/{id}")
    public DestinationDTO findById(@PathVariable Long id) {
        return facade.getDestinationById(id);
    }

    @GetMapping("/city/{city}")
    public Collection<DestinationDTO> findByCity(@PathVariable String city) {
        return facade.findCityDestinations(city);
    }

    @GetMapping("/country/{country}")
    public Collection<DestinationDTO> findByCountry(@PathVariable String country) {
        return facade.findCountryDestinations(country);
    }
}
