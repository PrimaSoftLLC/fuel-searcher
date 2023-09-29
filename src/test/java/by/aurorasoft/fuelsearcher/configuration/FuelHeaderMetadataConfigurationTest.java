package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.header.RoadGroupHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.header.RoutingLengthHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.header.SpreadRateHeaderMetadata;
import by.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata.RoadGroupHeaderMetadataFactory;
import by.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata.RoutingLengthHeaderMetadataFactory;
import by.aurorasoft.fuelsearcher.service.factory.fuelheadermetadata.SpreadRateHeaderMetadataFactory;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelHeaderMetadataConfigurationTest {
    private final FuelHeaderMetadataConfiguration configuration = new FuelHeaderMetadataConfiguration();

    @Test
    public void roadGroupHeaderMetadataShouldBeCreated() {
        final RoadGroupHeaderMetadataFactory givenFactory = mock(RoadGroupHeaderMetadataFactory.class);
        final String[] givenHeaderValues = {"first-value", "second-value", "third-value"};

        final RoadGroupHeaderMetadata givenHeaderMetadata = mock(RoadGroupHeaderMetadata.class);
        when(givenFactory.create(same(givenHeaderValues))).thenReturn(givenHeaderMetadata);

        final RoadGroupHeaderMetadata actual = this.configuration.roadGroupHeaderMetadata(
                givenFactory,
                givenHeaderValues
        );
        assertSame(givenHeaderMetadata, actual);
    }

    @Test
    public void routingLengthHeaderMetadataShouldBeCreated() {
        final RoutingLengthHeaderMetadataFactory givenFactory = mock(RoutingLengthHeaderMetadataFactory.class);
        final String[] givenHeaderValues = {"first-value", "second-value", "third-value"};

        final RoutingLengthHeaderMetadata givenHeaderMetadata = mock(RoutingLengthHeaderMetadata.class);
        when(givenFactory.create(same(givenHeaderValues))).thenReturn(givenHeaderMetadata);

        final RoutingLengthHeaderMetadata actual = this.configuration.routingLengthHeaderMetadata(
                givenFactory,
                givenHeaderValues
        );
        assertSame(givenHeaderMetadata, actual);
    }

    @Test
    public void spreadRateHeaderMetadataShouldBeCreated() {
        final SpreadRateHeaderMetadataFactory givenFactory = mock(SpreadRateHeaderMetadataFactory.class);
        final String[] givenHeaderValues = {"first-value", "second-value", "third-value"};

        final SpreadRateHeaderMetadata givenHeaderMetadata = mock(SpreadRateHeaderMetadata.class);
        when(givenFactory.create(same(givenHeaderValues))).thenReturn(givenHeaderMetadata);

        final SpreadRateHeaderMetadata actual = this.configuration.spreadRateHeaderMetadata(
                givenFactory,
                givenHeaderValues
        );
        assertSame(givenHeaderMetadata, actual);
    }

}
