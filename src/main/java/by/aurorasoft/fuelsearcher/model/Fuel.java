package by.aurorasoft.fuelsearcher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isNotDefinedDouble;

public record Fuel(double generationNorm, double consumption) {
    @JsonIgnore
    public boolean isDefinedFuel() {
        return !isNotDefinedDouble(this.generationNorm) || !isNotDefinedDouble(this.consumption);
    }
}
