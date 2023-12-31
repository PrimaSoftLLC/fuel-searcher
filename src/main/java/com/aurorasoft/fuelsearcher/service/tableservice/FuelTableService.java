package com.aurorasoft.fuelsearcher.service.tableservice;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class FuelTableService {
    private final FuelDocument document;

    public List<String> findTableNames() {
        return this.document.tables()
                .stream()
                .map(FuelTable::name)
                .toList();
    }
}
