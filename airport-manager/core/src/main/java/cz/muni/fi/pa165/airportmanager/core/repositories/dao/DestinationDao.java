package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Destination;

import java.util.List;

/**
 * @author kotrc
 * Created on 25.10.2018
 */

public interface DestinationDao {
    public void create(Destination destination);
    public void remove(Long id);
    public Destination findById(Long id);
    public List<Destination> findAll();
}
