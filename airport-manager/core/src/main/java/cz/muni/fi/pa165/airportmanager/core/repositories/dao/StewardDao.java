package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Steward;

import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-10-25
 * TODO: add javadoc comments
 */

public interface StewardDao {
    public void create(Steward s);
    public Steward findById(Long id);
    public List<Steward> findAll();
    public void updateGender(Long id, String g);
    public void remove(Long id);
}
