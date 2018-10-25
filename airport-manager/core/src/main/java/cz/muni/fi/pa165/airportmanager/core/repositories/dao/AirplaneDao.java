package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Airplane;

import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-10-25
 * TODO: add javadoc comments
 */

public interface AirplaneDao {
    public void create(Airplane a);
    public Airplane findById(Long id);
    public List<Airplane> findAll();
    public List<Airplane> findByName(String n);
    public void updateCompany(Long id, String c);
    public void remove(Long id);
}
