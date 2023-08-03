package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoLocation;
import lombok.experimental.UtilityClass;

import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.extractDoubleValue;

//TODO: refactor
@UtilityClass
public final class FuelInfoUtil {

    public static Optional<FuelInfo> extractFuelInfo(final FuelInfoLocation location) {
        //TODO: to other method
        final double generationNorm = extractDoubleValue(location.getRow(), location.getCellIndexGenerationNorm());
        //TODO: to other method
        final double consumption = extractDoubleValue(location.getRow(), location.getCellIndexConsumption());
        return Optional.of(new FuelInfo(generationNorm, consumption));
    }

}
