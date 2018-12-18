package cz.muni.fi.pa165.airportmanager.api.dto;

import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Wither;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime departureTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime arrivalTime;
    private DestinationDTO origin;
    private DestinationDTO destination;
    private AirplaneDTO airplane;
    private Set<StewardWithoutFlightsDTO> stewards = new HashSet<>();
}
