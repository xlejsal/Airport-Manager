package cz.muni.fi.pa165.airportmanager.persistence.repositories;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vojtech Lejsal (lejsal.vojtech@gmail.com)
 * Created on 2018-10-24
 */
public interface FlightRepository extends JpaRepository<FlightPO, Long> {
    public FlightPO findByFlightNumber(String flightNumber);

    /**
     * Finds all flights that intersect with the given interval and use a plane with the specified id.
     * @param from Start of interval.
     * @param to End of interval.
     * @param airplaneId Id of the plane.
     * @return All flights that intersect with the interval and use the specified plane.
     */
    @Query("select f from FlightPO where ((f.departureTime between ?1 and ?2) or (f.arrivalTime between ?1 and ?2) or (f.departureTime <= ?1 and f.arrivalTime >= ?2)) and f.airplane.id = ?3")
    List<FlightPO> findAllFlightsFromToWithAirplaneId(LocalDateTime from, LocalDateTime to, Long airplaneId);
}
