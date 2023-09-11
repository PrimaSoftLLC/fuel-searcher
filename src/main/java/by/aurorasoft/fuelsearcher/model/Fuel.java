package by.aurorasoft.fuelsearcher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isNotDefinedDouble;

@Value
public class Fuel {
    double generationNorm;
    double consumption;

    @JsonIgnore
    public boolean isDefinedFuel() {
        return !isNotDefinedDouble(this.generationNorm) || !isNotDefinedDouble(this.consumption);
    }
}
