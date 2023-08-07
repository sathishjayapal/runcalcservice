package me.sathish.runcalcservice.cityofsp.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityOfSPRunDTO {
    private Date date = new Date();
    private String id;
    private String text;
}
