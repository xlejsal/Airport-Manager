package cz.muni.fi.pa165.airportmanager.service.sample_data;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.enums.Gender;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.*;
import cz.muni.fi.pa165.airportmanager.service.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author: Stepan Benes
 */

@Component
@Transactional
public class SampleDataLoading {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoading.class);

    @Autowired
    private UserService userService;
    @Autowired
    private StewardService stewardService;
    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private DestinationService destinationService;

    @EventListener
    public void createData(ApplicationReadyEvent event){

        Set<StewardPO> stewards = new HashSet<>();

        log.info("Loading sample data.");

        UserPO admin = UserPO.builder()
                .login("admin")
                .email("admin@yahoo.com")
                .name("Administratorus")
                .surname("Rex")
                .admin(true)
                .build();

        UserPO user = UserPO.builder()
                .login("user")
                .email("lakatos14@seznam.cz")
                .name("Ferrus")
                .surname("Lakatos")
                .build();

        StewardPO stew1 = StewardPO.builder()
                .id(1L)
                .name("Andrej")
                .surname("Danko")
                .gender(Gender.Kapitan)
                .birthDate(LocalDate.of(1974, Month.SEPTEMBER, 15))
                .nationality("SVK")
                .flights(new HashSet<FlightPO>())
                .build();

        StewardPO stew2 = StewardPO.builder()
                .id(2L)
                .name("Thomas")
                .surname("Dubois")
                .gender(Gender.Male)
                .birthDate(LocalDate.of(1994, Month.DECEMBER, 17))
                .nationality("USA")
                .flights(new HashSet<FlightPO>())
                .build();

        StewardPO stew3 = StewardPO.builder()
                .id(3L)
                .name("Arthur")
                .surname("Morgan")
                .gender(Gender.Male)
                .birthDate(LocalDate.of(1856, Month.MAY, 24))
                .nationality("USA")
                .flights(new HashSet<FlightPO>())
                .build();

        AirplanePO airplane1 = AirplanePO.builder()
                .id(1L)
                .name("LCE7894")
                .capacity(13)
                .company("CSA")
                .type("Boeing 737")
                .build();

        AirplanePO airplane2 = AirplanePO.builder()
                .id(2L)
                .name("KCE2145")
                .capacity(230)
                .company("Ryan Air")
                .type("Airbus A370")
                .build();

        DestinationPO destination1 = DestinationPO.builder()
                .id(1L)
                .airportCode("KLM")
                .city("Bratislava")
                .country("Slovakia")
                .build();

        DestinationPO destination2 = DestinationPO.builder()
                .id(2L)
                .airportCode("QQQ")
                .city("Brno")
                .country("Czech Republic")
                .build();

        stewards.add(stew1);
        stewards.add(stew2);

        FlightPO flight1 = FlightPO.builder()
                .id(1L)
                .flightNumber("KDH-337")
                .airplane(airplane1)
                .departureTime(LocalDateTime.of(2018, 9, 10, 9, 35))
                .arrivalTime(LocalDateTime.of(2018, 9, 10, 19, 50))
                .origin(destination1)
                .destination(destination2)
                .stewards(stewards)
                .build();

        FlightPO flight2 = FlightPO.builder()
                .id(2L)
                .flightNumber("AAA-Auto")
                .airplane(airplane2)
                .departureTime(LocalDateTime.of(2018, 8, 10, 9, 35))
                .arrivalTime(LocalDateTime.of(2018, 8, 10, 19, 50))
                .origin(destination2)
                .destination(destination1)
                .stewards(stewards)
                .build();

        FlightPO flight3 = FlightPO.builder()
                .id(3L)
                .flightNumber("pp-69")
                .airplane(airplane1)
                .departureTime(LocalDateTime.of(2017, 8, 10, 9, 35))
                .arrivalTime(LocalDateTime.of(2017, 8, 10, 19, 50))
                .origin(destination2)
                .destination(destination1)
                .stewards(stewards)
                .build();


        airplaneService.createAirplane(airplane1);
        airplaneService.createAirplane(airplane2);
        stewardService.createSteward(stew1);
        stewardService.createSteward(stew2);
        stewardService.createSteward(stew3);
        destinationService.createDestination(destination1);
        destinationService.createDestination(destination2);
        flightService.createFlight(flight1);
        flightService.createFlight(flight2);
        flightService.createFlight(flight3);

        userService.registerUser(admin, "admin");
        userService.registerUser(user, "user");

        log.info("Loaded sample data.");
    }
}
