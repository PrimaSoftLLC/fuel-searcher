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

@RestController
@RequestMapping("/tableMetadata")
@RequiredArgsConstructor
public final class TableMetadataController {
    private final TableMetadataDictionary metadataDictionary;

    @GetMapping
    public ResponseEntity<TableMetadata> findByTableName(@RequestParam(name = "tableName") final String tableName) {
        return this.metadataDictionary.find(tableName)
                .map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new NoSuchEntityException(
                                "There is no metadata for table '%s'".formatted(tableName)
                        )
                );
    }
}
