package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.controller.exception.NoSuchEntityException;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.dictionary.TableMetadataDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/tableMetadata")
@RequiredArgsConstructor
public final class TableMetadataController {
    private static final String EXCEPTION_DESCRIPTION_NO_SUCH_METADATA = "There is no metadata for table '%s'";

    private final TableMetadataDictionary metadataDictionary;

    @GetMapping
    public ResponseEntity<TableMetadata> findByTableName(@RequestParam(name = "tableName") final String tableName) {
        final Optional<TableMetadata> optionalMetadata = this.metadataDictionary.find(tableName);
        return optionalMetadata.map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new NoSuchEntityException(
                                EXCEPTION_DESCRIPTION_NO_SUCH_METADATA.formatted(tableName)
                        )
                );
    }
}
