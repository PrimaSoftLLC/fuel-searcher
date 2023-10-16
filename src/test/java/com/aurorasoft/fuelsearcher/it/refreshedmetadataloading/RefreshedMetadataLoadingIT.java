package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.ExpectedDocumentMetadataProvider;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.util.FileUtil;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.aurorasoft.fuelsearcher.util.FileUtil.isFileExist;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mockStatic;

public final class RefreshedMetadataLoadingIT extends AbstractContextTest {
    private final ExpectedDocumentMetadataProvider expectedMetadataProvider = new ExpectedDocumentMetadataProvider();

    @Autowired
    private List<TableMetadata> loadedTablesMetadata;

    @Value("${metadata.file-path}")
    private String preparedMetadataFilePath;

    @Test
    public void refreshedPropertyMetadataShould() {
        try (final MockedStatic<FileUtil> mockedFileUtil = mockStatic(FileUtil.class)) {
            mockedFileUtil.when(() -> isFileExist(same(this.preparedMetadataFilePath))).thenReturn(false);

            final Set<TableMetadata> actual = new HashSet<>(this.loadedTablesMetadata);
            final Set<TableMetadata> expected = this.expectedMetadataProvider.provide();
            assertEquals(expected, actual);
        }
    }
}
