package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AirplaneDaoImplTest {

    @Autowired
    private AirplaneRepository repo;

    private AirplanePO airplane1;
    private AirplanePO airplane2;

    @Before
    public void init() {
        airplane1 = AirplanePO.builder()
                .name("LCE7894")
                .capacity(180)
                .company("CSA")
                .type("Boeing 737")
                .build();

        airplane2 = AirplanePO.builder()
                .name("KCE2145")
                .capacity(230)
                .company("Ryan Air")
                .type("Airbus A370")
                .build();
    }

    @Test
    public void createTest() {
        repo.save(airplane1);
        assertThat(airplane1.getId()).isNotNull();
        assertThat(repo.findById(airplane1.getId()).get()).isEqualTo(airplane1);
    }

    @Test
    public void updateTest() {
        repo.save(airplane1);
        repo.save(airplane2);
        AirplanePO updatedAirplane = repo.save(airplane1.withCapacity(300).withCompany("ČSA").withType("Airbus A380"));
        assertThat(repo.findById(airplane1.getId()).get()).isEqualTo(updatedAirplane);
        assertThat(repo.findById(airplane2.getId()).get()).isEqualTo(airplane2);
    }

    @Test
    public void findByIdTest() {
        repo.save(airplane1);
        repo.save(airplane2);
        AirplanePO airplane2Db = repo.findById(airplane2.getId()).get();
        assertThat(airplane2Db).isEqualTo(airplane2);
    }

    @Test
    public void findAllTest() {
        repo.save(airplane1);
        repo.save(airplane2);
        assertThat(repo.findAll()).containsExactlyInAnyOrder(airplane1, airplane2);
    }

    @Test
    public void findByName() {
        repo.save(airplane1);
        repo.save(airplane2);
        assertThat(repo.findFirstByName(airplane2.getName())).isEqualTo(airplane2);
    }

    @Test
    public void removeTest() {
        repo.save(airplane1);
        repo.save(airplane2);
        repo.delete(airplane1);
        assertThat(repo.findAll()).containsExactly(airplane2);
    }
}
