package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;

import java.util.List;

/**
 * TODO:javadoc
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public interface AirplaneService {

    List<AirplanePO> getAllAirplanes();

    AirplanePO getAirplaneById(Long Id);

    AirplanePO createAirplane(AirplanePO airplane);

    void deleteAirplane(Long Id);

    AirplanePO findAirplaneByName(String name);
}
