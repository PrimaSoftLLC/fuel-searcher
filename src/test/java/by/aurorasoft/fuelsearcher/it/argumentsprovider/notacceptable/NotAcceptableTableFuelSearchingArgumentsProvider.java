package by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.TableFuelSearchingArgumentsProvider;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;

import java.util.stream.Stream;

public abstract class NotAcceptableTableFuelSearchingArgumentsProvider
        extends TableFuelSearchingArgumentsProvider<NotAcceptableFuelSearchingArguments> {

    public NotAcceptableTableFuelSearchingArgumentsProvider(final String tableName) {
        super(tableName);
    }

    @Override
    protected final Stream<Object> findAdditionalJunitArgumentComponents(final NotAcceptableFuelSearchingArguments arguments) {
        return Stream.of(
                arguments.getFailedPropertyNames()
        );
    }
}
