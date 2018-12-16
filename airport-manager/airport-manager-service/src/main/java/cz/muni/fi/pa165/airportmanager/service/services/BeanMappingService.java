package cz.muni.fi.pa165.airportmanager.service.services;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;


public interface BeanMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public ModelMapper getMapper();
}
