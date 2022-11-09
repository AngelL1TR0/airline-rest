package org.iesfm.airline.controllers;

import org.iesfm.airline.entity.Passenger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengerControler {

    private Map<String, Passenger> passengers = new HashMap<>();

    @GetMapping(path = "/flights/{flightId}/passengers")
    private ResponseEntity <List<Passenger>> listFlightPassengers(
            @PathVariable("flightId") String flightId
    )
}
