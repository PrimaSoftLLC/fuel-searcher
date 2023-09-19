package by.aurorasoft.fuelsearcher.crud.service;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class TableMetadataServiceTest extends AbstractContextTest {

    @Autowired
    private TableMetadataService service;

    @Test
    public void metadataShouldBeFoundByTableName() {
        final String givenTableName = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

        final Optional<TableMetadata> optionalActual = this.service.findByTableName(givenTableName);
        assertTrue(optionalActual.isPresent());
        final TableMetadata actual = optionalActual.get();
        final Long expectedId = 255L;
        assertEquals(expectedId, actual.getId());
    }

    @Test
    public void metadataShouldNotBeFoundByTableName() {
        final String givenTableName = "НЕСУЩЕСТВУЮЩАЯ ТАБЛИЦА";

        final Optional<TableMetadata> optionalActual = this.service.findByTableName(givenTableName);
        assertTrue(optionalActual.isEmpty());
    }
}
