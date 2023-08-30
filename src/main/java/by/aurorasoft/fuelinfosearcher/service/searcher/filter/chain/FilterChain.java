package by.aurorasoft.fuelinfosearcher.service.searcher.filter.chain;

import by.aurorasoft.fuelinfosearcher.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelinfosearcher.functionalinterface.filteringfunction.FinalFilteringFunction;
import by.aurorasoft.fuelinfosearcher.functionalinterface.filteringfunction.InterimFilteringFunction;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.InterimFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class FilterChain {
    List<InterimFilter> interimFilters;
    FinalFilter finalFilter;

    public Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows, final Specification specification) {
        final FinalFilteringFunction filteringFunction = this.createFilteringFunction(specification);
        return filteringFunction.apply(rows);
    }

    public static FilterChainBuilder builder() {
        return new FilterChainBuilder();
    }

    private FinalFilteringFunction createFilteringFunction(final Specification specification) {
        final FinalFilteringFunction finalFilteringFunction = rows -> this.finalFilter.filter(rows, specification);
        return this.interimFilters.stream()
                .map(filter -> (InterimFilteringFunction) rows -> filter.filter(rows, specification))
                .reduce(InterimFilteringFunction::andThenInterimFilter)
                .map(filteringFunction -> filteringFunction.andThenFinalFilter(finalFilteringFunction))
                .orElse(finalFilteringFunction);
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class FilterChainBuilder extends BuilderRequiringAllProperties<FilterChain> {
        private List<InterimFilter> interimFilters;
        private FinalFilter finalFilter;

        public void interimFilter(final InterimFilter filter) {
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
