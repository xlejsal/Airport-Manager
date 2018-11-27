package cz.muni.fi.pa165.airportmanager.service.service;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.DestinationRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.service.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.service.services.impl.DestinationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-11-26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ComponentScan(basePackages = "cz.muni.fi.pa165.airportmanager.service")
public class DestinationServiceImplTest {

    private DestinationService service;

    @Mock
    private DestinationRepository repo;

    private DestinationPO dest1, dest2;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        service = new DestinationServiceImpl(repo);

        dest1 = DestinationPO.builder()
                .id(1L)
                .airportCode("PRG")
                .country("Czechia")
                .city("Prague")
                .build();

        dest2 = DestinationPO.builder()
                .id(2L)
                .airportCode("OST")
                .country("Czechia")
                .city("Ostrava")
                .build();
    }

    @Test
    public void createTest() {
        service.createDestination(dest1);
        then(repo).should().save(dest1);
    }

    @Test
    public void deleteTest() {
        when(repo.findById(dest2.getId())).thenReturn(Optional.of(dest2));
        service.deleteDestination(dest2.getId());
        then(repo).should().delete(dest2);
    }

    @Test
    public void getDestinationByIdTest() {
        when(repo.findById(dest1.getId())).thenReturn(Optional.of(dest1));
        DestinationPO found = service.getDestinationById(dest1.getId());
        assertEquals(found, dest1);
    }

    @Test
    public void getAllDestinationsTest() {
        when(repo.findAll()).thenReturn(Arrays.asList(dest1, dest2));
        List<DestinationPO> found = service.getAllDestinations();
        assertEquals(found.size(), 2);
    }

    @Test
    public void findCityDestinationsTest() {
        when(repo.findByCity(dest2.getCity())).thenReturn(Collections.singletonList(dest2));
        List<DestinationPO> found = service.findCityDestinations(dest2.getCity());
        assertEquals(found.get(0), dest2);
    }

    @Test
    public void findCountryDestinationsTest() {
        when(repo.findByCountry(dest2.getCountry())).thenReturn(Arrays.asList(dest1, dest2));
        List<DestinationPO> found = service.findCountryDestinations(dest2.getCountry());
        assertEquals(found.size(), 2);
    }
}
