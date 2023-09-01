package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import by.aurorasoft.fuelsearcher.service.searcher.filterchain.FilterChain;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class SimpleFuelSearcherTest {

    @Test
    public void subTableShouldBeFound()
            throws Exception {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final XWPFTable element = mock(XWPFTable.class);
        final List<IBodyElement> givenElements = singletonList(element);
        final Specification givenSpecification = mock(Specification.class);

        final Optional<XWPFTable> optionalActual = givenSearcher.findSubTable(givenElements, givenSpecification);
        assertTrue(optionalActual.isPresent());
        final XWPFTable actual = optionalActual.get();
        assertSame(element, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subTableShouldNotBeFoundBecauseOfElementsIsEmpty()
            throws Exception {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final List<IBodyElement> givenElements = emptyList();
        final Specification givenSpecification = mock(Specification.class);

        givenSearcher.findSubTable(givenElements, givenSpecification);
    }

    @Test(expected = ClassCastException.class)
    public void subTableShouldNotBeFoundBecauseOfFirstElementIsNotTable()
            throws Exception {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final List<IBodyElement> givenElements = List.of(mock(IBodyElement.class));
        final Specification givenSpecification = mock(Specification.class);

        givenSearcher.findSubTable(givenElements, givenSpecification);
    }

    @Test
    public void builderShouldBeCreated() {
        final SimpleSearcherBuilder actual = SimpleFuelSearcher.builder();
        assertNotNull(actual);
    }

    @Test
    public void elementsShouldBeValid() {
        final SimpleSearcherBuilder givenBuilder = SimpleFuelSearcher.builder();
        final List<IBodyElement> givenElements = List.of(mock(XWPFTable.class));

        final boolean actual = givenBuilder.isValidElements(givenElements);
        assertTrue(actual);
    }

    @Test
    public void elementsShouldNotBeValidBecauseOfNotValidSize() {
        final SimpleSearcherBuilder givenBuilder = SimpleFuelSearcher.builder();
        final List<IBodyElement> givenElements = List.of(mock(XWPFTable.class), mock(IBodyElement.class));

        final boolean actual = givenBuilder.isValidElements(givenElements);
        assertFalse(actual);
    }

    @Test
    public void elementsShouldNotBeValidBecauseOfFirstElementIsNotTable() {
        final SimpleSearcherBuilder givenBuilder = SimpleFuelSearcher.builder();
        final List<IBodyElement> givenElements = List.of(mock(IBodyElement.class));

        final boolean actual = givenBuilder.isValidElements(givenElements);
        assertFalse(actual);
    }

    @Test
    public void notValidElementsMessageShouldBeFound() {
        final SimpleSearcherBuilder givenBuilder = SimpleFuelSearcher.builder();

        final String actual = givenBuilder.findNotValidElementsMessage();
        final String expected = "Fuel table should contain only one table-element";
        assertEquals(expected, actual);
    }

    @Test
    public void searcherShouldBeBuilt() {
        final SimpleSearcherBuilder givenBuilder = SimpleFuelSearcher.builder();
        final FuelTable givenFuelTable = mock(FuelTable.class);
        final FuelHeaderMetadata givenFuelHeaderMetadata = createDefaultFuelHeaderMetaData();
        final FilterChain givenFilterChain = mock(FilterChain.class);

        final SimpleFuelSearcher actual = givenBuilder.build(givenFuelTable, givenFuelHeaderMetadata, givenFilterChain);
        assertNotNull(actual);
    }

    @Test
    public void builderAdditionalPropertiesShouldBeFound() {
        final SimpleSearcherBuilder givenBuilder = SimpleFuelSearcher.builder();

        final Stream<Object> actual = givenBuilder.findAdditionalProperties();
        final boolean actualEmpty = actual.findFirst().isEmpty();
        assertTrue(actualEmpty);
    }

    private static SimpleFuelSearcher createSearcher()
            throws Exception {
        final Constructor<SimpleFuelSearcher> constructor = SimpleFuelSearcher.class.getDeclaredConstructor(
                FuelTable.class, FuelHeaderMetadata.class, FilterChain.class
        );
        constructor.setAccessible(true);
        try {
            final FuelHeaderMetadata fuelHeaderMetadata = createDefaultFuelHeaderMetaData();
            return constructor.newInstance(null, fuelHeaderMetadata, null);
        } finally {
            constructor.setAccessible(false);
        }
    }

    private static FuelHeaderMetadata createDefaultFuelHeaderMetaData() {
        final FuelHeaderMetadata mockedMetadata = mock(FuelHeaderMetadata.class);
        when(mockedMetadata.getValues()).thenReturn(new String[]{});
        return mockedMetadata;
    }
}
