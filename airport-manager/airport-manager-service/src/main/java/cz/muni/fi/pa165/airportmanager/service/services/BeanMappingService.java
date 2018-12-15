package cz.muni.fi.pa165.airportmanager.service.services;

import java.util.Collection;
import java.util.List;

import com.github.dozermapper.core.Mapper;


public interface BeanMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
