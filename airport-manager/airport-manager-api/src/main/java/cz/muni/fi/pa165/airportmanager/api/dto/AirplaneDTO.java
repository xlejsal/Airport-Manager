package cz.muni.fi.pa165.airportmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;

/**
 * Airplane DTO class, based on Airplane entity
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
@EqualsAndHashCode(of = {"name"})
public class AirplaneDTO {
    private Long id;
    private String name;
    private String type;
    private String company;
    private Integer capacity;
}
