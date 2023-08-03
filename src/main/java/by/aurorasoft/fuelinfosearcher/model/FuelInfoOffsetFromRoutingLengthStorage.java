package by.aurorasoft.fuelinfosearcher.model;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public final class FuelInfoOffsetFromRoutingLengthStorage {
    private final Map<String, Map<String, Integer>> offsetsByRoutingLengthsByTableNames;

    public int findOffset(final String tableName, final String routingLength) {
        return this.offsetsByRoutingLengthsByTableNames
                .get(tableName)
                .get(routingLength);
    }
}
