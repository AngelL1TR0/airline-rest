package org.iesfm.airline.dao;

import org.iesfm.airline.entity.Passenger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryPassengerDAO implements PassengerDAO {
    // Por cada vuelo, habrá un mapa de pasajeros
    private Map<String, Map<String, Passenger>> passengers = new HashMap<>();

    /**
     * Dado un número de vuelo, devuelve sus pasajeros
     * @param flightId
     * @return
     */
    @Override
    public List<Passenger> listPassengers(String flightId) {
        return new ArrayList<>(getFlightPassengers(flightId).values());
    }

    @Override
    public Passenger getPassengers(String flightId, String nif) {
        return getFlightPassengers(flightId).get(nif);
    }

    /**
     * Dado un número de vuelo y un nif, devuelve si el pasajero está en el vuelo
     * @param flightId
     * @param nif
     * @return
     */
    @Override
    public boolean existPassenger(String flightId, String nif) {
        return getFlightPassengers(flightId).containsKey(nif);
    }

    @Override
    public boolean deletePassenger(String flightId, String nif) {
        return getFlightPassengers(flightId).remove(nif) != null;
    }

    /**
     * Añada un pasajero al vuelo. Devuelve false si el pasajero ya existe en el vuelo
     * @param flightId
     * @param passenger
     * @return
     */
    @Override
    public boolean addPassenger(String flightId, Passenger passenger) {
        if (existPassenger(flightId, passenger.getNif())) {
            return false;
        } else {
            getFlightPassengers(flightId).put(passenger.getNif(), passenger);
            return true;
        }
    }

    /**
     * Actualiza los datos de un pasajero en un vuelo. Devuelve false si el
     * pasajero no existe en el vuelo
     * @param flightNumber
     * @param passenger
     * @return
     */
    @Override
    public boolean updatePassenger(String flightNumber, Passenger passenger) {
        if (existPassenger(flightNumber, passenger.getNif())) {
            getFlightPassengers(flightNumber).put(passenger.getNif(), passenger);
            return true;
        } else {
            return false;
        }
    }

    private Map<String, Passenger> getFlightPassengers(String flightId) {
        passengers.putIfAbsent(flightId, new HashMap<>());
        return passengers.get(flightId);
    }
}

