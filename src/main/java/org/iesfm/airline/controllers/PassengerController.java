package org.iesfm.airline.controllers;

import org.iesfm.airline.entity.Passenger;
import org.iesfm.airline.services.FlightService;
import org.iesfm.airline.services.exceptions.FlightNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PassengerController {
    // La clave es la ${flightId}-${nif}
    // passenger{nif=1, flightId=3} -> 3-1
    @Autowired
    private FlightService flightService;

    @GetMapping(path = "/flights/{flightId}/passengers")
    public ResponseEntity<List<Passenger>> listFlightPassengers(
            @PathVariable("flightId") String flightId
    ) {
        try {
            return ResponseEntity.ok(flightService.listFlightPassengers(flightId));
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

