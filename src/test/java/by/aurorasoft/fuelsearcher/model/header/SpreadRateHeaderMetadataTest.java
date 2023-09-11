package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertArrayEquals;

public final class SpreadRateHeaderMetadataTest extends AbstractContextTest {

    @Autowired
    private SpreadRateHeaderMetadata metadata;

    @Test
    public void valuesShouldBeLoaded() {
        final String[] actual = this.metadata.getValues();
        final String[] expected = {"Менее 30", "30-50", "Более 50"};
        assertArrayEquals(expected, actual);
    }

}
