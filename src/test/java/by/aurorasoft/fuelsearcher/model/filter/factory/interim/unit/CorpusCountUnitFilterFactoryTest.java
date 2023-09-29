//package by.aurorasoft.fuelsearcher.model.filter.factory.interim.unit;
//
//import by.aurorasoft.fuelsearcher.model.filter.interim.unit.CorpusCountUnitFilter;
//import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.CorpusCountExtractor;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertSame;
//import static org.mockito.Mockito.mock;
//
//public final class CorpusCountUnitFilterFactoryTest {
//    private final CorpusCountUnitFilterFactory factory = new CorpusCountUnitFilterFactory(null);
//
//    @Test
//    public void filterShouldBeCreated() {
//        final CorpusCountExtractor givenCorpusCountExtractor = mock(CorpusCountExtractor.class);
//        final int givenFiltrationCellIndex = 5;
//
//        final CorpusCountUnitFilter actual = this.factory.create(givenCorpusCountExtractor, givenFiltrationCellIndex);
//
//        assertSame(givenCorpusCountExtractor, actual.getFiltrationValueExtractor());
//        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
//    }
//}
