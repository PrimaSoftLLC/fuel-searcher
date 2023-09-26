package by.aurorasoft.fuelsearcher.service.searchersparser;

import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@Getter
@Builder
public final class SearchersParsingResult {
    private final List<FuelSearcher> searchers;
    private final List<SpecificationValidator> specificationValidators;

    @Getter(value = NONE)
    @Setter(value = PRIVATE)
    private List<TableMetadata> tablesMetadata;

    public Optional<List<TableMetadata>> takeAwayTablesMetadata() {
        final Optional<List<TableMetadata>> optionalTablesMetadata = ofNullable(this.tablesMetadata);
        optionalTablesMetadata.ifPresent(tableMetadata -> this.setTablesMetadata(null));
        return optionalTablesMetadata;
    }
}
