package me.sathish.runcalcservice.cityofsp.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityOfSPRun {

    @Id private String id;
    private String text;
    private Date date = new Date();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityOfSPRun that = (CityOfSPRun) o;

        if (!id.equals(that.id)) return false;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
