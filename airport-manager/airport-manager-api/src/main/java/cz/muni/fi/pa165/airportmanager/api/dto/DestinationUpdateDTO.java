package cz.muni.fi.pa165.airportmanager.api.dto;

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
 */

@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"airportCode"})
public class DestinationUpdateDTO {
    private Long id;
    private String airportCode;
    private String city;
    private String country;
}
