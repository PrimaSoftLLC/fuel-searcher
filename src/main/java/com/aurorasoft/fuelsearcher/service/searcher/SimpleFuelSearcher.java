package com.aurorasoft.fuelsearcher.service.searcher;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Stream.empty;
import static lombok.AccessLevel.PRIVATE;

/**
 * For tables with one sub tables
 */
public final class SimpleFuelSearcher extends FuelSearcher {

    private SimpleFuelSearcher(final FuelTable table,
                               final FuelHeaderMetadata headerMetadata,
                               final FilterChain filterChain) {
        super(table, headerMetadata, filterChain);
    }

    public static SimpleSearcherBuilder builder() {
        return new SimpleSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements, final FuelSpecification specification) {
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable subTable = (XWPFTable) firstElement;
        return Optional.of(subTable);
    }

    @Override
    protected Stream<? extends PropertyMetadataSource> findAdditionalPropertyMetadataSources() {
        return empty();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class SimpleSearcherBuilder extends SearcherBuilder<SimpleFuelSearcher> {
        private static final String NOT_VALID_ELEMENTS_MESSAGE = "Fuel table should contain only one table-element";

        @Override
        protected boolean isValidElements(final List<IBodyElement> elements) {
            return isValidSize(elements) && isFirstTable(elements);
        }

        @Override
        protected String findNotValidElementsMessage() {
            return NOT_VALID_ELEMENTS_MESSAGE;
        }

        @Override
        protected SimpleFuelSearcher build(final FuelTable table,
                                           final FuelHeaderMetadata headerMetadata,
                                           final FilterChain filterChain) {
            return new SimpleFuelSearcher(table, headerMetadata, filterChain);
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return empty();
        }

        private static boolean isValidSize(final List<IBodyElement> elements) {
            return elements.size() == 1;
        }

        private static boolean isFirstTable(final List<IBodyElement> elements) {
            final IBodyElement firstElement = elements.get(0);
            return firstElement instanceof XWPFTable;
        }
    }
}
