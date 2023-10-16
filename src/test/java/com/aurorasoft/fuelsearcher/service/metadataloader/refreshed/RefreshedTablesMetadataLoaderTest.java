package com.aurorasoft.fuelsearcher.service.metadataloader.refreshed;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.RefreshedTablesMetadataFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class RefreshedTablesMetadataLoaderTest {

    @Mock
    private RefreshedTablesMetadataFactory mockedRefreshedMetadataFactory;

    @Mock
    private RefreshedTablesMetadataSaver mockedRefreshedMetadataSaver;

    private RefreshedTablesMetadataLoader loader;

    @Before
    public void initializeLoader() {
        this.loader = new RefreshedTablesMetadataLoader(
                this.mockedRefreshedMetadataFactory,
                this.mockedRefreshedMetadataSaver
        );
    }

    @Test
    public void loaderShouldBeAbleToLoad() {
        final boolean actual = this.loader.isAbleToLoad();
        assertTrue(actual);
    }

    @Test
    public void metadataShouldBeLoaded() {
        final List<TableMetadata> givenTablesMetadata = List.of(
                mock(TableMetadata.class),
                mock(TableMetadata.class),
                mock(TableMetadata.class)
        );
        when(this.mockedRefreshedMetadataFactory.create()).thenReturn(givenTablesMetadata);

        final List<TableMetadata> actual = this.loader.load();
        assertSame(givenTablesMetadata, actual);

        verify(this.mockedRefreshedMetadataSaver, times(1)).save(same(givenTablesMetadata));
    }
}
