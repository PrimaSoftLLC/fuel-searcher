package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain;

import by.aurorasoft.fuelinfosearcher.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.AbstractInterimFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class FilterChain {
    List<AbstractInterimFilter> interimFilters;
    FinalFilter finalFilter;

    public Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows, final Specification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> filteringFunction = this.createFilteringFunction(
                specification
        );
        return filteringFunction.apply(rows);
    }

    public static FilterChainBuilder builder() {
        return new FilterChainBuilder();
    }

    private Function<List<XWPFTableRow>, Optional<XWPFTableRow>> createFilteringFunction(final Specification specification) {
        final Function<List<XWPFTableRow>, Optional<XWPFTableRow>> conclusiveFilteringFunction = this.finalFilter
                .mapToFilteringFunction(specification);
        return this.interimFilters.stream()
                .map(filter -> filter.mapToFilteringFunction(specification))
                .reduce(Function::andThen)
                .map(filteringFunction -> filteringFunction.andThen(conclusiveFilteringFunction))
                .orElse(conclusiveFilteringFunction);
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class FilterChainBuilder extends BuilderRequiringAllProperties<FilterChain> {
        private List<AbstractInterimFilter> interimFilters;
        private FinalFilter finalFilter;

        public void interimFilter(final AbstractInterimFilter filter) {
            this.createInterimFiltersIfNecessary();
            this.interimFilters.add(filter);
        }

        public void finalFilter(final FinalFilter filter) {
            this.finalFilter = filter;
        }

        @Override
        protected Stream<Object> findProperties() {
            return Stream.of(this.interimFilters, this.finalFilter);
        }

        @Override
        protected FilterChain buildAfterStateValidation() {
            return new FilterChain(this.interimFilters, this.finalFilter);
        }

        private void createInterimFiltersIfNecessary() {
            if (this.interimFilters == null) {
                this.interimFilters = new ArrayList<>();
            }
        }
    }
}
