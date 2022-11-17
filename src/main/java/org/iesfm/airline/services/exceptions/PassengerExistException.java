package org.iesfm.airline.services.exceptions;

public class PassengerExistException extends Exception {
    private final String flighId;
    private final String nif;

    public PassengerExistException(String flighId, String nif) {
        this.flighId = flighId;
        this.nif = nif;
    }

    public String getFlighId() {
        return flighId;
    }

    public String getNif() {
        return nif;
    }
}