package org.iesfm.airline.controllers;

import org.iesfm.airline.entity.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class FlightController {
    private Map<String, Flight> flights = new HashMap<>();

    @GetMapping(path = "/flights")
    public List<Flight> list() {
        return flights
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/flights/{flightId}")
    public Flight getFlight(
            @PathVariable("flightId") String flightId
    ) {
        if (flights.containsKey(flightId)) {
            return flights.get(flightId);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No encontrado"
            );
        }
    }

    @PostMapping(path = "/flights")
    public void add(@RequestBody Flight flight) {
        if (flights.containsKey(flight.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe el vuelo con id " + flight.getId()
            );
        } else {
            flights.put(flight.getId(), flight);
        }
    }

    @PutMapping(path = "/flights/{flightId}")
    public void updateFlight(
            @PathVariable("flightId") String flightId,
            @RequestBody Flight flight
    ) {
        // 3 -> Madrid - Londres
        // 4 -> 1 Madrid - Berlin
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
            flights.put(flight.getId(), flight);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No encontrado"
            );
        }
    }

    @DeleteMapping(path = "/flights/{flightId}")
    public void delete(@PathVariable("flightId") String flightId) {
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No encontrado"
            );
        }
    }
}