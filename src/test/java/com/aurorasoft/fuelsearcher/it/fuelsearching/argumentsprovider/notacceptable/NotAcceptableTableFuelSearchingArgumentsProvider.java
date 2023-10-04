package com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notacceptable;

import com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.TableFuelSearchingArgumentsProvider;
import com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotAcceptableFuelSearchingArguments;

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
