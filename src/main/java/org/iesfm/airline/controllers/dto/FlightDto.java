package org.iesfm.airline.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesfm.airline.entity.Flight;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    @NotBlank
    private String id;
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    public static Flight toEntity(FlightDto dto) {
        return new Flight(
                dto.getId(),
                dto.getOrigin(),
                dto.getDestination(),
                dto.getDate()
        );
    }

    public static FlightDto toDto(Flight entity) {
        return new FlightDto(
                entity.getId(),
                entity.getOrigin(),
                entity.getDestination(),
                entity.getDate()
        );
    }
}