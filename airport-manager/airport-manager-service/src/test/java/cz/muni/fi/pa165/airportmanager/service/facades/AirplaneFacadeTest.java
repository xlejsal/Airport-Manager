package cz.muni.fi.pa165.airportmanager.service.facades;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.service.facades.impl.AirplaneFacadeImpl;
import cz.muni.fi.pa165.airportmanager.service.services.AirplaneService;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author kotrc
 * Created on 26.11.2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AirplaneFacadeTest {

    @Mock
    private AirplaneService airplaneService;

    @Mock
    private BeanMappingService beanMappingService;

    private AirplaneFacade airplaneFacade;

    private AirplanePO airplane1;
    private AirplanePO airplane2;
    private AirplanePO airplane3;

    private List<AirplanePO> airplanes;
    private List<AirplanePO> ryanAirplanes;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        airplaneFacade = new AirplaneFacadeImpl(airplaneService, beanMappingService);
        airplanes = new ArrayList<>();
        ryanAirplanes = new ArrayList<>();

        airplane1 = AirplanePO.builder()
                .id(1L)
                .name("LCE7894")
                .capacity(180)
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
        List<AirplaneDTO> airplaneDTO = new ArrayList<>();
        Mockito.when(airplaneService.getAllAirplanes()).thenReturn(airplanes);
        Mockito.when(beanMappingService.mapTo(airplanes, AirplaneDTO.class)).thenReturn(airplaneDTO);

        assertEquals(airplaneDTO, airplaneFacade.getAllAirplanes());
    }

    @Test
    public void getAirplaneById() {
        AirplaneDTO airplaneDTO = Mockito.mock(AirplaneDTO.class);
        Mockito.when(beanMappingService.mapTo(airplane1, AirplaneDTO.class)).thenReturn(airplaneDTO);

        assertEquals(airplaneFacade.getAirplaneById(1L), airplaneDTO);
        Mockito.verify(airplaneService).getAirplaneById(1L);
    }

    @Test
    public void createAirplane() {
        AirplaneDTO airplaneDTO = Mockito.mock(AirplaneDTO.class);
        AirplanePO airplanePO = Mockito.mock(AirplanePO.class);
        Mockito.when(beanMappingService.mapTo(airplaneDTO, AirplanePO.class)).thenReturn(airplanePO);

        airplaneFacade.createAirplane(airplaneDTO);
        Mockito.verify(airplaneService).createAirplane(airplanePO);
    }

    @Test
    public void deleteAirplane() {
        AirplanePO airplanePO = Mockito.mock(AirplanePO.class);
        Mockito.when(airplaneService.getAirplaneById(69L)).thenReturn(airplanePO);

        airplaneFacade.deleteAirplane(69L);
        Mockito.verify(airplaneService).deleteAirplane(69L);
    }

    @Test
    public void findAirplaneByName() {
        AirplaneDTO airplaneDTO = Mockito.mock(AirplaneDTO.class);
        Mockito.when(airplaneService.findAirplaneByName("Hamburger")).thenReturn(airplane3);
        Mockito.when(beanMappingService.mapTo(airplane1, AirplaneDTO.class)).thenReturn(airplaneDTO);

        assertEquals(airplaneFacade.findAirplaneByName("Hamburger"), airplaneDTO);
        Mockito.verify(airplaneService).findAirplaneByName("Hamburger");
    }

    @Test
    public void findCompanyAirplanes() {
        List<AirplaneDTO> airplaneDTO = new ArrayList<>();
        Mockito.when(airplaneService.findCompanyAirplanes("Ryan Air")).thenReturn(ryanAirplanes);
        Mockito.when(beanMappingService.mapTo(ryanAirplanes, AirplaneDTO.class)).thenReturn(airplaneDTO);

        assertEquals(airplaneFacade.findCompanyAirplanes("Ryan Air"), airplaneDTO);
        Mockito.verify(airplaneService).findCompanyAirplanes("Ryan Air");
    }
}