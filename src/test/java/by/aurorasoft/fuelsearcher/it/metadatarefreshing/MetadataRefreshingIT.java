package by.aurorasoft.fuelsearcher.it.metadatarefreshing;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.DocumentMetadataArgumentsProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.CollectionUtil.areEqualIgnoringOrder;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.context.support.TestPropertySourceUtils.addInlinedPropertiesToEnvironment;

@ContextConfiguration(initializers = {MetadataRefreshingIT.MetadataRefreshingEnablePropertyOverrider.class})
public final class MetadataRefreshingIT extends AbstractContextTest {
    private static final String HQL_QUERY_TO_FIND_PROPERTY_ALLOWABLE_VALUES
            = "SELECT e.allowableValues FROM PropertyMetadataEntity e "
            + "WHERE e.propertyName = :propertyName AND e.tableMetadata.tableName = :tableName";
    private static final String PARAMETER_NAME_PROPERTY_NAME = "propertyName";
    private static final String PARAMETER_NAME_TABLE_NAME = "tableName";

    @ParameterizedTest
    @ArgumentsSource(DocumentMetadataArgumentsProvider.class)
    public void propertyMetadataShouldBeRefreshed(final PropertyMetadataArguments arguments) {
        final String givenTableName = arguments.tableName();
        final String givenPropertyName = arguments.propertyName();

        final String[] actualPropertyAllowableValues = this.findPropertyAllowableValues(
                givenTableName,
                givenPropertyName
        );
        final String[] expectedPropertyAllowableValues = arguments.expectedPropertyAllowableValues();
        assertTrue(areEqualIgnoringOrder(expectedPropertyAllowableValues, actualPropertyAllowableValues));
    }

    private String[] findPropertyAllowableValues(final String tableName, final String propertyName) {
        return super.entityManager.createQuery(HQL_QUERY_TO_FIND_PROPERTY_ALLOWABLE_VALUES, String[].class)
                .setParameter(PARAMETER_NAME_TABLE_NAME, tableName)
                .setParameter(PARAMETER_NAME_PROPERTY_NAME, propertyName)
                .getSingleResult();
    }

    private static final class TEMPDocumentMetadataArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext extensionContext) {
            return Stream.of(

                    //Table #3
                    createMetadataJunitArguments(
                            "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ",
                            "длина гона",
                            new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ",
                            "тип почвы",
                            new String[]{"Минеральные почвы", "Торфяные почвы"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ",
                            "трактор",
                            new String[]{"Беларус-3522", "Беларус-3022"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ",
                            "механизм",
                            new String[]{"ПБН-6-50А"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ",
                            "количество корпусов",
                            new String[]{"6"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ",
                            "глубина вспашки",
                            new String[]{"21-22", "23-25", "25-27", "27-30", "30-35"}
                    ),

                    //Table #4
                    createMetadataJunitArguments(
                            "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ",
                            "длина гона",
                            new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                    ),
                    createMetadataJunitArguments(
                            "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ",
                            "глубина обработки",
                            new String[]{"Глубина обработки 6...8 см", "Глубина обработки 8...10 см", "Глубина обработки 10...14 см", "Глубина обработки 14...20 см"}
                    ),
                    createMetadataJunitArguments(
                            "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ",
                            "трактор",
                            new String[]{"Clas Xerion 5000", "Кировец К-744Р4", "Fendt 936", "Беларус 3522", "Case in Magnum 340", "Fendt 930", "John Deere 8430", "Беларус 3022", "Кировец К744Р1", "John Deere 8420", "Fendt 927", "Беларус 2522", "Беларус 2022", "Case IN Puma 210", "Беларус 1522"}
                    ),
                    createMetadataJunitArguments(
                            "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ",
                            "механизм",
                            new String[]{"Köckerling Allrounder 1200", "КПМ-14", "КШП-10", "Köckerling Allrounder 900", "Horsch Tiger 8MT", "Kverneland STS 600", "АПМ-10", "КУ-10", "АПМ-8", "Köckerling Vector 800", "КФУ-7.8", "КФУ-7.3", "Köckerling Vector 620", "КГП-6.2", "Cuhn Cultimer L 6000", "АП-6-13", "АКПН-6", "Köckerling Vector 570", "Lemken Karat 9/500KA", "Vibro Master 3083SS", "КПМ-12", "Horsch Tiger 6MT", "КШП-8.5", "Köckerling Allrounder 750", "John Deere 714 (17 стоек)", "Horsch Terrano 6FX", "Kverneland STS 500", "Lemken Korund 8/900", "Köckerling Vector 460", "SYNCRO 6030T", "Horsch Tiger 5MT", "Horsch Tiger 4MT", "Horsch Terrano 4FX", "КГП-4.6", "КШУ-12", "КСО-9.6", "Vaderstad Aggressive 700"}
                    ),
                    createMetadataJunitArguments(
                            "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ",
                            "ширина захвата",
                            new String[]{"12", "14", "10", "9", "8", "6", "10.3", "7.8", "7.3", "6.2", "5.7", "5", "8.3", "8.5", "7.5", "6.5", "4.6", "4", "9.6", "7"}
                    )
            );
        }

    }

    public static class MetadataRefreshingEnablePropertyOverrider
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(final @NotNull ConfigurableApplicationContext context) {
            addInlinedPropertiesToEnvironment(context, "metadata-refreshing.enable=true");
        }
    }
}
