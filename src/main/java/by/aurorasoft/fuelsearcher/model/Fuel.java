package by.aurorasoft.fuelsearcher.model;

import lombok.Value;

import java.util.OptionalDouble;
import java.util.function.ToDoubleFunction;

import static by.aurorasoft.fuelsearcher.util.XWPFUtil.NOT_DEFINED_DOUBLE;
import static by.aurorasoft.fuelsearcher.util.XWPFUtil.isNotDefinedDouble;
import static java.util.OptionalDouble.empty;

@Value
public class Fuel {
    double generationNorm;
    double consumption;

    public OptionalDouble findGenerationNorm() {
        return this.findComponent(Fuel::getGenerationNorm);
    }

    public OptionalDouble findConsumption() {
        return this.findComponent(Fuel::getConsumption);
    }

    public static Fuel createNotDefinedFuel() {
        return new Fuel(NOT_DEFINED_DOUBLE, NOT_DEFINED_DOUBLE);
    }

    private OptionalDouble findComponent(final ToDoubleFunction<Fuel> componentGetter) {
        final double component = componentGetter.applyAsDouble(this);
        return isNotDefinedDouble(component) ? OptionalDouble.of(component) : empty();
    }
}
