package cz.muni.fi.pa165.airportmanager.api.dto;

import lombok.*;
import lombok.experimental.Wither;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kotrc
 * Created on 23.11.2018
 */

@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"flightNumber", "departureTime"})
public class FlightDTO {
    private Long id;
    private String flightNumber;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private DestinationDTO origin;
    private DestinationDTO destination;
    private AirplaneDTO airplane;
    private Set<StewardWithoutFlightsDTO> stewards = new HashSet<>();
}
