package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

public final class SimpleTableFuelSearcher extends AbstractTableFuelSearcher {

    public SimpleTableFuelSearcher(final FuelTable fuelTable,
                                   final List<String> fuelHeaders,
                                   final RowFilterChain filterChain,
                                   final SpecificationPropertyExtractor fuelHeaderCellValueExtractor) {
        super(fuelTable, fuelHeaders, filterChain, fuelHeaderCellValueExtractor);
    }

    public static SimpleTableFuelSearcherBuilder builder() {
        return new SimpleTableFuelSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findElementTable(final FuelTable fuelTable, final Specification specification) {
        final List<IBodyElement> elements = fuelTable.getElements();
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable elementTable = (XWPFTable) firstElement;
        return Optional.of(elementTable);
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class SimpleTableFuelSearcherBuilder
            extends AbstractTableFuelSearcherBuilder<SimpleTableFuelSearcher> {

        @Override
        protected SimpleTableFuelSearcher build(final FuelTable fuelTable,
                                                final List<String> fuelHeaders,
                                                final RowFilterChain filterChain,
                                                final SpecificationPropertyExtractor fuelHeaderCellValueExtractor) {
            return new SimpleTableFuelSearcher(fuelTable, fuelHeaders, filterChain, fuelHeaderCellValueExtractor);
        }

    }
}
