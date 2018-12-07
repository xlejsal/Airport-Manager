package cz.muni.fi.pa165.airportmanager.api.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;

/**
 * Destination DTO class, based on Destination entity
 *
 * @author Stepan Benes
 * Created on 2018-11-20
 */

@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"airportCode"})
public class DestinationDTO {
    @NotNull
    private Long id;
    @NotNull
    private String airportCode;
    @NotNull
    private String city;
    @NotNull
    private String country;
}
