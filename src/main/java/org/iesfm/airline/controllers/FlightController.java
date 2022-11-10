package org.iesfm.airline.controllers;

import org.iesfm.airline.entity.Flight;
import org.iesfm.airline.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FlightService flightService;

    @GetMapping(path = "/flights")
    public ResponseEntity<List<Flight>> list() {
        return ResponseEntity.ok(
                flightService.listFlights()
        );
    }

    @GetMapping(path = "/flights/{flightId}")
    public ResponseEntity<Flight> getFlight(
            @PathVariable("flightId") String flightId
    ) {
        Flight flight = flightService.getFlight(flightId);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            //  ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/flights")
    public ResponseEntity<Void> add(@Valid @RequestBody Flight flight) {
        if (flightService.add(flight)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/flights/{flightId}")
    public ResponseEntity<Void> updateFlight(
            @PathVariable("flightId") String flightId,
            @Valid @RequestBody Flight flight
    ) {
        // 3 -> Madrid - Londres
        // 4 -> 1 Madrid - Berlin
        if (flightService.updateFlight(flightId,flight)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/flights/{flightId}")
    public ResponseEntity<Void> delete(@PathVariable("flightId") String flightId) {
        if (flightService.delete(flightId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
