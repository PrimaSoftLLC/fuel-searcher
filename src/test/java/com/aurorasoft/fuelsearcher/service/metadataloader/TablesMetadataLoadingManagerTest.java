package com.aurorasoft.fuelsearcher.service.metadataloader;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.metadataloader.exception.TablesMetadataLoadingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class TablesMetadataLoadingManagerTest {

    @Mock
    private TablesMetadataLoader firstMockedLoader;

    @Mock
    private TablesMetadataLoader secondMockedLoader;

    @Mock
    private TablesMetadataLoader thirdMockedLoader;

    private TablesMetadataLoadingManager loadingManager;

    @Before
    public void initializeLoadingManager() {
        this.loadingManager = new TablesMetadataLoadingManager(
                List.of(this.firstMockedLoader, this.secondMockedLoader, this.thirdMockedLoader)
        );
    }

    @Test
    public void tablesMetadataShouldBeLoaded() {
        when(this.firstMockedLoader.isAbleToLoad()).thenReturn(false);
        when(this.secondMockedLoader.isAbleToLoad()).thenReturn(true);

        final List<TableMetadata> givenTablesMetadata = List.of(
                mock(TableMetadata.class),
                mock(TableMetadata.class),
                mock(TableMetadata.class)
        );
        when(this.secondMockedLoader.load()).thenReturn(givenTablesMetadata);

        final List<TableMetadata> actual = this.loadingManager.load();
        assertSame(givenTablesMetadata, actual);

        verify(this.thirdMockedLoader, times(0)).isAbleToLoad();
    }

    @Test(expected = TablesMetadataLoadingException.class)
    public void tablesMetadataShouldNotBeLoaded() {
        when(this.firstMockedLoader.isAbleToLoad()).thenReturn(false);
        when(this.secondMockedLoader.isAbleToLoad()).thenReturn(false);
        when(this.thirdMockedLoader.isAbleToLoad()).thenReturn(false);

        this.loadingManager.load();
    }
}
