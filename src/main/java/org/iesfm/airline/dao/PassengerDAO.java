package org.iesfm.airline.dao;

import org.iesfm.airline.entity.Passenger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PassengerDAO {

        // Por cada vuelo, habrá un mapa de pasajeros
        private Map<String, Map<String, Passenger>> passengers = new HashMap<>();

        /**
         * Dado un número de vuelo, devuelve sus pasajeros
         *
         * @param flightId
         * @return
         */
        public List<Passenger> listPassengers(String flightId) {
            return new ArrayList<>(getFlightPassengers(flightId).values());
        }

        /**
         * Dado un número de vuelo y un nif, devuelve si el pasajero está en el vuelo
         *
         * @param flightId
         * @param nif
         * @return
         */
        public boolean existPassenger(String flightId, String nif) {
            return getFlightPassengers(flightId).containsKey(nif);
        }

        /**
         * Añada un pasajero al vuelo. Devuelve false si el pasajero ya existe en el vuelo
         *
         * @param flightId
         * @param passenger
         * @return
         */
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
         *
         * @param flightNumber
         * @param passenger
         * @return
         */
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

