//package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;
//
//import by.aurorasoft.fuelsearcher.model.filter.interim.group.CargoClassGroupFilter;
//import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.CargoClassExtractor;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertSame;
//import static org.mockito.Mockito.mock;
//
//public final class CargoClassGroupFilterFactoryTest {
//    private final CargoClassGroupFilterFactory factory = new CargoClassGroupFilterFactory(null);
//
//    @Test
//    public void filterShouldBeCreated() {
//        final CargoClassExtractor givenCargoClassExtractor = mock(CargoClassExtractor.class);
//        final int givenFiltrationCellIndex = 5;
//
//        final CargoClassGroupFilter actual = this.factory.create(givenCargoClassExtractor, givenFiltrationCellIndex);
//
//        assertSame(givenCargoClassExtractor, actual.getFiltrationValueExtractor());
//        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
//    }
//}
