package cz.muni.fi.pa165.airportmanager.api.dto;

import com.sun.istack.internal.NotNull;
import lombok.*;
import lombok.experimental.Wither;

@Getter
@Setter
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticateDTO {
    @NonNull
    private String login;

    @NotNull
    private String password;
}
