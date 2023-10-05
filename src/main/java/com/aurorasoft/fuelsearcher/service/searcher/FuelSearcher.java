package com.aurorasoft.fuelsearcher.service.searcher;

import com.aurorasoft.fuelsearcher.model.Fuel;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import com.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import com.aurorasoft.fuelsearcher.service.filter.conclusive.FinalFilter;
import com.aurorasoft.fuelsearcher.service.filter.interim.InterimFilter;
import com.aurorasoft.fuelsearcher.service.searcher.FilterChain.FilterChainBuilder;
import com.aurorasoft.fuelsearcher.util.XWPFTableRowUtil;
import lombok.Getter;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.StreamUtil.concat;
import static com.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findFirstCellIndexByContent;

@Getter
public abstract class FuelSearcher implements Translatable {
    private static final int HEADER_ROW_INDEX = 1;

    private final FuelTable table;
    private final FuelHeaderMetadata headerMetadata;
    private final FilterChain filterChain;

    public FuelSearcher(final FuelTable table, final FuelHeaderMetadata headerMetadata, final FilterChain filterChain) {
        this.table = table;
        this.headerMetadata = headerMetadata;
        this.filterChain = filterChain;
    }

    @Override
    public final String findAlias() {
        return this.findTableName();
    }

    public final Optional<Fuel> find(final FuelSpecification specification) {
        final List<IBodyElement> elements = this.table.elements();
        return this.findSubTable(elements, specification)
                .map(XWPFTable::getRows)
                .flatMap(subTableRows -> this.findFuel(subTableRows, specification))
                .filter(Fuel::isDefined);
    }

    public final String findTableName() {
        return this.table.name();
    }

    public final Stream<PropertyMetadataSource> findUsedPropertyMetadataSources() {
        return concat(
                this.filterChain.findFilters(),
                Stream.of(this.headerMetadata),
                this.findAdditionalPropertyMetadataSources()
        );
    }

    public final List<SpecificationPropertyExtractor> findUsedPropertyExtractors() {
        return this.findUsedPropertyMetadataSources()
                .map(PropertyMetadataSource::getPropertyExtractor)
                .toList();
    }

    protected abstract Optional<XWPFTable> findSubTable(final List<IBodyElement> elements,
                                                        final FuelSpecification specification);

    protected abstract Stream<? extends PropertyMetadataSource> findAdditionalPropertyMetadataSources();

    private Optional<Fuel> findFuel(final List<XWPFTableRow> subTableRows, final FuelSpecification specification) {
        final XWPFTableRow headerRow = subTableRows.get(HEADER_ROW_INDEX);
        return this.filterChain.filter(subTableRows, specification)
                .flatMap(fuelRow -> this.findFuelLocation(headerRow, specification, fuelRow))
                .map(FuelSearcher::extractFuel);
    }

    private Optional<FuelLocation> findFuelLocation(final XWPFTableRow headerRow,
                                                    final FuelSpecification specification,
                                                    final XWPFTableRow fuelRow) {
        final String fuelHeader = this.findFuelHeader(specification);
        return findFirstCellIndexByContent(headerRow, fuelHeader)
                .stream()
                .map(fuelHeaderCellIndex -> this.findGenerationNormCellIndex(fuelHeaderCellIndex, fuelHeader))
                .mapToObj(generationNormCellIndex -> createFuelLocation(fuelRow, generationNormCellIndex))
                .findFirst();
    }

    private String findFuelHeader(final FuelSpecification specification) {
        final SpecificationPropertyExtractor headerExtractor = this.headerMetadata.getPropertyExtractor();
        return headerExtractor.extract(specification);
    }

    private int findGenerationNormCellIndex(final int fuelHeaderCellIndex, final String fuelHeader) {
        final int fuelOffset = this.findFuelOffset(fuelHeader);
        return fuelHeaderCellIndex + fuelOffset;
    }

    private int findFuelOffset(final String fuelHeader) {
        final Optional<Integer> optionalFuelOffset = this.headerMetadata.findFuelOffset(fuelHeader);
        return optionalFuelOffset.orElseThrow(
                () -> new FuelOffsetNotExistException(
                        "Fuel's offset for header's value '%s' doesn't exist".formatted(fuelHeader)
                )
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
        return XWPFTableRowUtil.extractCellDoubleValue(row, cellIndex);
    }

    private record FuelLocation(XWPFTableRow row, int cellIndexGenerationNorm, int cellIndexConsumption) {
    }

    public static abstract class SearcherBuilder<S extends FuelSearcher> extends BuilderRequiringAllProperties<S> {
        private FuelTable table;

        private FuelHeaderMetadata headerMetadata;

        private FilterChainBuilder filterChainBuilder;

        public final void table(final FuelTable table) {
            this.table = table;
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
            final Stream<Object> currentProperties = Stream.of(this.table, this.headerMetadata, this.filterChainBuilder);
            final Stream<Object> additionalProperties = this.findAdditionalProperties();
            return concat(currentProperties, additionalProperties);
        }

        @Override
        protected final S buildAfterStateValidation() {
            this.validateFuelTable(this.table);
            final FilterChain filterChain = this.filterChainBuilder.build();
            return this.build(this.table, this.headerMetadata, filterChain);
        }

        protected abstract boolean isValidElements(final List<IBodyElement> elements);

        protected abstract String findNotValidElementsMessage();

        protected abstract S build(final FuelTable table,
                                   final FuelHeaderMetadata headerMetadata,
                                   final FilterChain filterChain);

        protected abstract Stream<Object> findAdditionalProperties();

        private void createFilterChainBuilderIfNecessary() {
            if (this.filterChainBuilder == null) {
                this.filterChainBuilder = FilterChain.builder();
            }
        }

        private void validateFuelTable(final FuelTable fuelTable) {
            final List<IBodyElement> elements = fuelTable.elements();
            if (!this.isValidElements(elements)) {
                final String exceptionDescription = this.findNotValidElementsMessage();
                throw new IllegalStateException(exceptionDescription);
            }
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
