package cz.muni.fi.pa165.airportmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightUpdateDTO {
    private Long id;
    private String flightNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime departureTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime arrivalTime;
    private String origin;
    private String destination;
    private String airplane;
    private Set<StewardWithoutFlightsDTO> stewards = new HashSet<>();
}
