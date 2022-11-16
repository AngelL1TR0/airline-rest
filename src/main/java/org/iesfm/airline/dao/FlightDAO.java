package org.iesfm.airline.dao;

import org.iesfm.airline.entity.Flight;

import java.util.List;

public interface FlightDAO {
    List<Flight> list();

    Flight getFlight(
            String flightId
    );

    boolean add(Flight flight);

    boolean updateFlight(
            String flightId,
            Flight flight
    );

    boolean delete(String flightId);
}
