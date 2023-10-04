package com.aurorasoft.fuelsearcher.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class FuelTableTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenTableName = "table";
        final FuelTable givenTable = FuelTable.builder()
                .name(givenTableName)
                .build();

        final String actual = givenTable.findAlias();
        assertEquals(givenTableName, actual);
    }

}
