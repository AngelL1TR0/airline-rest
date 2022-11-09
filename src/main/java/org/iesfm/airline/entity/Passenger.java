package org.iesfm.airline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Passenger {

    @NotBlank
    private String nif;
    @NotBlank
    private String flightId;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    private String email;
    @NotNull
    @Positive
    private int seatNumber;
}
