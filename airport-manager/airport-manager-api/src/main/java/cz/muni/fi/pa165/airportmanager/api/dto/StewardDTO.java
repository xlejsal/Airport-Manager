package cz.muni.fi.pa165.airportmanager.api.dto;

import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.airportmanager.api.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private Gender gender;
    private String nationality;
    private Set<FlightWithoutStewardsDTO> flights = new HashSet<>();
}
