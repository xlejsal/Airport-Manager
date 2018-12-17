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
 * Airplane DTO class, based on Airplane entity
 *
 * @author Stepan Benes
 */

@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class AirplaneUpdateDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String company;
    private Integer capacity;
}
