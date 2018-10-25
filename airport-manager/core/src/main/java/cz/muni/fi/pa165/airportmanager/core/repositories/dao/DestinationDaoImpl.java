package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Destination;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author kotrc
 * Created on 25.10.2018
 */
public class DestinationDaoImpl implements DestinationDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Destination destination) {
        em.persist(destination);
    }

    @Override
    public void remove(Long id) {
        em.remove(findById(id));
    }

    @Override
    public Destination findById(Long id) {
        return em.find(Destination.class, id);
    }

    @Override
    public List<Destination> findAll() {
        return em.createQuery("select a from Destinations a", Destination.class).getResultList();
    }
}
