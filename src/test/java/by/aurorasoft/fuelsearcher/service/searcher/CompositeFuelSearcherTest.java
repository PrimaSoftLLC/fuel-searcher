package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.service.searcher.CompositeFuelSearcher.CompositeSearcherBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public final class CompositeFuelSearcherTest {

    @Test
    public void builderShouldBeCreated() {
        final CompositeSearcherBuilder actual = CompositeFuelSearcher.builder();
        assertNotNull(actual);
    }

    @Test
    public void subTableShouldBeFound() {
        throw new RuntimeException();
    }

}
