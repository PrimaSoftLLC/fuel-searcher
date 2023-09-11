package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertArrayEquals;

public final class RoadGroupHeaderMetadataTest extends AbstractContextTest {

    @Autowired
    private RoadGroupHeaderMetadata metadata;

    @Test
    public void valuesShouldBeLoaded() {
        final String[] actual = this.metadata.getValues();
        final String[] expected = {"I", "II", "III"};
        assertArrayEquals(expected, actual);
    }

}
