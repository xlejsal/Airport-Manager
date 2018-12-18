package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.UserDTO;

import java.util.List;

/**
 * @author Stepan Benes
 */

public interface UserFacade {
    void registerUser(UserDTO user, String rawPassword);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long Id);

    UserDTO getUserByLogin(String login);

    UserDTO getUserByEmail(String email);

    boolean isAdmin(UserDTO user);

    boolean authenticate(UserAuthenticateDTO user);

    void deleteUser(Long Id);
}
