package org.iesfm.airline.controllers;

import org.iesfm.airline.controllers.dto.PassengerDto;
import org.iesfm.airline.entity.Passenger;
import org.iesfm.airline.services.FlightService;
import org.iesfm.airline.services.exceptions.FlightNotFoundException;
import org.iesfm.airline.services.exceptions.PassengerExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PassengerController {
    @Autowired
    private FlightService flightService;

    @GetMapping(path = "/flights/{flightId}/passengers")
    public ResponseEntity<List<PassengerDto>> listFlightPassengers(
            @PathVariable("flightId") String flightId
    ) {

        try {
            return ResponseEntity.ok(
                    flightService
                            .listFlightPassengers(flightId)
                            .stream()
                            .map(PassengerDto::toDto)
                            .collect(Collectors.toList())
            );
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/flights/{flightId}/passengers")
    public ResponseEntity<Void> addPassenger(
            @PathVariable("flightId") String flightId,
            @Valid @RequestBody PassengerDto passenger
    ) {
        try {
            flightService.addPassenger(PassengerDto.toEntity(passenger, flightId));
            return ResponseEntity.ok().build();
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (PassengerExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
