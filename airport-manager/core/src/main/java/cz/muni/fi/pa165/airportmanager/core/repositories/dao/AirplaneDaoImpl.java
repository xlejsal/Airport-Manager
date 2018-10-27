package cz.muni.fi.pa165.airportmanager.core.repositories.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.Airplane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @see cz.muni.fi.pa165.airportmanager.core.repositories.dao.AirplaneDao
 *
 * @author Stepan Benes
 * Created on 2018-10-25
 */

@Repository
public class AirplaneDaoImpl implements AirplaneDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Airplane a){
        em.persist(a);
    }

    @Override
    public Airplane findById(Long id){
        return em.find(Airplane.class, id);
    }

    @Override
    public List<Airplane> findAll(){
        return em.createQuery("select a from Airplanes a", Airplane.class).getResultList();
    }

    @Override
    public Airplane findByName(String n){
        return em.createQuery("SELECT a FROM Airplanes a WHERE a.name = :name ", Airplane.class).setParameter("name", "%"+n+"%").getSingleResult();
    }

    @Override
    public void updateCompany(Long id, String c){
        Airplane a = findById(id);
        a.setCompany(c);
        em.persist(a);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Long id){
        em.remove(findById(id));
    }
}
