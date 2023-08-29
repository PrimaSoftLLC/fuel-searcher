package by.aurorasoft.fuelinfosearcher.service.searcher;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.RowFilterChain;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

public final class SimpleFuelSearcher extends FuelSearcher {

    public SimpleFuelSearcher(final FuelTable fuelTable,
                              final List<String> fuelHeaders,
                              final RowFilterChain filterChain,
                              final SpecificationPropertyExtractor fuelHeaderCellValueExtractor) {
        super(fuelTable, fuelHeaders, filterChain, fuelHeaderCellValueExtractor);
    }

    public static SimpleFuelSearcherBuilder builder() {
        return new SimpleFuelSearcherBuilder();
    }

    @Override
    protected Optional<XWPFTable> findElementTable(final FuelTable fuelTable, final Specification specification) {
        final List<IBodyElement> elements = fuelTable.getElements();
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable elementTable = (XWPFTable) firstElement;
        return Optional.of(elementTable);
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class SimpleFuelSearcherBuilder extends FuelSearcherBuilder<SimpleFuelSearcher> {

        @Override
        protected SimpleFuelSearcher build(final FuelTable fuelTable,
                                           final List<String> fuelHeaders,
                                           final RowFilterChain filterChain,
                                           final SpecificationPropertyExtractor fuelHeaderCellValueExtractor) {
            return new SimpleFuelSearcher(fuelTable, fuelHeaders, filterChain, fuelHeaderCellValueExtractor);
        }

    }
}