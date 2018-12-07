package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.UserFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.UserPO;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stepan Benes
 */

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    private final BeanMappingService mapper;

    @Autowired
    public UserFacadeImpl(UserService userService, BeanMappingService mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public void registerUser(UserDTO user, String rawPassword){
        userService.registerUser(mapper.mapTo(user, UserPO.class), rawPassword);
    }

    @Override
    public List<UserDTO> getAllUsers(){
        return mapper.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long Id){
        return mapper.mapTo(userService.getUserById(Id), UserDTO.class);
    }

    @Override
    public UserDTO getUserByLogin(String login){
        return mapper.mapTo(userService.getUserByLogin(login), UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email){
        return mapper.mapTo(userService.getUserByEmail(email), UserDTO.class);
    }

    @Override
    public boolean isAdmin(UserDTO user){
        return userService.isAdmin(mapper.mapTo(user, UserPO.class));
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO user){
        return userService.authenticate(mapper.mapTo(user, UserPO.class), user.getPassword());
    }

    @Override
    public void deleteUser(Long Id){
        userService.deleteUser(Id);
    }
}
