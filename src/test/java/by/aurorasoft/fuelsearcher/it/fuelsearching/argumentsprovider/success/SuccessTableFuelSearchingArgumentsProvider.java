package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.TableFuelSearchingArgumentsProvider;
import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.SuccessFuelSearchingArguments;

import java.util.stream.Stream;

public abstract class SuccessTableFuelSearchingArgumentsProvider
        extends TableFuelSearchingArgumentsProvider<SuccessFuelSearchingArguments> {

    public SuccessTableFuelSearchingArgumentsProvider(final String tableName) {
        super(tableName);
    }

    @Override
    protected final Stream<Object> findAdditionalJunitArgumentComponents(final SuccessFuelSearchingArguments arguments) {
        return Stream.of(
                arguments.getExpected()
        );
    }

}
