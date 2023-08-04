package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoLocation;
import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.OptionalDouble;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.extractDoubleValue;

//TODO: refactor
@UtilityClass
public final class FuelInfoUtil {

    public static Optional<FuelInfo> extractFuelInfo(final FuelInfoLocation location) {
        final OptionalDouble optionalGenerationNorm = extractDoubleValue(location.getRow(), location.getCellIndexGenerationNorm());
        final OptionalDouble optionalConsumption = extractDoubleValue(location.getRow(), location.getCellIndexConsumption());
        if (optionalGenerationNorm.isEmpty() || optionalConsumption.isEmpty()) {
            return Optional.empty();
        }

        //TODO: to other method
        final double generationNorm = optionalGenerationNorm.getAsDouble();
        //TODO: to other method
        final double consumption = optionalConsumption.getAsDouble();
        return Optional.of(new FuelInfo(generationNorm, consumption));
    }

}
