package by.aurorasoft.fuelsearcher.controller;

import by.aurorasoft.fuelsearcher.service.tableservice.FuelTableService;
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
    private final FuelTableService tableService;

    @GetMapping("/tableNames")
    public ResponseEntity<List<String>> findTableNames() {
        final List<String> tableNames = this.tableService.findTableNames();
        return ok(tableNames);
    }
}
