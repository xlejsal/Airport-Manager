package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Steward;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @see cz.muni.fi.pa165.airportmanager.core.repositories.dao.StewardDao
 *
 * @author Stepan Benes
 * Created on 2018-10-25
 */

@Repository
public class StewardDaoImpl implements StewardDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Steward s){
        em.persist(s);
    }

    @Override
    public Steward findById(Long id){
        return em.find(Steward.class, id);
    }

    @Override
    public List<Steward> findAll(){
        return em.createQuery("select s from Stewards s", Steward.class).getResultList();
    }

    @Override
    public void updateGender(Long id, String g){
        Steward s = findById(id);
        s.setGender(g);
        em.persist(s);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Long id){
        em.remove(findById(id));
    }
}
