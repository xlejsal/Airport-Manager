package cz.muni.fi.pa165.airportmanager.api.dto;

import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Wither;

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
    @NotNull
    private Long id;
    @NotNull
    private String flightNumber;
    @NotNull
    private LocalDateTime departureTime;
    @NotNull
    private LocalDateTime arrivalTime;
    @NotNull
    private DestinationDTO origin;
    @NotNull
    private DestinationDTO destination;
    @NotNull
    private AirplaneDTO airplane;
    private Set<StewardWithoutFlightsDTO> stewards = new HashSet<>();
}
