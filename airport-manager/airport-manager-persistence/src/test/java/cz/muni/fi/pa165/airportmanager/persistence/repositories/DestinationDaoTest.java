package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stepan Benes
 * Created on 2018-10-29
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DestinationDaoTest {

    @Autowired
    private DestinationRepository repo;

    private DestinationPO dest1;
    private DestinationPO dest2;
    private DestinationPO dest3;

    @Before
    public void initialize(){
        dest1 = DestinationPO.builder()
                .airportCode("BRB")
                .city("Torshavn")
                .country("Faroe Isles")
                .build();

        dest2 = DestinationPO.builder()
                .airportCode("FFS")
                .city("Bucharest")
                .country("Romania")
                .build();

        dest3 = DestinationPO.builder()
                .airportCode("BBQ")
                .city("Brno")
                .country("Czechia")
                .build();

        repo.save(dest1);
        repo.save(dest2);
        repo.save(dest3);
    }

    @Test
    public void findAll(){
        List<DestinationPO> found = new ArrayList<DestinationPO>();
        repo.findAll().forEach(found::add);
        Assert.assertEquals(found.size(), 3);
    }

    @Test
    public void findById(){
        Assert.assertNotNull(repo.findById(dest2.getId()));
    }

    @Test
    public void update(){
        DestinationPO dest = repo.findById(dest3.getId()).orElse(null);
        dest.setCountry("Moravia");
        repo.save(dest);

        DestinationPO check = repo.findById(dest.getId()).orElse(null);
        Assert.assertEquals(check.getCountry(), "Moravia");
    }

    @Test
    public void remove(){
        repo.delete(dest2);
        Assert.assertNull(repo.findById(dest2.getId()).orElse(null));
        List<DestinationPO> found = new ArrayList<DestinationPO>();
        repo.findAll().forEach(found::add);
        Assert.assertEquals(found.size(), 2);
    }

    @Test(expected = DataIntegrityViolationException.class )
    public void uniqueCodeTest(){
        DestinationPO dest4 = DestinationPO.builder()
                .airportCode("BRB")
                .city("London")
                .country("Britain")
                .build();

        repo.save(dest4);
    }

    @Test(expected = ConstraintViolationException.class )
    public void nullAttributeTest(){
        DestinationPO dest5 = DestinationPO.builder()
                .airportCode("UFF")
                .country("Austrialia")
                .build();

        repo.save(dest5);
    }
}
