package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searcher.exception.FuelOffsetNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.FilterChain;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.FilterChain.FilterChainBuilder;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.InterimFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.concat;

//TODO: put FuelInfoLocation as inner class
public abstract class FuelSearcher {
    private static final int ROW_INDEX_FUEL_HEADER_VALUES = 1;

    private final FuelTable fuelTable;
    private final Map<String, Integer> fuelOffsetsByHeaderValues;
    private final FilterChain filterChain;
    private final SpecificationPropertyExtractor fuelHeaderValueExtractor;

    public FuelSearcher(final FuelTable fuelTable,
                        final FuelHeaderMetadata fuelHeaderMetadata,
                        final FilterChain filterChain) {
        this.fuelTable = fuelTable;
        this.fuelOffsetsByHeaderValues = createFuelOffsetsByHeaderValues(fuelHeaderMetadata);
        this.filterChain = filterChain;
        this.fuelHeaderValueExtractor = fuelHeaderMetadata.getFuelHeaderValueExtractor();
    }

    public final String findTableName() {
        return this.fuelTable.getName();
    }

    public final Optional<Fuel> find(final Specification specification) {
        return this.findSubTable(this.fuelTable, specification)
                .map(XWPFTable::getRows)
                .flatMap(subTableRows -> this.findFuel(subTableRows, specification));
    }

    protected abstract Optional<XWPFTable> findSubTable(final FuelTable fuelTable, final Specification specification);

    private static Map<String, Integer> createFuelOffsetsByHeaderValues(final FuelHeaderMetadata metadata) {
        final List<String> values = metadata.getValues();
        return range(0, values.size())
                .boxed()
                .collect(toMap(values::get, identity()));
    }

    private Optional<Fuel> findFuel(final List<XWPFTableRow> subTableRows, final Specification specification) {
        final XWPFTableRow headerValuesRow = subTableRows.get(ROW_INDEX_FUEL_HEADER_VALUES);
        return this.filterChain.filter(subTableRows, specification)
                .flatMap(row -> this.findFuelLocation(headerValuesRow, specification, row))
                .flatMap(FuelUtil::extractFuel);
    }

    private Optional<FuelLocation> findFuelLocation(final XWPFTableRow headerValuesRow,
                                                    final Specification specification,
                                                    final XWPFTableRow dataRow) {
        final String fuelHeaderValue = this.fuelHeaderValueExtractor.apply(specification);
        return findIndexFirstCellByContent(headerValuesRow, fuelHeaderValue)
                .stream()
                .map(fuelHeaderCellIndex -> this.findCellIndexGenerationNorm(fuelHeaderCellIndex, fuelHeaderValue))
                .mapToObj(generationNormCellIndex -> createFuelLocation(dataRow, generationNormCellIndex))
                .findFirst();
    }

    private int findCellIndexGenerationNorm(final int fuelHeaderCellIndex, final String fuelHeaderCellValue) {
        final int fuelOffset = this.findFuelOffset(fuelHeaderCellValue);
        return fuelHeaderCellIndex + fuelOffset;
    }

    private int findFuelOffset(final String fuelHeaderCellValue) {
        return this.fuelOffsetsByHeaderValues.computeIfAbsent(
                fuelHeaderCellValue,
                FuelSearcher::throwFuelOffsetNotExistException
        );
    }

    private static Integer throwFuelOffsetNotExistException(final String fuelHeaderValue) {
        throw new FuelOffsetNotExistException(
                "Fuel's offset for header's value '%s' doesn't exist".formatted(fuelHeaderValue)
        );
    }

    private static FuelLocation createFuelLocation(final XWPFTableRow dataRow, final int generationNormCellIndex) {
        final int consumptionCellIndex = generationNormCellIndex + 1;
        return new FuelLocation(dataRow, generationNormCellIndex, consumptionCellIndex);
    }

    public static abstract class FuelSearcherBuilder<S extends FuelSearcher> extends BuilderRequiringAllProperties<S> {
        private FuelTable fuelTable;
        private FuelHeaderMetadata fuelHeaderMetadata;
        private FilterChainBuilder filterChainBuilder;

        public final void fuelTable(final FuelTable fuelTable) {
            this.fuelTable = fuelTable;
        }

        public final void fuelHeaderMetadata(final FuelHeaderMetadata metadata) {
            this.fuelHeaderMetadata = metadata;
        }

        public final void intermediateFilter(final InterimFilter filter) {
            this.createFilterChainBuilderIfNecessary();
            this.filterChainBuilder.interimFilter(filter);
        }

        public final void conclusiveFilter(final FinalFilter filter) {
            this.createFilterChainBuilderIfNecessary();
            this.filterChainBuilder.finalFilter(filter);
        }

        @Override
        protected final Stream<Object> findProperties() {
            final Stream<Object> currentProperties = Stream.of(
                    this.fuelTable, this.fuelHeaderMetadata, this.filterChainBuilder
            );
            final Stream<Object> additionalProperties = this.findAdditionalProperties();
            return concat(currentProperties, additionalProperties);
        }

        @Override
        protected final S buildAfterStateValidation() {
            final FilterChain filterChain = this.filterChainBuilder.build();
            return this.build(this.fuelTable, this.fuelHeaderMetadata, filterChain);
        }

        protected abstract S build(final FuelTable fuelTable,
                                   final FuelHeaderMetadata fuelHeaderMetadata,
                                   final FilterChain filterChain);

        protected abstract Stream<Object> findAdditionalProperties();

        private void createFilterChainBuilderIfNecessary() {
            if (this.filterChainBuilder == null) {
                this.filterChainBuilder = FilterChain.builder();
            }
        }
    }
}
