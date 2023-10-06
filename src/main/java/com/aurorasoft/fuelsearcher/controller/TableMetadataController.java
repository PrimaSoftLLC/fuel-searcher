package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.controller.exception.NoSuchEntityException;
import com.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import com.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
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
public class TableMetadataController {
    private static final String EXCEPTION_DESCRIPTION_NO_SUCH_METADATA = "There is no metadata for table '%s'";

    //TODO: tableMetadataDictionary
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
