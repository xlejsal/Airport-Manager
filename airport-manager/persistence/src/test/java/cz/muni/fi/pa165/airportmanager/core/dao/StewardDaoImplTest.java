package cz.muni.fi.pa165.airportmanager.core.dao;

import cz.muni.fi.pa165.airportmanager.core.dao.StewardDao;
import cz.muni.fi.pa165.airportmanager.core.repositories.StewardRepository;
import cz.muni.fi.pa165.airportmanager.core.repositories.models.StewardPO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StewardDaoImplTest {

    @Autowired
    private StewardDao dao;

    @Autowired
    private StewardRepository repo;

    private StewardPO steward1;
    private StewardPO steward2;

    @Before
    public void init() {
        steward1 = StewardPO.builder()
                .name("John")
                .surname("Smith")
                .birthDate(LocalDate.of(1984, 4, 21))
                .gender("Male")
                .nationality("USA")
                .build();

        steward2 = StewardPO.builder()
                .name("Jane")
                .surname("Doe")
                .birthDate(LocalDate.of(1979, 9, 4))
                .gender("Female")
                .nationality("UK")
                .build();
    }

    @Test
    public void createTest() {
        dao.create(steward1);
        assertThat(steward1.getId()).isNotNull();
        assertThat(repo.findById(steward1.getId()).get()).isEqualTo(steward1);
    }

    @Test
    public void updateTest() {
        repo.save(steward1);
        repo.save(steward2);
        StewardPO updatedSteward = dao.update(steward1.withGender("Female"));
        assertThat(repo.findById(steward1.getId()).get()).isEqualTo(updatedSteward);
        assertThat(repo.findById(steward2.getId()).get()).isEqualTo(steward2);
    }

    @Test
    public void findByIdTest() {
        repo.save(steward1);
        repo.save(steward2);
        StewardPO steward2Db = dao.findById(steward2.getId());
        assertThat(steward2Db).isEqualTo(steward2);
    }

    @Test
    public void findAllTest() {
        repo.save(steward1);
        repo.save(steward2);
        assertThat(dao.findAll()).containsExactlyInAnyOrder(steward1, steward2);
    }

    @Test
    public void removeTest() {
        repo.save(steward1);
        repo.save(steward2);
        dao.delete(steward1);
        assertThat(repo.findAll()).containsExactly(steward2);
    }
}
