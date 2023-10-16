package com.aurorasoft.fuelsearcher.model.metadata;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public final class TableMetadataTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenTableName = "table";
        final TableMetadata givenMetadata = TableMetadata.builder()
                .tableName(givenTableName)
                .build();

        final String actual = givenMetadata.findAlias();
        assertSame(givenTableName, actual);
    }

}
