package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

import java.util.function.Supplier;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class TableFuelSearchingArgumentsProvider<T extends FuelSearchingArguments> {
    private final String tableName;

    public final Stream<Arguments> provide() {
        return this.createArguments().map(this::mapToJunitArguments);
    }

    protected abstract Stream<T> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier);

    protected abstract Stream<Object> findAdditionalJunitArgumentComponents(T arguments);

    private Stream<T> createArguments() {
        return this.createArguments(this::createSpecificationBuilder);
    }

    private FuelSpecificationBuilder createSpecificationBuilder() {
        return FuelSpecification.builder().tableName(this.tableName);
    }

    private Arguments mapToJunitArguments(final T arguments) {
        final Object[] argumentComponents = this.findJunitArgumentComponents(arguments);
        return Arguments.of(argumentComponents);
    }

    private Object[] findJunitArgumentComponents(final T arguments) {
        final FuelSpecification specification = arguments.getSpecification();
        final Stream<Object> additionalArguments = this.findAdditionalJunitArgumentComponents(arguments);
        return Stream.concat(
                Stream.of(specification),
                additionalArguments
        ).toArray();
    }
}
