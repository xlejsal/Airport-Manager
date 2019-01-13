package cz.muni.fi.pa165.airportmanager.service.validators;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneUpdateDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class AirplaneUpdateDTOValidator implements Validator {

    private AirplaneFacade facade;

    public AirplaneUpdateDTOValidator(AirplaneFacade airplaneFacade){
        facade = airplaneFacade;
    }

    @Override
    public boolean supports(Class<?> classy) {
        return AirplaneUpdateDTO.class.isAssignableFrom(classy);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AirplaneUpdateDTO plane = (AirplaneUpdateDTO) target;
        
        if(plane.getCapacity() < 0){
            errors.rejectValue("capacity", "invalid.capacity","Capacity below 0...");
        }
    }
}