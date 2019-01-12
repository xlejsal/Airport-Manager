package cz.muni.fi.pa165.airportmanager.service.validators;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class AirplaneDTOValidator implements Validator {

    private AirplaneFacade facade;

    public AirplaneDTOValidator(AirplaneFacade airplaneFacade){
        facade = airplaneFacade;
    }

    @Override
    public boolean supports(Class<?> classy) {
        return AirplaneDTO.class.isAssignableFrom(classy);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AirplaneDTO plane = (AirplaneDTO) target;
        List<AirplaneDTO> planes = facade.getAllAirplanes();

        if(plane.getCapacity() < 0){
            errors.rejectValue("capacity", "invalid.capacity","Capacity below 0...");
        }

        for(AirplaneDTO ap : planes){
            if(ap.getName().equals(plane.getName())) {
                errors.rejectValue("name", "invalid.name","Name already exists!");
                break;
            }
        }
    }
}
