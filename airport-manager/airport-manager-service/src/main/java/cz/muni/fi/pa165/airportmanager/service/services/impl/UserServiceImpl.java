package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.UserRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.UserPO;
import cz.muni.fi.pa165.airportmanager.service.exceptions.AirportManagerDataAccessException;
import cz.muni.fi.pa165.airportmanager.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

/**
 * @author Stepan Benes
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void registerUser(UserPO user, String rawPassword){
        if(userRepo.findByLogin(user.getLogin()) != null || userRepo.findByEmail(user.getEmail()) != null){
            throw new AirportManagerDataAccessException("User with this login or email already exists.");
        }
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt(4));
        user.setPasswordHash(hashedPassword);
        userRepo.save(user);
    }

    @Override
    public List<UserPO> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public UserPO getUserById(Long Id){
        return userRepo.findById(Id).orElseThrow(() -> new AirportManagerDataAccessException("User of ID " +
                Id + " does not exist"));
    }

    @Override
    public UserPO getUserByLogin(String login){
        try{
            return userRepo.findByLogin(login);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot find a user with the login " + login, e);
        }
    }

    @Override
    public UserPO getUserByEmail(String email){
        try{
            return userRepo.findByEmail(email);
        } catch(DataAccessException e){
            throw new AirportManagerDataAccessException("Cannot find a user with the e-mail " + email, e);
        }
    }

    @Override
    public boolean isAdmin(UserPO user){
        return getUserByLogin(user.getLogin()).isAdmin();
    }

    @Override
    public boolean authenticate(UserPO user, String password){
        return BCrypt.checkpw(password, userRepo.findByLogin(user.getLogin()).getPasswordHash());
    }

    @Override
    public void deleteUser(Long Id){
        userRepo.deleteById(Id);
    }
}
