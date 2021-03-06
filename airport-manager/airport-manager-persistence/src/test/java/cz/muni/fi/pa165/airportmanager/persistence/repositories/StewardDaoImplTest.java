package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.api.enums.Gender;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
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
    private StewardRepository repo;

    private StewardPO steward1;
    private StewardPO steward2;

    @Before
    public void init() {
        steward1 = StewardPO.builder()
                .name("John")
                .surname("Smith")
                .birthDate(LocalDate.of(1984, 4, 21))
                .gender(Gender.Male)
                .nationality("USA")
                .build();

        steward2 = StewardPO.builder()
                .name("Jane")
                .surname("Doe")
                .birthDate(LocalDate.of(1979, 9, 4))
                .gender(Gender.Female)
                .nationality("UK")
                .build();
    }

    @Test
    public void createTest() {
        repo.save(steward1);
        assertThat(steward1.getId()).isNotNull();
        assertThat(repo.findById(steward1.getId()).orElse(null)).isEqualTo(steward1);
    }

    @Test
    public void updateTest() {
        repo.save(steward1);
        repo.save(steward2);
        StewardPO updatedSteward = repo.save(steward1.withGender(Gender.Female));
        assertThat(repo.findById(steward1.getId()).orElse(null)).isEqualTo(updatedSteward);
        assertThat(repo.findById(steward2.getId()).orElse(null)).isEqualTo(steward2);
    }

    @Test
    public void findByIdTest() {
        repo.save(steward1);
        repo.save(steward2);
        StewardPO steward2Db = repo.findById(steward2.getId()).orElse(null);
        assertThat(steward2Db).isEqualTo(steward2);
    }

    @Test
    public void findAllTest() {
        repo.save(steward1);
        repo.save(steward2);
        assertThat(repo.findAll()).containsExactlyInAnyOrder(steward1, steward2);
    }

    @Test
    public void removeTest() {
        repo.save(steward1);
        repo.save(steward2);
        repo.delete(steward1);
        assertThat(repo.findAll()).containsExactly(steward2);
    }
}
