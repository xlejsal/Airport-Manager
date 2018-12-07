package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Stepan Benes
 */

public interface UserRepository extends JpaRepository<UserPO, Long> {
    public UserPO findByLogin(String login);
    public UserPO findByEmail(String email);
}
