package by.aurorasoft.fuelinfosearcher.service.searching.sowing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import org.springframework.stereotype.Service;

@Service
public final class SowingCornAndSubFlowerFuelInfoSearchingService extends AbstractSowingFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА";

    public SowingCornAndSubFlowerFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                          final FuelDocument fuelDocument) {
        super(offsetStorage, fuelDocument, TABLE_NAME);
    }
}
