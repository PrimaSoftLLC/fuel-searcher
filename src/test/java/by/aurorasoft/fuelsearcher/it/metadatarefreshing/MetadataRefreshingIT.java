package by.aurorasoft.fuelsearcher.it.metadatarefreshing;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
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
    public void propertyMetadataShouldBeRefreshed(final MetadataArguments arguments) {
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
                    //Table #1
                    createMetadataJunitArguments(
                            "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ",
                            "длина гона",
                            new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ",
                            "удельное сопротивление",
                            new String[]{"Удельное сопротивление 48...53 кПа", "Удельное сопротивление 54...59 кПа", "Удельное сопротивление 60...65 кПа"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ",
                            "трактор",
                            new String[]{"FENDT 1050", "Кировец К-744 Р4", "Кировец К-744 Р3", "Fendt 936 Vario", "Беларус 3525", "Fendt 936", "Беларус 3522", "Кировец K744P2", "John Deere 8520", "К 744Р3"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ",
                            "механизм",
                            new String[]{"Lemken Diamand 11", "ППО-8-40", "ППУ-13", "Kverneland RW 110", "Kverneland PG-100", "Vari Titan 10 7+3 L100", "ППШ-10-35", "Kuhn Challenger NSH-9", "ППО-9-45", "ППО-9-30/45"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ",
                            "количество корпусов",
                            new String[]{"9", "8", "13", "12", "10"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ",
                            "глубина вспашки",
                            new String[]{"18-20", "21-22", "23-25", "25-27"}
                    ),

                    //Table #2
                    createMetadataJunitArguments(
                            "ВСПАШКА СТЕРНИ",
                            "длина гона",
                            new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА СТЕРНИ",
                            "удельное сопротивление",
                            new String[]{"Удельное сопротивление 36...41 кПа", "Удельное сопротивление 42...47 кПа", "Удельное сопротивление 48...53 кПа", "Удельное сопротивление плуга 54...59 кПа", "Удельное сопротивление 60...65 кПа", "Удельное сопротивление 66...71 кПа"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА СТЕРНИ",
                            "трактор",
                            new String[]{"CASE II Steiger 550", "FENDT 100", "Кировец К-744 Р4", "Кировец К-744 Р3", "Massey Ferguson 8737S", "Fendt936 Vario", "Беларус 3525", "Fendt 936", "Беларус 3522", "Кировец K744 P2", "Case Magnum 340", "Jhon Deer 8530", "Беларус 3022", "Кировец К701", "John Deere 8520", "Fendt 927 Vario", "John Deere 8420", "Беларус 2522", "Кировец К-424", "Беларус 2022", "Беларус 1822", "Беларус 1523", "FENDT 1050"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА СТЕРНИ",
                            "механизм",
                            new String[]{"Lemken EuroTitan 10 8+3+1", "Lemken Diamand 11", "Lemken EuroTitan 10", "ППО-8-40К", "ПБН-6-50А", "ППУ-13", "Kverneland RW 110", "Lemkem Diamant 16", "Kverneland PG-100", "Lemken Diamant 11 7 L100", "Vari Titan 10 7+3 L100", "ППШ-10-35", "ППО-9-45", "ППО-8-40", "ППН-8-30/50", "ППО-9-45К", "ПОМ-6+1+1", "ПН-8-40", "ППН-7-30/50", "ПН-8-35У", "ППО-9-30/45", "KUHN Manager F10", "Amazone Cayron 200", "Lemken EuroDiamant 6", "ППН-7-30", "Lemken EurOpal-5", "ПЛН-5-35", "ПО-(4+1)-40", "ПКМ-5-40Р", "ПО 4-40", "ПОН-4-40", "ПЛН-4-35"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА СТЕРНИ",
                            "количество корпусов",
                            new String[]{"12", "9", "10", "8", "6", "13", "7", "5", "4"}
                    ),
                    createMetadataJunitArguments(
                            "ВСПАШКА СТЕРНИ",
                            "глубина вспашки",
                            new String[]{"18-20", "21-22", "23-25", "25-27"}
                    ),

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
