package by.aurorasoft.fuelsearcher.controller;

import by.aurorasoft.fuelsearcher.controller.exception.NoSuchEntityException;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("tableMetadata")
@RequiredArgsConstructor
public class TableMetadataController {
    private static final String EXCEPTION_DESCRIPTION_NO_SUCH_METADATA = "There is no metadata for table '%s'";

    private final TableMetadataService metadataService;

    @GetMapping
    public ResponseEntity<TableMetadata> findByTableName(@RequestParam(name = "tableName") final String tableName) {
        final Optional<TableMetadata> optionalMetadata = this.metadataService.findByTableName(tableName);
        return optionalMetadata.map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new NoSuchEntityException(
                                EXCEPTION_DESCRIPTION_NO_SUCH_METADATA.formatted(tableName)
                        )
                );
    }
}
