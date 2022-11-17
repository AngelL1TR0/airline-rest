package org.iesfm.airline.services;

import org.iesfm.airline.dao.FlightDAO;
import org.iesfm.airline.dao.PassengerDAO;
import org.iesfm.airline.entity.Flight;
import org.iesfm.airline.entity.Passenger;
import org.iesfm.airline.services.exceptions.FlightNotFoundException;
import org.iesfm.airline.services.exceptions.PassengerExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightDAO flightDAO;

    @Autowired
    private PassengerDAO passengerDAO;

    public List<Flight> listFlights() {
        return flightDAO.list();
    }

    public Flight getFlight(
            String flightId
    ) {
        return flightDAO.getFlight(flightId);
    }

    public boolean add(Flight flight) {

        return flightDAO.add(flight);
    }

    public boolean updateFlight(
            String flightId,
            Flight flight
    ) {
        return flightDAO.updateFlight(flightId, flight);
    }

    public boolean delete(String flightId) {
        return flightDAO.delete(flightId);
    }

    public List<Passenger> listFlightPassengers(String flightId)
            throws FlightNotFoundException {
        if (flightDAO.getFlight(flightId) == null) {
            throw new FlightNotFoundException(flightId);
        } else {
            return passengerDAO.listPassengers(flightId);
        }
    }

    public void addPassenger(Passenger passenger)
            throws FlightNotFoundException, PassengerExistException {
        if(flightDAO.getFlight(passenger.getFlightId()) == null) {
            throw new FlightNotFoundException(passenger.getFlightId());
        } else if (!passengerDAO.addPassenger(passenger)) {
            throw new PassengerExistException(passenger.getFlightId(), passenger.getNif());
        }
    }
}