package by.aurorasoft.fuelsearcher.service.derivingsearcherfactory;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class DerivingSearcherFactory<T> {
    private final List<FuelSearcher> searchers;

    public final List<T> create() {
        return this.searchers.stream()
                .map(this::createDerivedObject)
                .toList();
    }

    protected abstract T createDerivedObject(final FuelSearcher searcher);
}
