package org.iesfm.airline.controllers;

import org.iesfm.airline.controllers.dto.FlightDto;
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
    public ResponseEntity<List<FlightDto>> list(
            @RequestParam(value = "origin", required = false) String  origin,
            @RequestParam(value = "destination", required = false) String  destination
    ) {
//        List<Flight> flights = flightService.listFlights();
//        List<FlightDto> flightsDto = new LinkedList<>();
//        for(Flight f: flights) {
//            flightsDto.add(FlightDto.toDto(f));
//        }
//        return ResponseEntity.ok(flightsDto);
        return ResponseEntity.ok(
                flightService
                        .listFlights(origin, destination)
                        .stream() // RUIDO
                        .map(flight -> FlightDto.toDto(flight))
                        //.map(FlightDto::toDto)
                        .collect(Collectors.toList()) // RUIDO
        );
    }

    @GetMapping(path = "/flights/{flightId}")
    public ResponseEntity<FlightDto> getFlight(
            @PathVariable("flightId") String flightId
    ) {
        Flight flight = flightService.getFlight(flightId);
        if (flight != null) {
            return ResponseEntity.ok(FlightDto.toDto(flight));
        } else {
            //  ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping(path = "/flights")
    public ResponseEntity<Void> add(
            @Valid @RequestBody FlightDto flight
    ) {
        if (flightService.add(FlightDto.toEntity(flight))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/flights/{flightId}")
    public ResponseEntity<Void> updateFlight(
            @PathVariable("flightId") String flightId,
            @Valid @RequestBody FlightDto flight
    ) {
        // 3 -> Madrid - Londres
        // 4 -> 1 Madrid - Berlin
        if (flightService.updateFlight(flightId, FlightDto.toEntity(flight))) {
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