package cz.muni.fi.pa165.airportmanager.service.services.impl;

import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    private ModelMapper mapper = new ModelMapper();

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new LinkedList<>();
        for (Object object : objects) {
            mappedCollection.add(mapper.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        return mapper.map(u,mapToClass);
    }

    public ModelMapper getMapper(){
        return mapper;
    }
}
