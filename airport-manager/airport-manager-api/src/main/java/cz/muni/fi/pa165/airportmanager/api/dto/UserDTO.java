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
 * @author Stepan Benes
 */

@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"email", "login"})
public class UserDTO {
    @NotNull
    private Long id;

    @NotNull
    private String passwordHash;

    @NotNull
    private String login;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private boolean admin;
}
