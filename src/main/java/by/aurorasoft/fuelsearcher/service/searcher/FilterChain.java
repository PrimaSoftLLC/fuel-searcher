package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.filter.Filter;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public final class FilterChain {
    private final List<InterimFilter> interimFilters;
    private final FinalFilter finalFilter;

    public static FilterChainBuilder builder() {
        return new FilterChainBuilder();
    }

    public Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows, final FuelSpecification specification) {
        final FinalFilteringFunction filteringFunction = this.createFilteringFunction(specification);
        return filteringFunction.apply(rows);
    }

    public Stream<Filter<?>> findFilters() {
        return Stream.concat(
                this.interimFilters.stream(),
                Stream.of(this.finalFilter)
        );
    }

    private FinalFilteringFunction createFilteringFunction(final FuelSpecification specification) {
        final FinalFilteringFunction finalFilteringFunction = createFinalFilteringFunction(
                this.finalFilter,
                specification
        );
        return this.interimFilters.stream()
                .map(filter -> createInterimFilteringFunction(filter, specification))
                .reduce(InterimFilteringFunction::andThenInterimFunction)
                .map(filteringFunction -> filteringFunction.andThenFinalFilter(finalFilteringFunction))
                .orElse(finalFilteringFunction);
    }

    private static FinalFilteringFunction createFinalFilteringFunction(final FinalFilter filter,
                                                                       final FuelSpecification specification) {
        return rows -> filter.filter(rows, specification);
    }

    private static InterimFilteringFunction createInterimFilteringFunction(final InterimFilter filter,
                                                                           final FuelSpecification specification) {
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
