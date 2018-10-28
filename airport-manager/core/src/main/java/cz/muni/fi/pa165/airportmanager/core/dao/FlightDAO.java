package cz.muni.fi.pa165.airportmanager.core.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.FlightPO;

import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-28
 */
public interface FlightDAO {

    FlightPO findById(long id);

    FlightPO create(FlightPO flight);

    void delete(FlightPO flight);

    List<FlightPO> findAll();
}
