package by.aurorasoft.fuelsearcher.model;

import lombok.Value;

import static java.lang.Double.isNaN;

@Value
public class Fuel {
    double generationNorm;
    double consumption;

    public boolean isNotDefinedFuel() {
        return isNaN(this.generationNorm) && isNaN(this.consumption);
    }
}
