package org.iesfm.airline.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesfm.airline.entity.Passenger;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    @NotBlank
    private String nif;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    private String email;
    @NotNull
    @Positive
    private int seatNumber;

    public static PassengerDto toDto(Passenger entity) {
        return new PassengerDto(
                entity.getNif(),
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getSeatNumber()
        );
    }
    public static Passenger toEntity(
            PassengerDto dto,
            String flightId
    ) {
        return new Passenger(
                dto.getNif(),
                flightId,
                dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getSeatNumber()
        );
    }
}