package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider;

import com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table.*;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class DocumentMetadataProvider {
    private final List<TableMetadataProvider> tableMetadataProviders = List.of(
            new FirstTablePropertyMetadataViewsProvider(),
            new SecondTablePropertyMetadataViewsProvider(),
            new ThirdTablePropertyMetadataViewsProvider(),
            new FourthTablePropertyMetadataViewsProvider(),
            new FifthTablePropertyMetadataViewsProvider(),
            new SixthTablePropertyMetadataViewsProvider(),
            new SeventhTablePropertyMetadataViewsProvider(),
            new EighthTablePropertyMetadataViewsProvider(),
            new NinthTablePropertyMetadataViewsProvider(),
            new TenthTablePropertyMetadataViewsProvider(),
            new EleventhTablePropertyMetadataViewsProvider(),
            new TwelfthTablePropertyMetadataViewsProvider(),
            new ThirteenthTablePropertyMetadataViewsProvider(),
            new FourteenthTablePropertyMetadataViewsProvider(),
            new FifteenthTablePropertyMetadataViewsProvider(),
            new SixteenthTablePropertyMetadataViewsProvider(),
            new SeventeenthTablePropertyMetadataViewsProvider(),
            new EighteenthTablePropertyMetadataViewsProvider(),
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
                .map(TableMetadataProvider::provide)
                .collect(toSet());
    }
}
