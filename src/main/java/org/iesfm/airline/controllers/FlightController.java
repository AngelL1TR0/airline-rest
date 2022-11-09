package org.iesfm.airline.controllers;

import org.iesfm.airline.entity.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class FlightController {
    private Map<String, Flight> flights = new HashMap<>();

    @GetMapping(path = "/flights")
    public ResponseEntity <@Valid List<Flight>> list() {
        return ResponseEntity.ok(
                flights
                        .values()
                        .stream()
                        .collect(Collectors.toList())
        );

    }

    @GetMapping(path = "/flights/{flightId}")
    public ResponseEntity <Flight> getFlight(
            @PathVariable("flightId") String flightId
    ) {
        if (flights.containsKey(flightId)) {
            return ResponseEntity.ok( flights.get(flightId));
        } else {
          return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/flights")
    public ResponseEntity <Void> add(@Valid @RequestBody Flight flight) {
        if (flights.containsKey(flight.getId())) {
             return ResponseEntity.status(
                    HttpStatus.CONFLICT
            ).build();
        } else {
            flights.put(flight.getId(), flight);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping(path = "/flights/{flightId}")
    public ResponseEntity <Void> updateFlight(
            @PathVariable("flightId") String flightId,
            @RequestBody Flight flight
    ) {
        // 3 -> Madrid - Londres
        // 4 -> 1 Madrid - Berlin
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
            flights.put(flight.getId(), flight);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/flights/{flightId}")
    public ResponseEntity <Void> delete(@PathVariable("flightId") String flightId) {
        if (flights.containsKey(flightId)) {
            flights.remove(flightId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}