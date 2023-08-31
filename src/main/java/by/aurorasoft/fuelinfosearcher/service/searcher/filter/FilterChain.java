package by.aurorasoft.fuelinfosearcher.service.searcher.filter;

import by.aurorasoft.fuelinfosearcher.builder.BuilderRequiringAllProperties;
import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.InterimFilter;
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
                .map(filter -> createInterimFilteringFunction(filter, specification))
                .reduce(InterimFilteringFunction::andThenInterimFunction)
                .map(filteringFunction -> filteringFunction.andThenFinalFilter(finalFilteringFunction))
                .orElse(finalFilteringFunction);
    }

    private static InterimFilteringFunction createInterimFilteringFunction(final InterimFilter filter,
                                                                           final Specification specification) {
        return rows -> filter.filter(rows, specification);
    }

    @FunctionalInterface
    private interface FilteringFunction<R> extends Function<List<XWPFTableRow>, R> {

    }

    @FunctionalInterface
    private interface InterimFilteringFunction extends FilteringFunction<List<XWPFTableRow>> {

        default InterimFilteringFunction andThenInterimFunction(final InterimFilteringFunction after) {
            return rows -> this.andThen(after).apply(rows);
        }

        default FinalFilteringFunction andThenFinalFilter(final FinalFilteringFunction after) {
            return rows -> this.andThen(after).apply(rows);
        }

    }

    @FunctionalInterface
    private interface FinalFilteringFunction extends FilteringFunction<Optional<XWPFTableRow>> {

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
