package org.iesfm.airline.services.exceptions;

public class FlightNotFoundException extends Exception{

    private final String flighId;

    public FlightNotFoundException(String flighId) {
        super();
        this.flighId = flighId;
    }

    public String getFlighId() {
        return flighId;
    }
}
