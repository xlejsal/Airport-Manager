package cz.muni.fi.pa165.airportmanager.api.dto;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kotrc
 * Created on 17.12.2018
 */
@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"flightNumber", "departureTime"})
public class FlightCreateDTO {
    private Long id;
    @NotNull
    private String flightNumber;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime departureTime;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime arrivalTime;
    @NotNull
    private Long originId;
    @NotNull
    private Long destinationId;
    @NotNull
    private Long airplaneId;
    private Set<Long> stewardIds = new HashSet<>();
}
