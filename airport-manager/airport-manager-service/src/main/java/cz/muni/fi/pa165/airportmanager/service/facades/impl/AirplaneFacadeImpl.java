package cz.muni.fi.pa165.airportmanager.service.facades.impl;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.AirplanePO;
import cz.muni.fi.pa165.airportmanager.service.services.AirplaneService;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the AirplaneFacade interface
 * @author Stepan Benes
 * Created on 2018-11-20
 */

@Service
@Transactional
public class AirplaneFacadeImpl implements AirplaneFacade {

    private AirplaneService airplaneService;
    private BeanMappingService beanMapper;

    @Autowired
    public AirplaneFacadeImpl(@Qualifier("airplaneService") AirplaneService service, BeanMappingService mapper){
        airplaneService = service;
        beanMapper = mapper;
    }

    @Override
    public List<AirplaneDTO> getAllAirplanes(){ return beanMapper.mapTo(airplaneService.getAllAirplanes(), AirplaneDTO.class); }

    @Override
    public AirplaneDTO getAirplaneById(Long Id){ return beanMapper.mapTo(airplaneService.getAirplaneById(Id), AirplaneDTO.class); }

    @Override
    public void createAirplane(AirplaneDTO airplane){ airplaneService.createAirplane(beanMapper.mapTo(airplane, AirplanePO.class)); }

    @Override
    public void deleteAirplane(Long Id){ airplaneService.deleteAirplane(Id);  }

    @Override
    public AirplaneDTO findAirplaneByName(String name){ return beanMapper.mapTo(airplaneService.findAirplaneByName(name), AirplaneDTO.class);  }

    @Override
    public List<AirplaneDTO> findCompanyAirplanes(String company){ return beanMapper.mapTo(airplaneService.findCompanyAirplanes(company), AirplaneDTO.class);   }
}
