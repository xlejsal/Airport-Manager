package cz.muni.fi.pa165.airportmanager.api.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;

import java.util.List;

/**
 * TODO:javadoc
 * @author Stepan Benes
 * Created on 2018-11-20
 */

public interface AirplaneFacade {

    List<AirplaneDTO> getAllAirplanes();

    AirplaneDTO getAirplaneById(Long Id);

    AirplaneDTO createAirplane(AirplaneDTO airplane);

    void deleteAirplane(Long Id);

    AirplaneDTO findAirplaneByName(String name);
}
