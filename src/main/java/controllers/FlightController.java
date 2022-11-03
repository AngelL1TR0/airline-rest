package controllers;

import entity.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightController {

    private Map<String, Flight> flights = new HashMap<>();

    @GetMapping(path = "/flights") // sirve para invocar est metodo para cuando un cliente quiera obtener una lista de vuelos
    public List<Flight> list(){
        return flights.values()
                .stream()
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/flights")
    public void add (Flight flight){
        flights.put(flight.getId(), flight);

    }
}
