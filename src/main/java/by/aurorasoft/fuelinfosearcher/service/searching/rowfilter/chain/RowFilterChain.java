package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.exception.RowFilterChainBuildingException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.AbstractConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.AbstractIntermediateRowFilter;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class RowFilterChain {
    List<AbstractIntermediateRowFilter> intermediateFilters;
    AbstractConclusiveRowFilter conclusiveFilter;

    public Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows, final FuelSpecification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> filteringFunction = this.createFilteringFunction(
                specification
        );
        return filteringFunction.apply(rows);
    }

    private Function<List<XWPFTableRow>, Optional<XWPFTableRow>> createFilteringFunction(final FuelSpecification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> conclusiveFilteringFunction = this.conclusiveFilter
                .mapToFilteringFunction(specification);
        return this.intermediateFilters.stream()
                .map(filter -> filter.mapToFilteringFunction(specification))
                .reduce(Function::andThen)
                .map(filteringFunction -> filteringFunction.andThen(conclusiveFilteringFunction))
                .orElse(conclusiveFilteringFunction);
    }

    public static FilterChainBuilder builder() {
        return new FilterChainBuilder();
    }

    public static class FilterChainBuilder {
        private final List<AbstractIntermediateRowFilter> intermediateFilters;
        private AbstractConclusiveRowFilter conclusiveFilter;

        private FilterChainBuilder() {
            this.intermediateFilters = new ArrayList<>();
        }

        public FilterChainBuilder intermediateFilter(final AbstractIntermediateRowFilter filter) {
            this.intermediateFilters.add(filter);
            return this;
        }

        public FilterChainBuilder conclusiveFilter(final AbstractConclusiveRowFilter filter) {
            this.conclusiveFilter = filter;
            return this;
        }

        public RowFilterChain build() {
            if (this.conclusiveFilter == null) {
                throw new RowFilterChainBuildingException("Conclusive filter isn't defined");
            }
            return new RowFilterChain(this.intermediateFilters, this.conclusiveFilter);
        }
    }
}
