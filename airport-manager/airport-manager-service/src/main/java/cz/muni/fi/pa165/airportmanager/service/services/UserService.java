package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.UserPO;

import java.util.List;

/**
 * @author Stepan Benes
 */

public interface UserService {
    void registerUser(UserPO user, String rawPassword);

    List<UserPO> getAllUsers();

    UserPO getUserById(Long Id);

    UserPO getUserByLogin(String login);

    UserPO getUserByEmail(String email);

    boolean isAdmin(UserPO user);

    boolean authenticate(UserPO user, String password);

    void deleteUser(Long Id);
}
