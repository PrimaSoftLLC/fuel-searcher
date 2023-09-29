//package by.aurorasoft.fuelsearcher.model.filter.factory.conclusive;
//
//import by.aurorasoft.fuelsearcher.model.filter.conclusive.WorkingWidthFinalFilter;
//import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.WorkingWidthExtractor;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertSame;
//import static org.mockito.Mockito.mock;
//
//public final class WorkingWidthFinalFilterFactoryTest {
//    private final WorkingWidthFinalFilterFactory factory = new WorkingWidthFinalFilterFactory(null);
//
//    @Test
//    public void filterShouldBeCreated() {
//        final WorkingWidthExtractor givenWorkingWidthExtractor = mock(WorkingWidthExtractor.class);
//        final int givenFiltrationCellIndex = 6;
//
//        final WorkingWidthFinalFilter actual = this.factory.create(givenWorkingWidthExtractor, givenFiltrationCellIndex);
//        assertSame(givenWorkingWidthExtractor, actual.getFiltrationValueExtractor());
//        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
//    }
//}
