package com.aurorasoft.fuelsearcher.service.searcher;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.service.searcher.SimpleFuelSearcher.SimpleSearcherBuilder;
import com.aurorasoft.fuelsearcher.testutil.ReflectionUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public final class SimpleFuelSearcherTest {
    private static final String FIELD_NAME_TABLE = "table";
    private static final String FIELD_NAME_HEADER_METADATA = "headerMetadata";
    private static final String FIELD_NAME_FILTER_CHAIN = "filterChain";

    @Test
    public void builderShouldBeCreated() {
        final SimpleSearcherBuilder actual = SimpleFuelSearcher.builder();
        assertNotNull(actual);
    }

    @Test
    public void subTableShouldBeFound() {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final XWPFTable element = mock(XWPFTable.class);
        final List<IBodyElement> givenElements = singletonList(element);
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final Optional<XWPFTable> optionalActual = givenSearcher.findSubTable(givenElements, givenSpecification);
        assertTrue(optionalActual.isPresent());
        final XWPFTable actual = optionalActual.get();
        assertSame(element, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subTableShouldNotBeFoundBecauseOfElementsIsEmpty() {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final List<IBodyElement> givenElements = emptyList();
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        givenSearcher.findSubTable(givenElements, givenSpecification);
    }

    @Test(expected = ClassCastException.class)
    public void subTableShouldNotBeFoundBecauseOfFirstElementIsNotTable() {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final List<IBodyElement> givenElements = List.of(mock(IBodyElement.class));
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        givenSearcher.findSubTable(givenElements, givenSpecification);
    }

    @Test
    public void additionalPropertyMetadataSourcesShouldBeFoundShouldBeFound() {
        final SimpleFuelSearcher givenSearcher = createSearcher();

        final Stream<? extends PropertyMetadataSource> actual = givenSearcher.findAdditionalPropertyMetadataSources();
        final boolean actualEmpty = actual.findFirst().isEmpty();
        assertTrue(actualEmpty);
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
        final FuelHeaderMetadata givenHeaderMetadata = mock(FuelHeaderMetadata.class);
        final FilterChain givenFilterChain = Mockito.mock(FilterChain.class);

        final SimpleFuelSearcher actual = givenBuilder.build(
                givenFuelTable,
                givenHeaderMetadata,
                givenFilterChain
        );

        final FuelTable actualTable = findTable(actual);
        assertSame(givenFuelTable, actualTable);

        final FuelHeaderMetadata actualHeaderMetadata = findHeaderMetadata(actual);
        assertSame(givenHeaderMetadata, actualHeaderMetadata);

        final FilterChain actualFilterChain = findFilterChain(actual);
        assertSame(givenFilterChain, actualFilterChain);
    }

    @Test
    public void builderAdditionalPropertiesShouldBeFound() {
        final SimpleSearcherBuilder givenBuilder = SimpleFuelSearcher.builder();

        final Stream<Object> actual = givenBuilder.findAdditionalProperties();
        final boolean actualEmpty = actual.findFirst().isEmpty();
        assertTrue(actualEmpty);
    }

    private static SimpleFuelSearcher createSearcher() {
        return ReflectionUtil.createObject(
                SimpleFuelSearcher.class,
                new Class<?>[]{FuelTable.class, FuelHeaderMetadata.class, FilterChain.class},
                new Object[]{null, null, null}
        );
    }

    private static FuelTable findTable(final FuelSearcher searcher) {
        return ReflectionUtil.findProperty(
                searcher,
                FIELD_NAME_TABLE,
                FuelTable.class
        );
    }

    private static FuelHeaderMetadata findHeaderMetadata(final FuelSearcher searcher) {
        return ReflectionUtil.findProperty(
                searcher,
                FIELD_NAME_HEADER_METADATA,
                FuelHeaderMetadata.class
        );
    }

    private static FilterChain findFilterChain(final FuelSearcher searcher) {
        return ReflectionUtil.findProperty(
                searcher,
                FIELD_NAME_FILTER_CHAIN,
                FilterChain.class
        );
    }
}
