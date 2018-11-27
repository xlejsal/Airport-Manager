package cz.muni.fi.pa165.airportmanager.service.facade;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.StewardPO;
import cz.muni.fi.pa165.airportmanager.service.facades.impl.StewardFacadeImpl;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.services.StewardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * A basic test set for the StewardFacadeImpl, just to check everything is calling what it should
 * @author Stepan Benes
 * Created on 2018-11-26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StewardFacadeImplTest {
    private StewardFacade facade;

    @Mock
    private StewardService service;
    @Mock
    private BeanMappingService mapper;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        facade = new StewardFacadeImpl(service, mapper);
    }

    @Test
    public void findAllTest(){
        List<StewardPO> mockStewards = new ArrayList<>();
        when(service.getAllStewards()).thenReturn(mockStewards);
        List<StewardDTO> mockDTOs = new ArrayList<>();
        when(mapper.mapTo(mockStewards, StewardDTO.class)).thenReturn(mockDTOs);

        assertEquals(facade.getAllStewards(), mockDTOs);
        verify(service).getAllStewards();
    }

    @Test
    public void getByIdTest(){
        StewardPO mockSteward = Mockito.mock(StewardPO.class);
        when(service.getStewardById(24L)).thenReturn(mockSteward);
        StewardDTO mockDTO = Mockito.mock(StewardDTO.class);
        when(mapper.mapTo(mockSteward, StewardDTO.class)).thenReturn(mockDTO);

        assertEquals(facade.getStewardById(24L), mockDTO);
        verify(service).getStewardById(24L);
    }

    @Test
    public void createTest(){
        StewardPO mockSteward = Mockito.mock(StewardPO.class);
        StewardDTO mockDTO = Mockito.mock(StewardDTO.class);
        when(mapper.mapTo(mockDTO, StewardPO.class)).thenReturn(mockSteward);

        facade.createSteward(mockDTO);
        verify(service).createSteward(mockSteward);
    }

    @Test
    public void deleteTest(){
        StewardPO mockSteward = Mockito.mock(StewardPO.class);
        when(service.getStewardById(24L)).thenReturn(mockSteward);

        facade.deleteSteward(24L);
        verify(service).deleteSteward(24L);
    }

    @Test
    public void isAvailableTest(){
        when(service.isAvailableFromTo(24L, LocalDateTime.now(), LocalDateTime.now().plusHours(2))).thenReturn(true);

        assertEquals(facade.isAvailableFromTo(24L, LocalDateTime.now(), LocalDateTime.now().plusHours(2)), true);
        verify(service).isAvailableFromTo(24L, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    }
}
