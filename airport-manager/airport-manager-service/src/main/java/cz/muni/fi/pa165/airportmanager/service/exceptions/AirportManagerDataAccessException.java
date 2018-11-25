package cz.muni.fi.pa165.airportmanager.service.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Exception to be thrown during any DAO layer exceptions
 * @author Stepan Benes
 * Created on 2018-11-25
 */
public class AirportManagerDataAccessException extends DataAccessException {

    public AirportManagerDataAccessException(String msg) {
        super(msg);
    }

    public AirportManagerDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
