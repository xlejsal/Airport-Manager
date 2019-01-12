package cz.muni.fi.pa165.airportmanager.service.validators;

import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class DestinationDTOValidator implements Validator {

    private DestinationFacade facade;

    public DestinationDTOValidator(DestinationFacade destinationFacade){
        facade = destinationFacade;
    }

    @Override
    public boolean supports(Class<?> classy) {
        return DestinationDTO.class.isAssignableFrom(classy);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DestinationDTO dest = (DestinationDTO) target;
        List<DestinationDTO> destinations = facade.getAllDestinations();

        for(DestinationDTO ds : destinations){
            if(ds.getAirportCode().equals(dest.getAirportCode())) {
                errors.rejectValue("airportCode", "invalid.airportCode","Destination with this code already exists!");
                break;
            }
        }
    }
}
