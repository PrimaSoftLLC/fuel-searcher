package com.aurorasoft.fuelsearcher.model.specification;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class FuelSpecificationTest {

    @Test
    public void tableNameShouldBeFound() {
        final String givenTableName = "table";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .tableName(givenTableName)
                .build();

        final Optional<String> optionalActual = givenSpecification.findTableName();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenTableName, actual);
    }

    @Test
    public void tableNameShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findTableName();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void tractorShouldBeFound() {
        final String givenTractor = "tractor";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .tractor(givenTractor)
                .build();

        final Optional<String> optionalActual = givenSpecification.findTractor();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenTractor, actual);
    }

    @Test
    public void tractorShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findTractor();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void machineryShouldBeFound() {
        final String givenMachinery = "machinery";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .machinery(givenMachinery)
                .build();

        final Optional<String> optionalActual = givenSpecification.findMachinery();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenMachinery, actual);
    }

    @Test
    public void machineryShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findMachinery();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void corpusCountShouldBeFound() {
        final String givenCorpusCount = "corpus-count";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .corpusCount(givenCorpusCount)
                .build();

        final Optional<String> optionalActual = givenSpecification.findCorpusCount();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenCorpusCount, actual);
    }

    @Test
    public void corpusCountShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findCorpusCount();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void ploughingDepthShouldBeFound() {
        final String givenPloughingDepth = "ploughing-depth";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .ploughingDepth(givenPloughingDepth)
                .build();

        final Optional<String> optionalActual = givenSpecification.findPloughingDepth();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenPloughingDepth, actual);
    }

    @Test
    public void ploughingDepthShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findPloughingDepth();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void routingLengthShouldBeFound() {
        final String givenRoutingLength = "routing-length";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .routingLength(givenRoutingLength)
                .build();

        final Optional<String> optionalActual = givenSpecification.findRoutingLength();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenRoutingLength, actual);
    }

    @Test
    public void routingLengthShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findRoutingLength();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void specificResistanceShouldBeFound() {
        final String givenSpecificResistance = "specific-resistance";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .specificResistance(givenSpecificResistance)
                .build();

        final Optional<String> optionalActual = givenSpecification.findSpecificResistance();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenSpecificResistance, actual);
    }

    @Test
    public void specificResistanceShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findSpecificResistance();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void soilTypeShouldBeFound() {
        final String givenSoilType = "soil-type";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .soilType(givenSoilType)
                .build();

        final Optional<String> optionalActual = givenSpecification.findSoilType();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenSoilType, actual);
    }

    @Test
    public void soilTypeShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findSoilType();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void processingDepthShouldBeFound() {
        final String givenProcessingDepth = "processing-depth";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .processingDepth(givenProcessingDepth)
                .build();

        final Optional<String> optionalActual = givenSpecification.findProcessingDepth();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenProcessingDepth, actual);
    }

    @Test
    public void processingDepthShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findProcessingDepth();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void workingWidthShouldBeFound() {
        final String givenWorkingWidth = "working-width";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .workingWidth(givenWorkingWidth)
                .build();

        final Optional<String> optionalActual = givenSpecification.findWorkingWidth();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenWorkingWidth, actual);
    }

    @Test
    public void workingWidthShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findWorkingWidth();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void sowingNormShouldBeFound() {
        final String givenSowingNorm = "sowing-norm";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .sowingNorm(givenSowingNorm)
                .build();

        final Optional<String> optionalActual = givenSpecification.findSowingNorm();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenSowingNorm, actual);
    }

    @Test
    public void sowingNormShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findSowingNorm();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void fertilizerTypeShouldBeFound() {
        final String givenFertilizerType = "fertilizer-type";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .fertilizerType(givenFertilizerType)
                .build();

        final Optional<String> optionalActual = givenSpecification.findFertilizerType();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenFertilizerType, actual);
    }

    @Test
    public void fertilizerTypeShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findFertilizerType();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void chargingMethodAndTransportDistanceShouldBeFound() {
        final String givenChargingMethodAndTransportDistance = "charging-method-and-transport-distance";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .chargingMethodAndTransportDistance(givenChargingMethodAndTransportDistance)
                .build();

        final Optional<String> optionalActual = givenSpecification.findChargingMethodAndTransportDistance();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenChargingMethodAndTransportDistance, actual);
    }

    @Test
    public void chargingMethodAndTransportDistanceShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findChargingMethodAndTransportDistance();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void spreadRateShouldBeFound() {
        final String givenSpreadRate = "spread-rate";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .spreadRate(givenSpreadRate)
                .build();

        final Optional<String> optionalActual = givenSpecification.findSpreadRate();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenSpreadRate, actual);
    }

    @Test
    public void spreadRateShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findSpreadRate();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void roadGroupShouldBeFound() {
        final String givenRoadGroup = "road-group";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .roadGroup(givenRoadGroup)
                .build();

        final Optional<String> optionalActual = givenSpecification.findRoadGroup();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenRoadGroup, actual);
    }

    @Test
    public void roadGroupShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findRoadGroup();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void transportDistanceShouldBeFound() {
        final String givenTransportDistance = "transport-distance";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .transportDistance(givenTransportDistance)
                .build();

        final Optional<String> optionalActual = givenSpecification.findTransportDistance();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenTransportDistance, actual);
    }

    @Test
    public void transportDistanceShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findTransportDistance();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void cargoClassShouldBeFound() {
        final String givenCargoClass = "cargo-class";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .cargoClass(givenCargoClass)
                .build();

        final Optional<String> optionalActual = givenSpecification.findCargoClass();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenCargoClass, actual);
    }

    @Test
    public void cargoClassShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findCargoClass();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void yieldShouldBeFound() {
        final String givenYield = "yield";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .yield(givenYield)
                .build();

        final Optional<String> optionalActual = givenSpecification.findYield();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenYield, actual);
    }

    @Test
    public void yieldShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findYield();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void rowWidthShouldBeFound() {
        final String givenRowWidth = "row-width";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .rowWidth(givenRowWidth)
                .build();

        final Optional<String> optionalActual = givenSpecification.findRowWidth();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenRowWidth, actual);
    }

    @Test
    public void rowWidthShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findRowWidth();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void combineShouldBeFound() {
        final String givenCombine = "combine";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .combine(givenCombine)
                .build();

        final Optional<String> optionalActual = givenSpecification.findCombine();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenCombine, actual);
    }

    @Test
    public void combineShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findCombine();
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void weightRatioGrainToStrawShouldBeFound() {
        final String givenWeightRatioGrainToStraw = "weight-ratio-grain-to-straw";
        final FuelSpecification givenSpecification = FuelSpecification.builder()
                .weightRatioGrainToStraw(givenWeightRatioGrainToStraw)
                .build();

        final Optional<String> optionalActual = givenSpecification.findWeightRatioGrainToStraw();
        assertTrue(optionalActual.isPresent());
        final String actual = optionalActual.get();
        assertEquals(givenWeightRatioGrainToStraw, actual);
    }

    @Test
    public void weightRatioGrainToStrawShouldNotBeFound() {
        final FuelSpecification givenSpecification = FuelSpecification.builder().build();

        final Optional<String> optionalActual = givenSpecification.findWeightRatioGrainToStraw();
        assertTrue(optionalActual.isEmpty());
    }
}
