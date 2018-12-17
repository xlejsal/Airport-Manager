package cz.muni.fi.pa165.airportmanager.service.validators;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * @author kotrc
 * Created on 17.12.2018
 */
public class FlightCreateDTOValidator implements Validator {

    private FlightFacade facade;

    public FlightCreateDTOValidator(FlightFacade flightFacade) {
        facade = flightFacade;
    }

    @Override
    public boolean supports(Class<?> classy) {
        return FlightCreateDTO.class.isAssignableFrom(classy);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FlightCreateDTO flight = (FlightCreateDTO) target;
        List<FlightDTO> flights = facade.getAllFlights();

        for (FlightDTO f : flights){
            if(f.getFlightNumber().equals(flight.getFlightNumber())) {
                errors.rejectValue("flightNumber", "invalid.name", "Flight with this number already exists");
            }
        }

        for (FlightDTO f : facade.getAllFlights()) {
            if( f.getAirplane().getId().equals(flight.getAirplaneId())) {
                if ((f.getDepartureTime().isBefore(flight.getArrivalTime()) &&
                        f.getDepartureTime().isAfter(flight.getDepartureTime())) ||
                        f.getArrivalTime().isBefore(flight.getArrivalTime()) &&
                                f.getArrivalTime().isAfter(flight.getDepartureTime()) ||
                        f.getArrivalTime().isEqual(flight.getDepartureTime()) ||
                        f.getDepartureTime().isEqual(flight.getArrivalTime()) ||
                        f.getDepartureTime().isEqual(flight.getDepartureTime()) ||
                        f.getArrivalTime().isEqual(flight.getArrivalTime())) {
                    errors.rejectValue("airplaneId", "invalid.name",
                            "This airplane already has a flight in time of this flight");
                }
            }
        }
    }
}
