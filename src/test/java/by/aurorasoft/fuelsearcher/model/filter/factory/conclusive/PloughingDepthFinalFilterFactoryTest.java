//package by.aurorasoft.fuelsearcher.model.filter.factory.conclusive;
//
//import by.aurorasoft.fuelsearcher.model.filter.conclusive.PloughingDepthFinalFilter;
//import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.PloughingDepthExtractor;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertSame;
//import static org.mockito.Mockito.mock;
//
//public final class PloughingDepthFinalFilterFactoryTest {
//    private final PloughingDepthFinalFilterFactory factory = new PloughingDepthFinalFilterFactory(
//            null
//    );
//
//    @Test
//    public void filterShouldBeCreated() {
//        final PloughingDepthExtractor givenPloughingDepthExtractor = mock(PloughingDepthExtractor.class);
//        final int givenFiltrationCellIndex = 4;
//
//        final PloughingDepthFinalFilter actual = this.factory.create(
//                givenPloughingDepthExtractor, givenFiltrationCellIndex
//        );
//        assertSame(givenPloughingDepthExtractor, actual.getFiltrationValueExtractor());
//        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
//    }
//
//}
