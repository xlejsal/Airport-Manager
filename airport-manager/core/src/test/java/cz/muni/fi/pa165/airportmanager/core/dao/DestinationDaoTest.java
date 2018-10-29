package cz.muni.fi.pa165.airportmanager.core.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.DestinationPO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
    private DestinationDao dao;

    private DestinationPO dest1;
    private DestinationPO dest2;
    private DestinationPO dest3;

    @Before
    public void initialize(){
        DestinationPO dest1 = DestinationPO.builder()
                .airportCode("BRB")
                .city("Torshavn")
                .country("Faroe Isles")
                .build();

        DestinationPO dest2 = DestinationPO.builder()
                .airportCode("FFS")
                .city("Bucharest")
                .country("Romania")
                .build();

        DestinationPO dest3 = DestinationPO.builder()
                .airportCode("BBQ")
                .city("Brno")
                .country("Czechia")
                .build();

        dao.create(dest1);
        dao.create(dest2);
        dao.create(dest3);
    }

    @Test
    public void findAll(){
        List<DestinationPO> found = dao.findAll();
        Assert.assertEquals(found.size(), 3);
    }

    @Test
    public void findById(){
        Assert.assertNotNull(dao.findById(dest2.getId()));
    }

    @Test
    public void update(){
        DestinationPO dest = dao.findById(dest3.getId());
        dest.setCountry("Moravia");
        dao.update(dest);

        DestinationPO check = dao.findById(dest3.getId());
        Assert.assertEquals(check.getCity(), "Moravia");
    }

    @Test
    public void remove(){
        dao.delete(dest2);
        Assert.assertNull(dao.findById(dest2.getId()));
        Assert.assertEquals(dao.findAll().size(), 2);
    }
}
