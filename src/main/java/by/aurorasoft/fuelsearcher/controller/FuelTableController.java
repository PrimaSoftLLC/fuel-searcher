package by.aurorasoft.fuelsearcher.controller;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/fuelTable")
@RequiredArgsConstructor
public class FuelTableController {
    private final FuelDocument document;

    @GetMapping("/tableNames")
    public ResponseEntity<List<String>> findTableNames() {
        final List<String> tableNames = this.findAllTableNames();
        return ok(tableNames);
    }

    private List<String> findAllTableNames() {
        return this.document.tables()
                .stream()
                .map(FuelTable::name)
                .toList();
    }

}
