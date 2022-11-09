package org.iesfm.airline.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @NotBlank
    private String id;
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

}
