package org.iesfm.airline.dao;

import org.iesfm.airline.entity.Passenger;

import java.util.List;

public interface PassengerDAO {
    List<Passenger> listPassengers(String flightId);

    Passenger getPassengers(String flightId, String nif);

    boolean existPassenger(String flightId, String nif);

    boolean deletePassenger(String flightId, String nif);

    boolean addPassenger(Passenger passenger);

    boolean updatePassenger(Passenger passenger);
}