package cz.muni.fi.pa165.airportmanager.service.services;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.AirplaneRepository;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.service.exceptions.AirportManagerDataAccessException;
import cz.muni.fi.pa165.airportmanager.service.services.impl.AirplaneServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author kotrc
 * Created on 26.11.2018
 */
public class AirplaneServiceTest {

    @Mock
    private AirplaneRepository airplaneRepository;

    private AirplaneService airplaneService;

    private AirplanePO airplane1;
    private AirplanePO airplane2;
    private AirplanePO airplane3;

    private List<AirplanePO> airplanes;
    private List<AirplanePO> ryanAirplanes;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        airplaneService = new AirplaneServiceImpl(airplaneRepository);

        airplanes = new ArrayList<>();
        ryanAirplanes = new ArrayList<>();

        airplane1 = AirplanePO.builder()
                .id(1L)
                .name("LCE7894")
                .capacity(13)
                .company("CSA")
                .type("Boeing 737")
                .build();

        airplane2 = AirplanePO.builder()
                .id(2L)
                .name("KCE2145")
                .capacity(230)
                .company("Ryan Air")
                .type("Airbus A370")
                .build();

        airplane3 = AirplanePO.builder()
                .id(3L)
                .name("Hamburger")
                .capacity(69)
                .company("Ryan Air")
                .type("Boeing 666")
                .build();

        airplanes.add(airplane1);
        airplanes.add(airplane2);
        airplanes.add(airplane3);
        ryanAirplanes.add(airplane2);
        ryanAirplanes.add(airplane3);
    }

    @Test
    public void getAllAirplanes() {
        Mockito.when(airplaneRepository.findAll()).thenReturn(airplanes);
        List<AirplanePO> found = airplaneService.getAllAirplanes();
        assertEquals(airplanes, found);
    }

    @Test
    public void getAirplaneById() {
        Mockito.when(airplaneRepository.findById(airplane2.getId())).thenReturn(Optional.of(airplane2));
        AirplanePO found = airplaneService.getAirplaneById(airplane2.getId());
        assertEquals(found, airplane2);
    }

    @Test(expected = AirportManagerDataAccessException.class)
    public void getNonExistingAirplaneId() {
        airplaneService.getAirplaneById(666L);
    }

    @Test
    public void createAirplane() {
        airplaneService.createAirplane(airplane1);
        Mockito.verify(airplaneRepository).save(airplane1);
    }

    @Test
    public void deleteAirplane() {
        airplaneService.deleteAirplane(airplane1.getId());
        Mockito.verify(airplaneRepository).delete(airplane1);
    }

    @Test(expected = AirportManagerDataAccessException.class)
    public void DeleteNonExistingAirplane() {
        airplaneService.deleteAirplane(87L);
    }

    @Test
    public void findAirplaneByName() {
        Mockito.when(airplaneRepository.findByCompany("Ryan Air")).thenReturn(ryanAirplanes);
        AirplanePO found = airplaneService.findAirplaneByName("Hamburger");
        assertEquals(airplanes, found);
    }

    @Test
    public void findCompanyAirplanes() {
        Mockito.when(airplaneRepository.findByCompany("Ryan Air")).thenReturn(ryanAirplanes);
        List<AirplanePO> found = airplaneService.findCompanyAirplanes("Ryan Air");
        assertEquals(airplanes, found);
    }
}