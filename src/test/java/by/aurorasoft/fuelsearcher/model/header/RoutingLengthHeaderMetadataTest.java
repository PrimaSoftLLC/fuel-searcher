//package by.aurorasoft.fuelsearcher.model.header;
//
//import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.Assert.assertArrayEquals;
//
//public final class RoutingLengthHeaderMetadataTest extends AbstractContextTest {
//
//    @Autowired
//    private RoutingLengthHeaderMetadata metadata;
//
//    @Test
//    public void valuesShouldBeLoaded() {
//        final String[] actual = this.metadata.getValues();
//        final String[] expected = {"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"};
//        assertArrayEquals(expected, actual);
//    }
//}
