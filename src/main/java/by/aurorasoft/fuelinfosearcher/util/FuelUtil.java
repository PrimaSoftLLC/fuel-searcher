package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelLocation;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.ToIntFunction;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.extractDoubleValue;

@UtilityClass
public final class FuelUtil {

    public static Optional<Fuel> extractFuel(final FuelLocation location) {
        final OptionalDouble optionalGenerationNorm = extractGenerationNorm(location);
        final OptionalDouble optionalConsumption = extractConsumption(location);
        if (optionalGenerationNorm.isEmpty() || optionalConsumption.isEmpty()) {
            return Optional.empty();
        }
        final double generationNorm = optionalGenerationNorm.getAsDouble();
        final double consumption = optionalConsumption.getAsDouble();
        return Optional.of(new Fuel(generationNorm, consumption));
    }

    private static OptionalDouble extractGenerationNorm(final FuelLocation location) {
        return extractFuelInfoComponent(location, FuelLocation::getCellIndexGenerationNorm);
    }

    private static OptionalDouble extractConsumption(final FuelLocation location) {
        return extractFuelInfoComponent(location, FuelLocation::getCellIndexConsumption);
    }

    private static OptionalDouble extractFuelInfoComponent(final FuelLocation location,
                                                           final ToIntFunction<FuelLocation> cellIndexGetter) {
        final XWPFTableRow row = location.getRow();
        final int cellIndex = cellIndexGetter.applyAsInt(location);
        return extractDoubleValue(row, cellIndex);
    }

}
