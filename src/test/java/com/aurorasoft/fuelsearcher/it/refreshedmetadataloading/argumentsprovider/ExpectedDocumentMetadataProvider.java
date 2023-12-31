package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider;

import com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table.*;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class ExpectedDocumentMetadataProvider {
    private final List<ExpectedTableMetadataProvider> tableMetadataProviders = List.of(
            new ExpectedFirstTableMetadataProvider(),
            new ExpectedSecondTableMetadataProvider(),
            new ExpectedThirdTableMetadataProvider(),
            new ExpectedFourthTableMetadataProvider(),
            new ExpectedFifthTableMetadataProvider(),
            new ExpectedSixthTableMetadataProvider(),
            new ExpectedSeventhTableMetadataProvider(),
            new ExpectedEighthTableMetadataProvider(),
            new ExpectedNinthTableMetadataProvider(),
            new ExpectedTenthTableMetadataProvider(),
            new ExpectedEleventhTableMetadataProvider(),
            new ExpectedTwelfthTableMetadataProvider(),
            new ExpectedThirteenthTableMetadataProvider(),
            new ExpectedFourteenthTableMetadataProvider(),
            new ExpectedFifteenthTableMetadataProvider(),
            new ExpectedSixteenthTableMetadataProvider(),
            new ExpectedSeventeenthTableMetadataProvider(),
            new ExpectedEighteenthTableMetadataProvider(),
            new ExpectedNineteenthTableMetadataProvider(),
            new ExpectedTwentiethTableMetadataProvider(),
            new ExpectedTwentyFirstTableMetadataProvider(),
            new ExpectedTwentySecondTableMetadataProvider(),
            new ExpectedTwentyThirdTableMetadataProvider(),
            new ExpectedTwentyFourthTableMetadataProvider(),
            new ExpectedTwentyFifthTableMetadataProvider(),
            new ExpectedTwentySixthTableMetadataProvider(),
            new ExpectedTwentySeventhTableMetadataProvider()
    );

    public Set<TableMetadata> provide() {
        return this.tableMetadataProviders.stream()
                .map(ExpectedTableMetadataProvider::provide)
                .collect(toSet());
    }
}
