package cz.muni.fi.pa165.airportmanager.service.service;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.FlightRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.services.FlightService;
import cz.muni.fi.pa165.airportmanager.service.services.impl.FlightServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
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
public class FlightServiceImplTest {

    private FlightService service;

    @Mock
    private FlightRepository repo;

    private FlightPO flight1, flight2;

    private StewardPO steward;

    private LocalDateTime depTime1, depTime2, arrTime1, arrTime2;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        service = new FlightServiceImpl(repo);
        depTime1 = LocalDateTime.of(2018, 11, 10, 9, 30);
        depTime1 = LocalDateTime.of(2018, 11, 12, 12, 30);

        arrTime1 = LocalDateTime.of(2018, 11, 10, 18, 45);
        arrTime1 = LocalDateTime.of(2018, 11, 11, 2, 50);

        AirplanePO airplane = new AirplanePO();
        airplane.setCapacity(64);
        airplane.setCompany("American Airlines");
        airplane.setName("Burger");
        airplane.setType("Boeing 717");

        DestinationPO origin = new DestinationPO();
        origin.setAirportCode("CCC");
        origin.setCity("Tokyo");
        origin.setCountry("Japan");

        DestinationPO destination = new DestinationPO();
        destination.setAirportCode("KLH");
        destination.setCity("Bratislava");
        destination.setCountry("Slovakia");

        steward = new StewardPO();
        steward.setBirthDate(LocalDate.of(1996, Month.SEPTEMBER, 18));
        steward.setGender("Woman");
        steward.setName("Marienka");
        steward.setSurname("Pernikova");
        steward.setNationality("Slovak");

        HashSet<StewardPO> stewards = new HashSet<>();
        stewards.add(steward);

        flight1 = FlightPO.builder()
                .id(1L)
                .flightNumber("OK-740")
                .airplane(airplane)
                .departureTime(depTime1)
                .arrivalTime(arrTime1)
                .origin(origin)
                .destination(destination)
                .stewards(stewards)
                .build();

        flight2 = FlightPO.builder()
                .id(1L)
                .flightNumber("OK-740")
                .airplane(airplane)
                .departureTime(depTime1)
                .arrivalTime(arrTime1)
                .origin(origin)
                .destination(destination)
                .stewards(stewards)
                .build();
    }

    @Test
    public void createFlightTest() {
        service.createFlight(flight1);
        then(repo).should().save(flight1);
    }

    @Test
    public void getAllFlightsTest() {
        when(repo.findAll()).thenReturn(Arrays.asList(flight1, flight2));
        List<FlightPO> found = service.getAllFlights();
        assertEquals(found.size(), 2);
    }

    @Test
    public void getFlightByIdTest() {
        when(repo.findById(flight1.getId())).thenReturn(Optional.of(flight1));
        FlightPO found = service.getFlightById(flight1.getId());
        assertEquals(found, flight1);
    }

    @Test
    public void getFlightByFlightNumberTest() {
        when(repo.findById(flight1.getId())).thenReturn(Optional.of(flight1));
        FlightPO found = service.getFlightById(flight1.getId());
        assertEquals(found, flight1);
    }

    @Test
    public void deleteFlightTest() {
        when(repo.findById(flight2.getId())).thenReturn(Optional.of(flight2));
        service.deleteFlight(flight2.getId());
        then(repo).should().delete(flight2);
    }

    @Test
    public void addStewardTest() {
        service.addSteward(steward.withName("John Smith"), flight1);
        then(repo).should().save(flight1);
    }

    @Test
    public void removeStewardTest() {
        service.removeSteward(steward, flight2);
        then(repo).should().save(flight2);
    }
}
