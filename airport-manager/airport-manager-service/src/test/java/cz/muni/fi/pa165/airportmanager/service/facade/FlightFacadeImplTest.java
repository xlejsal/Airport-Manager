package cz.muni.fi.pa165.airportmanager.service.facade;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.facades.impl.FlightFacadeImpl;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.services.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
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
public class FlightFacadeImplTest {

    private FlightFacade facade;

    @Mock
    private FlightService service;
    @Mock
    private BeanMappingService mapper;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        facade = new FlightFacadeImpl(service, mapper);
    }

    @Test
    public void createFlightTest() {
        FlightPO mockFlight = Mockito.mock(FlightPO.class);
        FlightDTO mockDTO = Mockito.mock(FlightDTO.class);
        when(mapper.mapTo(mockDTO, FlightPO.class)).thenReturn(mockFlight);

        facade.createFlight(mockDTO);
        verify(service).createFlight(mockFlight);
    }

    @Test
    public void getAllFlightsTest() {
        List<FlightPO> mockFlights = new ArrayList<>();
        when(service.getAllFlights()).thenReturn(mockFlights);
        List<FlightDTO> mockDTOs = new ArrayList<>();
        when(mapper.mapTo(mockFlights, FlightDTO.class)).thenReturn(mockDTOs);

        assertEquals(facade.getAllFlights(), mockDTOs);
        verify(service).getAllFlights();
    }

    @Test
    public void getFlightByIdTest() {
        FlightPO mockFlight = Mockito.mock(FlightPO.class);
        when(service.getFlightById(3L)).thenReturn(mockFlight);
        FlightDTO mockDTO = Mockito.mock(FlightDTO.class);
        when(mapper.mapTo(mockFlight, FlightDTO.class)).thenReturn(mockDTO);

        assertEquals(facade.getFlightById(3L), mockDTO);
        verify(service).getFlightById(3L);
    }

    @Test
    public void getFlightByFlightNumberTest() {
        FlightPO mockFlight = Mockito.mock(FlightPO.class);
        when(service.getFlightByFlightNumber("OK-475")).thenReturn(mockFlight);
        FlightDTO mockDTO = Mockito.mock(FlightDTO.class);
        when(mapper.mapTo(mockFlight, FlightDTO.class)).thenReturn(mockDTO);

        assertEquals(facade.getFlightByFlightNumber("OK-475"), mockDTO);
        verify(service).getFlightByFlightNumber("OK-475");
    }

    @Test
    public void deleteFlightTest() {
        FlightPO mockFlight = Mockito.mock(FlightPO.class);
        when(service.getFlightById(2L)).thenReturn(mockFlight);

        facade.deleteFlight(2L);
        verify(service).deleteFlight(2L);
    }

    @Test
    public void addStewardTest() {
        FlightPO mockFlight = Mockito.mock(FlightPO.class);
        FlightDTO mockFlightDTO = Mockito.mock(FlightDTO.class);
        StewardPO mockSteward = Mockito.mock(StewardPO.class);
        StewardDTO mockStewardDTO = Mockito.mock(StewardDTO.class);
        when(mapper.mapTo(mockFlightDTO, FlightPO.class)).thenReturn(mockFlight);
        when(mapper.mapTo(mockStewardDTO, StewardPO.class)).thenReturn(mockSteward);

        facade.addSteward(mockStewardDTO, mockFlightDTO);
        verify(service).addSteward(mockSteward, mockFlight);
    }

    @Test
    public void removeStewardTest() {
        FlightPO mockFlight = Mockito.mock(FlightPO.class);
        FlightDTO mockFlightDTO = Mockito.mock(FlightDTO.class);
        StewardPO mockSteward = Mockito.mock(StewardPO.class);
        StewardDTO mockStewardDTO = Mockito.mock(StewardDTO.class);
        when(mapper.mapTo(mockFlightDTO, FlightPO.class)).thenReturn(mockFlight);
        when(mapper.mapTo(mockStewardDTO, StewardPO.class)).thenReturn(mockSteward);

        facade.removeSteward(mockStewardDTO, mockFlightDTO);
        verify(service).removeSteward(mockSteward, mockFlight);
    }
}
