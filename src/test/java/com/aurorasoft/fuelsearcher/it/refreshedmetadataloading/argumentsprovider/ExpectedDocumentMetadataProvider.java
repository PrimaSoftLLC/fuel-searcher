package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider;

import com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table.*;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class ExpectedDocumentMetadataProvider {
    private final List<ExpectedTableMetadataProvider> tableMetadataProviders = List.of(
            new FirstTablePropertyMetadataViewsProvider(),
            new SecondTablePropertyMetadataViewsProvider(),
            new ThirdTablePropertyMetadataViewsProvider(),
            new FourthTablePropertyMetadataViewsProvider(),
            new FifthTablePropertyMetadataViewsProvider(),
            new SixthTablePropertyMetadataViewsProvider(),
            new SeventhTablePropertyMetadataViewsProvider(),
            new EighthTableMetadataProvider(),
            new NinthTablePropertyMetadataViewsProvider(),
            new TenthTablePropertyMetadataViewsProvider(),
            new EleventhTablePropertyMetadataViewsProvider(),
            new TwelfthTablePropertyMetadataViewsProvider(),
            new ThirteenthTablePropertyMetadataViewsProvider(),
            new FourteenthTablePropertyMetadataViewsProvider(),
            new FifteenthTablePropertyMetadataViewsProvider(),
            new SixteenthTablePropertyMetadataViewsProvider(),
            new SeventeenthTablePropertyMetadataViewsProvider(),
            new EighteenthTableMetadataProvider(),
            new NineteenthTablePropertyMetadataViewsProvider(),
            new TwentiethTablePropertyMetadataViewsProvider(),
            new TwentyFirstTablePropertyMetadataViewsProvider(),
            new TwentySecondTablePropertyMetadataViewsProvider(),
            new TwentyThirdTablePropertyMetadataViewsProvider(),
            new TwentyFourthTablePropertyMetadataViewsProvider(),
            new TwentyFifthTablePropertyMetadataViewsProvider(),
            new TwentySixthTablePropertyMetadataViewsProvider(),
            new TwentySeventhTablePropertyMetadataViewsProvider()
    );

    public Set<TableMetadata> provide() {
        return this.tableMetadataProviders.stream()
                .map(ExpectedTableMetadataProvider::provide)
                .collect(toSet());
    }
}
