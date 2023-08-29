package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searcher.exception.FuelSearcherBuildingException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.RowFilterChain.RowFilterChainBuilder;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.AbstractInterimFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

//TODO: put FuelInfoLocation as inner class
public abstract class FuelSearcher {
    private static final int ROW_INDEX_FUEL_HEADER_VALUES = 1;

    private final FuelTable fuelTable;
    private final Map<String, Integer> fuelOffsetsByHeaderValues;
    private final RowFilterChain filterChain;
    private final SpecificationPropertyExtractor fuelHeaderValueExtractor;

    public FuelSearcher(final FuelTable fuelTable,
                        final FuelHeaderMetadata fuelHeaderMetadata,
                        final RowFilterChain filterChain) {
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
        //TODO: throw exception if fuelOffset doesn't exist
        final int fuelOffset = this.fuelOffsetsByHeaderValues.get(fuelHeaderCellValue);
        return fuelHeaderCellIndex + fuelOffset;
    }

    private static FuelLocation createFuelLocation(final XWPFTableRow dataRow, final int generationNormCellIndex) {
        final int consumptionCellIndex = generationNormCellIndex + 1;
        return new FuelLocation(dataRow, generationNormCellIndex, consumptionCellIndex);
    }

    public static abstract class FuelSearcherBuilder<S extends FuelSearcher> {
        private FuelTable fuelTable;
        private FuelHeaderMetadata fuelHeaderMetadata;
        private final RowFilterChainBuilder filterChainBuilder = RowFilterChain.builder();

        public final FuelSearcherBuilder<S> fuelTable(final FuelTable fuelTable) {
            this.fuelTable = fuelTable;
            return this;
        }

        public final FuelSearcherBuilder<S> fuelHeaderMetadata(final FuelHeaderMetadata metadata) {
            this.fuelHeaderMetadata = metadata;
            return this;
        }

        public final FuelSearcherBuilder<S> intermediateFilter(final AbstractInterimFilter filter) {
            this.filterChainBuilder.intermediateFilter(filter);
            return this;
        }

        public final FuelSearcherBuilder<S> conclusiveFilter(final FinalFilter filter) {
            this.filterChainBuilder.conclusiveFilter(filter);
            return this;
        }

        public final S build() {
            this.validateState();
            final RowFilterChain filterChain = this.filterChainBuilder.build();
            return this.build(this.fuelTable, this.fuelHeaderMetadata, filterChain);
        }

        protected abstract S build(final FuelTable fuelTable,
                                   final FuelHeaderMetadata fuelHeaderMetadata,
                                   final RowFilterChain filterChain);

        //TODO: refactor(do super class with this method)
        private void validateState() {
            if (this.fuelTable == null) {
                throw new FuelSearcherBuildingException("Fuel table isn't defined");
            }
            //TODO: continue validation
        }
    }
}
