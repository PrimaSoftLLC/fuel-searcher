package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.TableFuelSearchingArgumentsProvider;
import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;

import java.util.stream.Stream;

import static java.util.stream.Stream.empty;

public abstract class NotFoundTableFuelSearchingArgumentsProvider
        extends TableFuelSearchingArgumentsProvider<NotFoundFuelSearchingArguments> {

    public NotFoundTableFuelSearchingArgumentsProvider(final String tableName) {
        super(tableName);
    }

    @Override
    protected final Stream<Object> findAdditionalJunitArgumentComponents(final NotFoundFuelSearchingArguments arguments) {
        return empty();
    }
}
