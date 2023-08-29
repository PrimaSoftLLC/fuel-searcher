package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain;

import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.exception.RowFilterChainBuildingException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.AbstractInterimFilter;
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
    List<AbstractInterimFilter> intermediateFilters;
    FinalFilter conclusiveFilter;

    public Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows, final Specification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> filteringFunction = this.createFilteringFunction(
                specification
        );
        return filteringFunction.apply(rows);
    }

    private Function<List<XWPFTableRow>, Optional<XWPFTableRow>> createFilteringFunction(
            final Specification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> conclusiveFilteringFunction = this.conclusiveFilter
                .mapToFilteringFunction(specification);
        return this.intermediateFilters.stream()
                .map(filter -> filter.mapToFilteringFunction(specification))
                .reduce(Function::andThen)
                .map(filteringFunction -> filteringFunction.andThen(conclusiveFilteringFunction))
                .orElse(conclusiveFilteringFunction);
    }

    public static RowFilterChainBuilder builder() {
        return new RowFilterChainBuilder();
    }

    public static final class RowFilterChainBuilder {
        private final List<AbstractInterimFilter> intermediateFilters;
        private FinalFilter conclusiveFilter;

        private RowFilterChainBuilder() {
            this.intermediateFilters = new ArrayList<>();
        }

        public RowFilterChainBuilder intermediateFilter(final AbstractInterimFilter filter) {
            this.intermediateFilters.add(filter);
            return this;
        }

        public RowFilterChainBuilder conclusiveFilter(final FinalFilter filter) {
            this.conclusiveFilter = filter;
            return this;
        }

        public RowFilterChain build() {
            this.validateState();
            return new RowFilterChain(this.intermediateFilters, this.conclusiveFilter);
        }

        //TODO: refactor(do super class with this method)
        private void validateState() {
            if (this.conclusiveFilter == null) {
                throw new RowFilterChainBuildingException("Conclusive filter isn't defined");
            }
        }
    }
}