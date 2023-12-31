package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.controller.exception.NoSuchEntityException;
import com.aurorasoft.fuelsearcher.controller.exception.NotValidSpecificationException;
import com.aurorasoft.fuelsearcher.model.Fuel;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearchingManager;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingManager;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fuel")
@RequiredArgsConstructor
public final class FuelController {
    private final FuelSearchingManager searchingManager;
    private final SpecificationValidatingManager specificationValidatingManager;

    @GetMapping
    public ResponseEntity<Fuel> findFuel(@RequestParam(name = "tableName") final String tableName,
                                         @RequestParam(name = "tractor", required = false) final String tractor,
                                         @RequestParam(name = "machinery", required = false) final String machinery,
                                         @RequestParam(name = "corpusCount", required = false) final String corpusCount,
                                         @RequestParam(name = "ploughingDepth", required = false) final String ploughingDepth,
                                         @RequestParam(name = "routingLength", required = false) final String routingLength,
                                         @RequestParam(name = "specificResistance", required = false) final String specificResistance,
                                         @RequestParam(name = "soilType", required = false) final String soilType,
                                         @RequestParam(name = "processingDepth", required = false) final String processingDepth,
                                         @RequestParam(name = "workingWidth", required = false) final String workingWidth,
                                         @RequestParam(name = "sowingNorm", required = false) final String sowingNorm,
                                         @RequestParam(name = "fertilizerType", required = false) final String fertilizerType,
                                         @RequestParam(name = "chargingMethodAndTransportDistance", required = false) final String chargingMethodAndTransportDistance,
                                         @RequestParam(name = "spreadRate", required = false) final String spreadRate,
                                         @RequestParam(name = "roadGroup", required = false) final String roadGroup,
                                         @RequestParam(name = "transportDistance", required = false) final String transportDistance,
                                         @RequestParam(name = "cargoClass", required = false) final String cargoClass,
                                         @RequestParam(name = "yield", required = false) final String yield,
                                         @RequestParam(name = "rowWidth", required = false) final String rowWidth,
                                         @RequestParam(name = "combine", required = false) final String combine,
                                         @RequestParam(name = "weightRatioGrainToStraw", required = false) final String weightRatioGrainToStraw) {
        final FuelSpecification specification = FuelSpecification.builder()
                .tableName(tableName)
                .tractor(tractor)
                .machinery(machinery)
                .corpusCount(corpusCount)
                .ploughingDepth(ploughingDepth)
                .routingLength(routingLength)
                .specificResistance(specificResistance)
                .soilType(soilType)
                .processingDepth(processingDepth)
                .workingWidth(workingWidth)
                .sowingNorm(sowingNorm)
                .fertilizerType(fertilizerType)
                .chargingMethodAndTransportDistance(chargingMethodAndTransportDistance)
                .spreadRate(spreadRate)
                .roadGroup(roadGroup)
                .transportDistance(transportDistance)
                .cargoClass(cargoClass)
                .yield(yield)
                .rowWidth(rowWidth)
                .combine(combine)
                .weightRatioGrainToStraw(weightRatioGrainToStraw)
                .build();
        this.validateSpecification(specification);
        return this.searchingManager.find(specification)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchEntityException("Fuel with given properties doesn't exist"));
    }

    private void validateSpecification(final FuelSpecification specification) {
        final SpecificationValidatingResult result = this.specificationValidatingManager.validate(specification);
        if (!result.isValid()) {
            throw new NotValidSpecificationException(result);
        }
    }
}
