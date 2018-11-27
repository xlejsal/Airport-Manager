package cz.muni.fi.pa165.airportmanager.service.facade;

import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.DestinationPO;
import cz.muni.fi.pa165.airportmanager.service.facades.impl.DestinationFacadeImpl;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.services.DestinationService;
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
public class DestinationFacadeImplTest {

    private DestinationFacade facade;

    @Mock
    private DestinationService service;
    @Mock
    private BeanMappingService mapper;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        facade = new DestinationFacadeImpl(service, mapper);
    }

    @Test
    public void getAllDestinationsTest() {
        List<DestinationPO> mockDestinations = new ArrayList<>();
        when(service.getAllDestinations()).thenReturn(mockDestinations);
        List<DestinationDTO> mockDTOs = new ArrayList<>();
        when(mapper.mapTo(mockDestinations, DestinationDTO.class)).thenReturn(mockDTOs);

        assertEquals(facade.getAllDestinations(), mockDTOs);
        verify(service).getAllDestinations();
    }

    @Test
    public void getDestinationByIdTest() {
        DestinationPO mockDestination = Mockito.mock(DestinationPO.class);
        when(service.getDestinationById(24L)).thenReturn(mockDestination);
        DestinationDTO mockDTO = Mockito.mock(DestinationDTO.class);
        when(mapper.mapTo(mockDestination, DestinationDTO.class)).thenReturn(mockDTO);

        assertEquals(facade.getDestinationById(24L), mockDTO);
        verify(service).getDestinationById(24L);
    }

    @Test
    public void createDestinationTest() {
        DestinationPO mockDestination = Mockito.mock(DestinationPO.class);
        DestinationDTO mockDTO = Mockito.mock(DestinationDTO.class);
        when(mapper.mapTo(mockDTO, DestinationPO.class)).thenReturn(mockDestination);

        facade.createDestination(mockDTO);
        verify(service).createDestination(mockDestination);
    }

    @Test
    public void deleteDestinationTest() {
        DestinationPO mockDestination = Mockito.mock(DestinationPO.class);
        when(service.getDestinationById(24L)).thenReturn(mockDestination);

        facade.deleteDestination(24L);
        verify(service).deleteDestination(24L);
    }

    @Test
    public void findCityDestinationsTest() {
        List<DestinationPO> mockDestinations = new ArrayList<>();
        when(service.findCityDestinations("Brno")).thenReturn(mockDestinations);
        List<DestinationDTO> mockDTOs = new ArrayList<>();
        when(mapper.mapTo(mockDestinations, DestinationDTO.class)).thenReturn(mockDTOs);

        assertEquals(facade.findCityDestinations("Brno"), mockDTOs);
        verify(service).findCityDestinations("Brno");
    }

    @Test
    public void findCountryDestinationsTest() {
        List<DestinationPO> mockDestinations = new ArrayList<>();
        when(service.findCountryDestinations("Czechia")).thenReturn(mockDestinations);
        List<DestinationDTO> mockDTOs = new ArrayList<>();
        when(mapper.mapTo(mockDestinations, DestinationDTO.class)).thenReturn(mockDTOs);

        assertEquals(facade.findCountryDestinations("Czechia"), mockDTOs);
        verify(service).findCountryDestinations("Czechia");
    }
}
