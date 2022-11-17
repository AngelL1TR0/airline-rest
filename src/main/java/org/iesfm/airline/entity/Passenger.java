package org.iesfm.airline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Passenger {

    private String nif;
    private String flightId;
    private String name;
    private String surname;
    private String email;
    private int seatNumber;
}
