package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searcher.FilterChain.FilterChainBuilder;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.extractCellDoubleValue;
import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.concat;

public abstract class FuelSearcher implements Translatable {
    private static final int ROW_INDEX_HEADERS = 1;

    private final FuelTable table;
    private final Map<String, Integer> fuelOffsetsByHeaders;
    private final FilterChain filterChain;
    private final SpecificationPropertyExtractor headerExtractor;

    public FuelSearcher(final FuelTable table,
                        final Map<String, Integer> fuelOffsetsByHeaders,
                        final FilterChain filterChain,
                        final SpecificationPropertyExtractor headerExtractor) {
        this.table = table;
        this.fuelOffsetsByHeaders = fuelOffsetsByHeaders;
        this.filterChain = filterChain;
        this.headerExtractor = headerExtractor;
    }

    @Override
    public final String findAlias() {
        return this.table.getName();
    }

    public final Optional<Fuel> find(final FuelSpecification specification) {
        final List<IBodyElement> elements = this.table.getElements();
        return this.findSubTable(elements, specification)
                .map(XWPFTable::getRows)
                .flatMap(subTableRows -> this.findFuel(subTableRows, specification))
                .filter(Fuel::isDefinedFuel);
    }

    protected abstract Optional<XWPFTable> findSubTable(final List<IBodyElement> elements,
                                                        final FuelSpecification specification);

    private Optional<Fuel> findFuel(final List<XWPFTableRow> subTableRows, final FuelSpecification specification) {
        final XWPFTableRow headersRow = subTableRows.get(ROW_INDEX_HEADERS);
        return this.filterChain.filter(subTableRows, specification)
                .flatMap(fuelRow -> this.findFuelLocation(headersRow, specification, fuelRow))
                .map(FuelSearcher::extractFuel);
    }

    private Optional<FuelLocation> findFuelLocation(final XWPFTableRow headersRow,
                                                    final FuelSpecification specification,
                                                    final XWPFTableRow fuelRow) {
        final String fuelHeader = this.headerExtractor.extract(specification);
        return findIndexFirstCellByContent(headersRow, fuelHeader)
                .stream()
                .map(fuelHeaderCellIndex -> this.findCellIndexGenerationNorm(fuelHeaderCellIndex, fuelHeader))
                .mapToObj(generationNormCellIndex -> createFuelLocation(fuelRow, generationNormCellIndex))
                .findFirst();
    }

    private int findCellIndexGenerationNorm(final int fuelHeaderCellIndex, final String fuelHeader) {
        final int fuelOffset = this.findFuelOffset(fuelHeader);
        return fuelHeaderCellIndex + fuelOffset;
    }

    private int findFuelOffset(final String fuelHeader) {
        return this.fuelOffsetsByHeaders.computeIfAbsent(
                fuelHeader,
                FuelSearcher::throwFuelOffsetNotExistException
        );
    }

    private static Integer throwFuelOffsetNotExistException(final String fuelHeader) {
        throw new FuelOffsetNotExistException(
                "Fuel's offset for header's value '%s' doesn't exist".formatted(fuelHeader)
        );
    }

    private static FuelLocation createFuelLocation(final XWPFTableRow fuelRow, final int generationNormCellIndex) {
        final int consumptionCellIndex = generationNormCellIndex + 1;
        return new FuelLocation(fuelRow, generationNormCellIndex, consumptionCellIndex);
    }

    private static Fuel extractFuel(final FuelLocation location) {
        final double generationNorm = extractGenerationNorm(location);
        final double consumption = extractConsumption(location);
        return new Fuel(generationNorm, consumption);
    }

    private static double extractGenerationNorm(final FuelLocation location) {
        return extractFuelComponent(location, FuelLocation::cellIndexGenerationNorm);
    }

    private static double extractConsumption(final FuelLocation location) {
        return extractFuelComponent(location, FuelLocation::cellIndexConsumption);
    }

    private static double extractFuelComponent(final FuelLocation location,
                                               final ToIntFunction<FuelLocation> cellIndexGetter) {
        final XWPFTableRow row = location.row();
        final int cellIndex = cellIndexGetter.applyAsInt(location);
        return extractCellDoubleValue(row, cellIndex);
    }


    private record FuelLocation(XWPFTableRow row, int cellIndexGenerationNorm, int cellIndexConsumption) {
    }

    public static abstract class SearcherBuilder<S extends FuelSearcher> extends BuilderRequiringAllProperties<S> {
        private FuelTable table;
        private FuelHeaderMetadata headerMetadata;
        private FilterChainBuilder filterChainBuilder;

        public final void table(final FuelTable fuelTable) {
            this.table = fuelTable;
        }

        public final void headerMetadata(final FuelHeaderMetadata metadata) {
            this.headerMetadata = metadata;
        }

        public final void interimFilter(final InterimFilter filter) {
            this.createFilterChainBuilderIfNecessary();
            this.filterChainBuilder.interimFilter(filter);
        }

        public final void finalFilter(final FinalFilter filter) {
            this.createFilterChainBuilderIfNecessary();
            this.filterChainBuilder.finalFilter(filter);
        }

        @Override
        protected final Stream<Object> findProperties() {
            final Stream<Object> currentProperties = Stream.of(
                    this.table, this.headerMetadata, this.filterChainBuilder
            );
            final Stream<Object> additionalProperties = this.findAdditionalProperties();
            return concat(currentProperties, additionalProperties);
        }

        @Override
        protected final S buildAfterStateValidation() {
            this.validateFuelTable(this.table);
            final Map<String, Integer> fuelOffsetsByHeaders = createFuelOffsetsByHeaders(this.headerMetadata);
            final FilterChain filterChain = this.filterChainBuilder.build();
            final SpecificationPropertyExtractor headerExtractor = this.headerMetadata.getHeaderExtractor();
            return this.build(this.table, fuelOffsetsByHeaders, filterChain, headerExtractor);
        }

        protected abstract boolean isValidElements(final List<IBodyElement> elements);

        protected abstract String findNotValidElementsMessage();

        protected abstract S build(final FuelTable fuelTable,
                                   final Map<String, Integer> fuelOffsetsByHeaders,
                                   final FilterChain filterChain,
                                   final SpecificationPropertyExtractor headerExtractor);

        protected abstract Stream<Object> findAdditionalProperties();

        private void createFilterChainBuilderIfNecessary() {
            if (this.filterChainBuilder == null) {
                this.filterChainBuilder = FilterChain.builder();
            }
        }

        private void validateFuelTable(final FuelTable fuelTable) {
            final List<IBodyElement> elements = fuelTable.getElements();
            if (!this.isValidElements(elements)) {
                final String exceptionDescription = this.findNotValidElementsMessage();
                throw new IllegalArgumentException(exceptionDescription);
            }
        }

        private static Map<String, Integer> createFuelOffsetsByHeaders(final FuelHeaderMetadata metadata) {
            final String[] values = metadata.getValues();
            return range(0, values.length)
                    .boxed()
                    .collect(
                            toMap(
                                    i -> values[i],
                                    identity()
                            )
                    );
        }
    }

    private static final class FuelOffsetNotExistException extends RuntimeException {

        @SuppressWarnings("unused")
        public FuelOffsetNotExistException() {

        }

        public FuelOffsetNotExistException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public FuelOffsetNotExistException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FuelOffsetNotExistException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
