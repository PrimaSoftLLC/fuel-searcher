package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelLocation;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searcher.exception.FuelSearcherBuildingException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.RowFilterChain.RowFilterChainBuilder;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.AbstractInterimFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelUtil;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findIndexFirstCellByContent;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

//TODO: put FuelInfoLocation as inner class
public abstract class FuelSearcher {
    private static final int ROW_INDEX_FUEL_HEADER = 1;

    private final FuelTable fuelTable;
    private final Map<String, Integer> fuelOffsetsByHeaders;
    private final RowFilterChain filterChain;
    private final SpecificationPropertyExtractor fuelHeaderCellValueExtractor;

    public FuelSearcher(final FuelTable fuelTable,
                        final List<String> fuelHeaders,
                        final RowFilterChain filterChain,
                        final SpecificationPropertyExtractor fuelHeaderCellValueExtractor) {
        this.fuelTable = fuelTable;
        this.fuelOffsetsByHeaders = createFuelOffsetsByHeaders(fuelHeaders);
        this.filterChain = filterChain;
        this.fuelHeaderCellValueExtractor = fuelHeaderCellValueExtractor;
    }

    public final String findTableName() {
        return this.fuelTable.getName();
    }

    public final Optional<Fuel> find(final Specification specification) {
        return this.findElementTable(this.fuelTable, specification)
                .map(XWPFTable::getRows)
                .flatMap(elementTableRows -> this.findFuel(elementTableRows, specification));
    }

    protected abstract Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                            final Specification specification);

    private static Map<String, Integer> createFuelOffsetsByHeaders(final List<String> fuelHeaders) {
        return range(0, fuelHeaders.size())
                .boxed()
                .collect(toMap(fuelHeaders::get, identity()));
    }

    private Optional<Fuel> findFuel(final List<XWPFTableRow> elementTableRows, final Specification specification) {
        final XWPFTableRow fuelHeaderRow = elementTableRows.get(ROW_INDEX_FUEL_HEADER);
        return this.filterChain.filter(elementTableRows, specification)
                .flatMap(row -> this.findFuelLocation(fuelHeaderRow, specification, row))
                .flatMap(FuelUtil::extractFuel);
    }

    private Optional<FuelLocation> findFuelLocation(final XWPFTableRow fuelHeaderRow,
                                                    final Specification specification,
                                                    final XWPFTableRow dataRow) {
        final String fuelHeaderCellValue = this.fuelHeaderCellValueExtractor.apply(specification);
        return findIndexFirstCellByContent(fuelHeaderRow, fuelHeaderCellValue)
                .stream()
                .map(fuelHeaderCellIndex -> this.findCellIndexGenerationNorm(fuelHeaderCellIndex, fuelHeaderCellValue))
                .mapToObj(generationNormCellIndex -> createFuelLocation(dataRow, generationNormCellIndex))
                .findFirst();
    }

    private int findCellIndexGenerationNorm(final int fuelHeaderCellIndex, final String fuelHeaderCellValue) {
        final int fuelOffset = this.fuelOffsetsByHeaders.get(fuelHeaderCellValue);
        return fuelHeaderCellIndex + fuelOffset;
    }

    private static FuelLocation createFuelLocation(final XWPFTableRow dataRow, final int generationNormCellIndex) {
        final int consumptionCellIndex = generationNormCellIndex + 1;
        return new FuelLocation(dataRow, generationNormCellIndex, consumptionCellIndex);
    }

    public static abstract class FuelSearcherBuilder<S extends FuelSearcher> {
        private FuelTable fuelTable;
        private final List<String> fuelHeaders = new ArrayList<>();
        private final RowFilterChainBuilder filterChainBuilder = RowFilterChain.builder();
        private SpecificationPropertyExtractor fuelHeaderCellValueExtractor;

        public final FuelSearcherBuilder<S> fuelTable(final FuelTable fuelTable) {
            this.fuelTable = fuelTable;
            return this;
        }

        public final FuelSearcherBuilder<S> fuelHeader(final String fuelHeader) {
            this.fuelHeaders.add(fuelHeader);
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

        public final FuelSearcherBuilder<S> fuelHeaderCellValueExtractor(
                final SpecificationPropertyExtractor fuelHeaderCellValueExtractor) {
            this.fuelHeaderCellValueExtractor = fuelHeaderCellValueExtractor;
            return this;
        }

        public final S build() {
            this.validateState();
            final RowFilterChain filterChain = this.filterChainBuilder.build();
            return this.build(this.fuelTable, this.fuelHeaders, filterChain, this.fuelHeaderCellValueExtractor);
        }

        protected abstract S build(final FuelTable fuelTable,
                                   final List<String> fuelHeaders,
                                   final RowFilterChain filterChain,
                                   final SpecificationPropertyExtractor fuelHeaderCellValueExtractor);

        //TODO: refactor(do super class with this method)
        private void validateState() {
            if (this.fuelTable == null) {
                throw new FuelSearcherBuildingException("Fuel table isn't defined");
            } else if (this.fuelHeaders.isEmpty()) {
                throw new FuelSearcherBuildingException("Fuel headers isn't defined");
            } else if (this.fuelHeaderCellValueExtractor == null) {
                throw new FuelSearcherBuildingException("Fuel header cell value extractor isn't defined");
            }
        }
    }
}
