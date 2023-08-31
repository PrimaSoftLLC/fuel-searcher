package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelHeaderMetadata;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.exception.FuelOffsetNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.FilterChain;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.FilterChain.FilterChainBuilder;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.InterimFilter;
import lombok.Value;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.extractDoubleValue;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.concat;

public abstract class FuelSearcher {
    private static final int ROW_INDEX_FUEL_HEADERS = 1;

    private final FuelTable fuelTable;
    private final Map<String, Integer> fuelOffsetsByHeaders;
    private final FilterChain filterChain;
    private final SpecificationPropertyExtractor fuelHeaderExtractor;

    public FuelSearcher(final FuelTable fuelTable,
                        final FuelHeaderMetadata fuelHeaderMetadata,
                        final FilterChain filterChain) {
        this.fuelTable = fuelTable;
        this.fuelOffsetsByHeaders = createFuelOffsetsByHeaders(fuelHeaderMetadata);
        this.filterChain = filterChain;
        this.fuelHeaderExtractor = fuelHeaderMetadata.getFuelHeaderExtractor();
    }

    public final String findTableName() {
        return this.fuelTable.getName();
    }

    public final Optional<Fuel> find(final Specification specification) {
        final List<IBodyElement> elements = this.fuelTable.getElements();
        return this.findSubTable(elements, specification)
                .map(XWPFTable::getRows)
                .flatMap(subTableRows -> this.findFuel(subTableRows, specification));
    }

    protected abstract Optional<XWPFTable> findSubTable(final List<IBodyElement> elements,
                                                        final Specification specification);

    private static Map<String, Integer> createFuelOffsetsByHeaders(final FuelHeaderMetadata metadata) {
        final List<String> values = metadata.getValues();
        return range(0, values.size())
                .boxed()
                .collect(toMap(values::get, identity()));
    }

    private Optional<Fuel> findFuel(final List<XWPFTableRow> subTableRows, final Specification specification) {
        final XWPFTableRow headersRow = subTableRows.get(ROW_INDEX_FUEL_HEADERS);
        return this.filterChain.filter(subTableRows, specification)
                .flatMap(fuelRow -> this.findFuelLocation(headersRow, specification, fuelRow))
                .map(FuelSearcher::extractFuel);
    }

    private Optional<FuelLocation> findFuelLocation(final XWPFTableRow headersRow,
                                                    final Specification specification,
                                                    final XWPFTableRow fuelRow) {
        final String fuelHeader = this.fuelHeaderExtractor.apply(specification);
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
        return extractFuelComponent(location, FuelLocation::getCellIndexGenerationNorm);
    }

    private static double extractConsumption(final FuelLocation location) {
        return extractFuelComponent(location, FuelLocation::getCellIndexConsumption);
    }

    private static double extractFuelComponent(final FuelLocation location,
                                               final ToIntFunction<FuelLocation> cellIndexGetter) {
        final XWPFTableRow row = location.getRow();
        final int cellIndex = cellIndexGetter.applyAsInt(location);
        return extractDoubleValue(row, cellIndex);
    }

    @Value
    private static class FuelLocation {
        XWPFTableRow row;
        int cellIndexGenerationNorm;
        int cellIndexConsumption;
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
