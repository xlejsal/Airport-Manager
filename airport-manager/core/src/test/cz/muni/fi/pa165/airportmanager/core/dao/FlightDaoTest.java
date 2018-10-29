package cz.muni.fi.pa165.airportmanager.core.dao;

import cz.muni.fi.pa165.airportmanager.core.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.core.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.core.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.core.repositories.models.StewardPO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kotrc
 * Created on 29.10.2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightDaoTest {

    @Autowired
    AirplaneDao airplaneDao;

    @Autowired
    DestinationDao destinationDao;

    @Autowired
    StewardDao stewardDao;

    @Autowired
    FlightDao flightDao;

    private AirplanePO airplaneBig;
    private AirplanePO airplaneSmall;
    private FlightPO flight;
    private FlightPO flightMagic;
    private Set<StewardPO> stewardSet = new HashSet<>();

    private static final LocalDate BIRTH_DATE = LocalDate.of(1996, Month.SEPTEMBER, 18);
    private static final LocalDateTime DEPARTURE = LocalDateTime.of(2018, Month.NOVEMBER, 6, 17, 30);
    private static final LocalDateTime ARRIVAL = LocalDateTime.of(2018, Month.NOVEMBER, 7, 11, 45);

    @BeforeEach
    public void init() {

        DestinationPO destination;
        DestinationPO origin;
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

        stewardGirl = new StewardPO();
        stewardGirl.setBirthDate(BIRTH_DATE);
        stewardGirl.setGender("Woman");
        stewardGirl.setName("Marienka");
        stewardGirl.setSurname("Pernikova");
        stewardGirl.setNationality("Slovak");

        stewardBoy = new StewardPO();
        stewardBoy.setBirthDate(BIRTH_DATE);
        stewardBoy.setGender("Man");
        stewardBoy.setName("Juraj");
        stewardBoy.setSurname("Janosik");
        stewardBoy.setNationality("Slovak");

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
        flightMagic.setDepartureTime(ARRIVAL);
        flightMagic.setArrivalTime(DEPARTURE);
        flightMagic.setStewards(stewardSet);

        airplaneDao.create(airplaneBig);
        airplaneDao.create(airplaneSmall);
        destinationDao.create(destination);
        destinationDao.create(origin);
        stewardDao.create(stewardGirl);
        stewardDao.create(stewardBoy);
        flightDao.create(flight);
        flightDao.create(flightMagic);
    }

    @Test
    public void testFindById() {
        FlightPO found = flightDao.findById(flight.getId());
        Assert.assertEquals(found, flight);
    }

    @Test
    public void testFindNothing() {
        FlightPO found = flightDao.findById(flight.getId() + 1);
        Assert.assertNull(found);
    }

    @Test
    public void testUpdatePlane() {
        Assert.assertEquals(flight.getAirplane(), airplaneBig);
        flight.setAirplane(airplaneSmall);
        flightDao.update(flight);
        Assert.assertEquals(flight.getAirplane(), airplaneSmall);
    }

    @Test
    public void testDelete() {
        Assert.assertNotNull(flightDao.findById(flight.getId()));
        flightDao.delete(flight);
        Assert.assertNull(flightDao.findById(flight.getId()));
    }

    @Test
    public void testFindAll() {
        List<FlightPO> flights = flightDao.findAll();
        Assert.assertEquals(flights.size(), 2);
        Assert.assertTrue(flights.contains(flight));
        Assert.assertTrue(flights.contains(flightMagic));
    }
}