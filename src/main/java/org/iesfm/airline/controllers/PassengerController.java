package org.iesfm.airline.controllers;

import org.iesfm.airline.entity.Passenger;
import org.iesfm.airline.services.FlightService;
import org.iesfm.airline.services.exceptions.FlightNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping(path = "/flights/{flightId}/passengers/passengerId")
    public ResponseEntity<Passenger> getPassenger(
            @PathVariable("passengerId") String passengerId
    ){
        Passenger passenger = flightService.listFlightPassengers(passengerId);
        if (passenger != null){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/flights/{flightId}")
    public ResponseEntity<Void> add(@Valid @RequestBody Passenger passenger){
        if (){

        }
    }

}

