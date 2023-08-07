package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoLocation;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.ToIntFunction;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.extractDoubleValue;

@UtilityClass
public final class FuelInfoUtil {

    public static Optional<FuelInfo> extractFuelInfo(final FuelInfoLocation location) {
        final OptionalDouble optionalGenerationNorm = extractGenerationNorm(location);
        final OptionalDouble optionalConsumption = extractConsumption(location);
        if (optionalGenerationNorm.isEmpty() || optionalConsumption.isEmpty()) {
            return Optional.empty();
        }
        final double generationNorm = optionalGenerationNorm.getAsDouble();
        final double consumption = optionalConsumption.getAsDouble();
        return Optional.of(new FuelInfo(generationNorm, consumption));
    }

    private static OptionalDouble extractGenerationNorm(final FuelInfoLocation location) {
        return extractFuelInfoComponent(location, FuelInfoLocation::getCellIndexGenerationNorm);
    }

    private static OptionalDouble extractConsumption(final FuelInfoLocation location) {
        return extractFuelInfoComponent(location, FuelInfoLocation::getCellIndexConsumption);
    }

    private static OptionalDouble extractFuelInfoComponent(final FuelInfoLocation location,
                                                           final ToIntFunction<FuelInfoLocation> cellIndexGetter) {
        final XWPFTableRow row = location.getRow();
        final int cellIndex = cellIndexGetter.applyAsInt(location);
        return extractDoubleValue(row, cellIndex);
    }

}
