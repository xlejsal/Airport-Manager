package cz.muni.fi.pa165.airportmanager.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"cz.muni.fi.pa165.airportmanager.persistence", "cz.muni.fi.pa165.airportmanager.service"})
public class AirportManagerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportManagerServiceApplication.class, args);
    }
}
