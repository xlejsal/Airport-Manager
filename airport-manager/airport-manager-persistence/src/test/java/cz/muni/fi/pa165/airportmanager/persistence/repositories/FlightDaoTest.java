package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.api.enums.Gender;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests
 * @author kotrc
 * Created on 29.10.2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FlightDaoTest {

    @Autowired
    AirplaneRepository airplaneRepo;

    @Autowired
    DestinationRepository destinationRepo;

    @Autowired
    StewardRepository stewardRepo;

    @Autowired
    FlightRepository flightRepo;

    private AirplanePO airplaneBig;
    private AirplanePO airplaneSmall;
    private DestinationPO destination;
    private DestinationPO origin;
    private FlightPO flight;
    private FlightPO flightMagic;
    private Set<StewardPO> stewardSet = new HashSet<>();
    private Set<FlightPO> flightSet = new HashSet<>();

    private static final LocalDate BIRTH_DATE = LocalDate.of(1996, Month.SEPTEMBER, 18);
    private static final LocalDateTime DEPARTURE = LocalDateTime.of(2018, Month.NOVEMBER, 6, 17, 30);
    private static final LocalDateTime ARRIVAL = LocalDateTime.of(2018, Month.NOVEMBER, 7, 11, 45);
    private static final LocalDateTime TIME = LocalDateTime.of(2018, Month.NOVEMBER, 9, 9, 0);

    @Before
    public void init() {

        StewardPO stewardGirl;
        StewardPO stewardBoy;

        airplaneBig = new AirplanePO();
        airplaneBig.setCapacity(256);
        airplaneBig.setCompany("British Airlines");
        airplaneBig.setName("Pikachu");
        airplaneBig.setType("Boeing 737");

        airplaneSmall = new AirplanePO();
        airplaneSmall.setCapacity(64);
        airplaneSmall.setCompany("American Airlines");
        airplaneSmall.setName("Burger");
        airplaneSmall.setType("Boeing 717");

        destination = new DestinationPO();
        destination.setAirportCode("KLH");
        destination.setCity("Bratislava");
        destination.setCountry("Slovakia");

        origin = new DestinationPO();
        origin.setAirportCode("CCC");
        origin.setCity("Tokyo");
        origin.setCountry("Japan");

        flightSet.add(flight);
        flightSet.add(flightMagic);

        stewardGirl = new StewardPO();
        stewardGirl.setBirthDate(BIRTH_DATE);
        stewardGirl.setGender(Gender.Female);
        stewardGirl.setName("Marienka");
        stewardGirl.setSurname("Pernikova");
        stewardGirl.setNationality("Slovak");
        stewardGirl.setFlights(flightSet);

        stewardBoy = new StewardPO();
        stewardBoy.setBirthDate(BIRTH_DATE);
        stewardBoy.setGender(Gender.Male);
        stewardBoy.setName("Juraj");
        stewardBoy.setSurname("Janosik");
        stewardBoy.setNationality("Slovak");
        stewardBoy.setFlights(flightSet);

        stewardSet.add(stewardGirl);
        stewardSet.add(stewardBoy);

        flight = new FlightPO();
        flight.setAirplane(airplaneBig);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setFlightNumber("GH-337");
        flight.setDepartureTime(DEPARTURE);
        flight.setArrivalTime(ARRIVAL);
        flight.setStewards(stewardSet);

        flightMagic = new FlightPO();
        flightMagic.setAirplane(airplaneSmall);
        flightMagic.setOrigin(destination);
        flightMagic.setDestination(origin);
        flightMagic.setFlightNumber("BJ-696");
        flightMagic.setDepartureTime(DEPARTURE);
        flightMagic.setArrivalTime(ARRIVAL);
        flightMagic.setStewards(stewardSet);

        airplaneRepo.save(airplaneBig);
        airplaneRepo.save(airplaneSmall);
        destinationRepo.save(destination);
        destinationRepo.save(origin);
        stewardRepo.save(stewardGirl);
        stewardRepo.save(stewardBoy);
        flightRepo.save(flight);
        flightRepo.save(flightMagic);
    }

    @Test
    public void testFindById() {
        FlightPO found = flightRepo.findById(flight.getId()).orElse(null);
        Assert.assertEquals(found, flight);
    }

    @Test
    public void testFindNothing() {
        FlightPO found = flightRepo.findById(flight.getId() + 12345).orElse(null);
        Assert.assertNull(found);
    }

    @Test
    public void testUpdateAirplane() {
        Assert.assertEquals(flight.getAirplane(), airplaneBig);
        flight.setAirplane(airplaneSmall);
        flightRepo.save(flight);
        Assert.assertEquals(flight.getAirplane(), airplaneSmall);
    }

    @Test
    public void testUpdateArrivalTime() {
        Assert.assertEquals(flight.getArrivalTime(), ARRIVAL);
        flight.setArrivalTime(TIME);
        flightRepo.save(flight);
        Assert.assertEquals(flight.getArrivalTime(), TIME);
    }

    @Test
    public void testDelete() {
        Assert.assertNotNull(flightRepo.findById(flight.getId()).orElse(null));
        flightRepo.delete(flight);
        Assert.assertNull(flightRepo.findById(flight.getId()).orElse(null));
    }

    @Test
    public void testFindAll() {
        List<FlightPO> flights = flightRepo.findAll();
        Assert.assertEquals(flights.size(), 2);
        Assert.assertTrue(flights.contains(flight));
        Assert.assertTrue(flights.contains(flightMagic));
    }

    @Test
    public void testCreate() {
        FlightPO flightTest = new FlightPO();
        flightTest.setAirplane(airplaneSmall);
        flightTest.setOrigin(origin);
        flightTest.setDestination(destination);
        flightTest.setFlightNumber("BB-169");
        flightTest.setDepartureTime(DEPARTURE);
        flightTest.setArrivalTime(ARRIVAL);
        flightTest.setStewards(stewardSet);

        flightRepo.save(flightTest);

        FlightPO found = flightRepo.findById(flightTest.getId()).orElse(null);
        Assert.assertEquals(found, flightTest);
    }
}