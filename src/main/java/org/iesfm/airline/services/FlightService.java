package org.iesfm.airline.services;

import org.iesfm.airline.dao.InMemoryFlightDAO;
import org.iesfm.airline.dao.InMemoryPassengerDAO;
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
        private InMemoryFlightDAO inMemoryFlightDAO;

        @Autowired
        private InMemoryPassengerDAO inMemoryPassengerDAO;

        public List<Flight> listFlights() {
            return inMemoryFlightDAO.list();
        }

        public Flight getFlight(
                String flightId
        ) {
            return inMemoryFlightDAO.getFlight(flightId);
        }

        public boolean add(Flight flight) {
            return inMemoryFlightDAO.add(flight);
        }

        public boolean updateFlight(
                String flightId,
                Flight flight
        ) {
            return inMemoryFlightDAO.updateFlight(flightId, flight);
        }

        public boolean delete(String flightId) {
            return inMemoryFlightDAO.delete(flightId);
        }

        public List<Passenger> listFlightPassengers(String flightId)
                throws FlightNotFoundException {
            if (inMemoryFlightDAO.getFlight(flightId) == null) {
                throw new FlightNotFoundException(flightId);
            } else {
                return inMemoryPassengerDAO.listPassengers(flightId);

            }
        }


        public boolean addPassanger(String  flightId, Passenger passenger)
                throws FlightNotFoundException, PassengerExistException {
            return inMemoryPassengerDAO.addPassenger(flightId, passenger);
        }
    }