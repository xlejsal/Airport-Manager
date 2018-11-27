package cz.muni.fi.pa165.airportmanager.service.service;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.StewardRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import cz.muni.fi.pa165.airportmanager.service.services.impl.StewardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

/**
 * A set of tests to check the functionality of StewardServiceImpl
 * @author Stepan Benes
 * Created on 2018-11-26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StewardServiceImplTest {
    private StewardService service;

    @Mock
    private StewardRepository repo;

    private StewardPO stew1, stew2, stew3;
    private FlightPO flight1, flight2;
    private Set<StewardPO> flightStews = new HashSet<StewardPO>();
    private List<StewardPO> stews = new ArrayList<StewardPO>();
    private LocalDateTime stamp;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        service = new StewardServiceImpl(repo);

        stamp = LocalDateTime.now();


        stew1 = StewardPO.builder()
                    .id(1L)
                    .name("Andrej")
                    .surname("Danko")
                    .gender("Kapitan")
                    .birthDate(LocalDate.of(1974, Month.SEPTEMBER, 15))
                    .nationality("SVK")
                    .flights(new HashSet<FlightPO>())
                    .build();

        stew2 = StewardPO.builder()
                .id(2L)
                .name("Thomas")
                .surname("Dubois")
                .gender("Man")
                .birthDate(LocalDate.of(1994, Month.DECEMBER, 17))
                .nationality("USA")
                .flights(new HashSet<FlightPO>())
                .build();

        stew3 = StewardPO.builder()
                .id(3L)
                .name("Rocky")
                .surname("Balboa")
                .gender("Man")
                .birthDate(LocalDate.of(1966, Month.JUNE, 6))
                .nationality("USA")
                .flights(new HashSet<FlightPO>())
                .build();

        stews.add(stew1);
        stews.add(stew2);
        stews.add(stew3);

        flight1 = FlightPO.builder()
                .id(1L)
                .flightNumber("333")
                .departureTime(stamp)
                .arrivalTime(stamp.plusHours(5))
                .origin((Mockito.mock(DestinationPO.class)))
                .destination((Mockito.mock(DestinationPO.class)))
                .airplane(Mockito.mock(AirplanePO.class))
                .stewards(new HashSet<StewardPO>())
                .build();

        flight2 = FlightPO.builder()
                .id(2L)
                .flightNumber("757")
                .departureTime(stamp.plusHours(16))
                .arrivalTime(stamp.plusHours(24))
                .origin((Mockito.mock(DestinationPO.class)))
                .destination((Mockito.mock(DestinationPO.class)))
                .airplane(Mockito.mock(AirplanePO.class))
                .stewards(new HashSet<StewardPO>())
                .build();

        flight1.setStewards(flightStews);
        flight2.setStewards(flightStews);

        Set<FlightPO> flights = new HashSet<FlightPO>();
        flights.add(flight1);
        flights.add(flight2);

        stew1.setFlights(flights);
    }

    @Test
    public void createTest(){
        service.createSteward(stew1);
        then(repo).should().save(stew1);
    }

    @Test
    public void deleteTest(){
        when(repo.findById(stew2.getId())).thenReturn(Optional.of(stew2));
        service.deleteSteward(stew2.getId());
        then(repo).should().delete(stew2);
    }

    @Test
    public void getStewardByIdTest(){
        when(repo.findById(stew3.getId())).thenReturn(Optional.of(stew3));
        StewardPO found = service.getStewardById(stew3.getId());
        assertEquals(found, stew3);
    }

    @Test
    public void getAllStewardsTest(){
        when(repo.findAll()).thenReturn(stews);
        List<StewardPO> found = service.getAllStewards();
        assertEquals(found.size(), stews.size());
    }

    @Test
    public void stewardIsAvailable(){
        when(repo.findById(stew1.getId())).thenReturn(Optional.of(stew1));
        assertEquals(true, service.isAvailableFromTo(stew1.getId(), stamp.plusHours(6), stamp.plusHours(8)));
    }

    @Test
    public void stewardIsNotAvailable(){
        when(repo.findById(stew1.getId())).thenReturn(Optional.of(stew1));
        assertEquals(false, service.isAvailableFromTo(stew1.getId(), stamp.plusHours(16), stamp.plusHours(21)));
    }
}
