package org.iesfm.airline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private String id;
    private String origin;
    private String destination;
    private String date;

}
