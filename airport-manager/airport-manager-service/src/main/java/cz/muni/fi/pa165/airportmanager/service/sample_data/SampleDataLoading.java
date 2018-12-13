package cz.muni.fi.pa165.airportmanager.service.sample_data;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.UserPO;
import cz.muni.fi.pa165.airportmanager.service.services.AirplaneService;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import cz.muni.fi.pa165.airportmanager.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;

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

    @EventListener
    public void createData(ApplicationReadyEvent event){
        log.info("Loading sample data.");

        UserPO admin = UserPO.builder()
                .id(1L)
                .passwordHash("1611651561")
                .login("admin")
                .email("admin@yahoo.com")
                .name("Administratorus")
                .surname("Rex")
                .admin(true)
                .build();

        UserPO fero = UserPO.builder()
                .id(2L)
                .login("fero14")
                .passwordHash("1515665")
                .email("lakatos14@seznam.cz")
                .name("Ferrus")
                .surname("Lakatos")
                .build();

        StewardPO stew1 = StewardPO.builder()
                .id(1L)
                .name("Andrej")
                .surname("Danko")
                .gender("Kapitan")
                .birthDate(LocalDate.of(1974, Month.SEPTEMBER, 15))
                .nationality("SVK")
                .flights(new HashSet<FlightPO>())
                .build();

        StewardPO stew2 = StewardPO.builder()
                .id(2L)
                .name("Thomas")
                .surname("Dubois")
                .gender("Man")
                .birthDate(LocalDate.of(1994, Month.DECEMBER, 17))
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

        /* needs fixing
        userService.registerUser(admin, "1234");
        userService.registerUser(fero, "tukabel");
        */
        airplaneService.createAirplane(airplane1);
        airplaneService.createAirplane(airplane2);
        stewardService.createSteward(stew1);
        stewardService.createSteward(stew2);

        log.info("Loaded sample data.");
    }
}
