package org.iesfm.airline.dao;

import org.iesfm.airline.entity.Flight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightDAO {

    private Map<String, Flight> flights = new HashMap<>();

    public List<Flight> list() {
        return
                flights
                        .values()
                        .stream()
                        .collect(Collectors.toList());
    }

    public Flight getFlight(
            String flightId
    ) {
        return flights.get(flightId);
    }

    public boolean add(Flight flight) {
        if (!flights.containsKey(flight.getId())) {
            flights.put(flight.getId(), flight);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateFlight(
            String flightId,
            Flight flight
    ) {
        // 3 -> Madrid - Londres
        // 4 -> 1 Madrid - Berlin
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
            flights.put(flight.getId(), flight);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete( String flightId) {
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
            return true;
        } else {
            return false;
        }
    }
}
