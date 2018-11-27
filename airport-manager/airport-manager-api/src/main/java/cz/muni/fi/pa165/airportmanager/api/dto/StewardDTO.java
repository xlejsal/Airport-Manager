package cz.muni.fi.pa165.airportmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-17
 */

@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "surname", "birthDate"})
public class StewardDTO {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gender;
    private String nationality;
    private Set<FlightWithoutStewardsDTO> flights = new HashSet<>();
}
