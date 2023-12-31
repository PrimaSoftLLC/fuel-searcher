package com.aurorasoft.fuelsearcher.model.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
@Getter(value = NONE)
@Builder
public class FuelSpecification {

    /**
     * имя таблицы
     */
    String tableName;

    /**
     * трактор
     */
    String tractor;

    /**
     * марка плуга, сельскохозяйственная машина
     */
    String machinery;

    /**
     * количество корпусов
     */
    String corpusCount;

    /**
     * глубина вспашки
     */
    String ploughingDepth;

    /**
     * длина гона
     */
    String routingLength;

    /**
     * удельное сопротивление
     */
    String specificResistance;

    /**
     * тип почвы
     */
    String soilType;

    /**
     * глубина обработки
     */
    String processingDepth;

    /**
     * ширина захвата
     */
    String workingWidth;

    /**
     * норма высева
     */
    String sowingNorm;

    /**
     * тип удобрения
     */
    String fertilizerType;

    /**
     * способ загрузки удобрений и расстояние транспортировки
     */
    String chargingMethodAndTransportDistance;

    /**
     * норма внесения
     */
    String spreadRate;

    /**
     * группа дорог
     */
    String roadGroup;

    /**
     * расстояние транспортировки
     */
    String transportDistance;

    /**
     * класс груза
     */
    String cargoClass;

    /**
     * урожайность
     */
    String yield;

    /**
     * ширина междурядий
     */
    String rowWidth;

    /**
     * марка комбайн
     */
    String combine;

    /**
     * соотношение массы зерна к массе соломы
     */
    String weightRatioGrainToStraw;

    public Optional<String> findTableName() {
        return ofNullable(this.tableName);
    }

    public Optional<String> findTractor() {
        return ofNullable(this.tractor);
    }

    public Optional<String> findMachinery() {
        return ofNullable(this.machinery);
    }

    public Optional<String> findCorpusCount() {
        return ofNullable(this.corpusCount);
    }

    public Optional<String> findPloughingDepth() {
        return ofNullable(this.ploughingDepth);
    }

    public Optional<String> findRoutingLength() {
        return ofNullable(this.routingLength);
    }

    public Optional<String> findSpecificResistance() {
        return ofNullable(this.specificResistance);
    }

    public Optional<String> findSoilType() {
        return ofNullable(this.soilType);
    }

    public Optional<String> findProcessingDepth() {
        return ofNullable(this.processingDepth);
    }

    public Optional<String> findWorkingWidth() {
        return ofNullable(this.workingWidth);
    }

    public Optional<String> findSowingNorm() {
        return ofNullable(this.sowingNorm);
    }

    public Optional<String> findFertilizerType() {
        return ofNullable(this.fertilizerType);
    }

    public Optional<String> findChargingMethodAndTransportDistance() {
        return ofNullable(this.chargingMethodAndTransportDistance);
    }

    public Optional<String> findSpreadRate() {
        return ofNullable(this.spreadRate);
    }

    public Optional<String> findRoadGroup() {
        return ofNullable(this.roadGroup);
    }

    public Optional<String> findTransportDistance() {
        return ofNullable(this.transportDistance);
    }

    public Optional<String> findCargoClass() {
        return ofNullable(this.cargoClass);
    }

    public Optional<String> findYield() {
        return ofNullable(this.yield);
    }

    public Optional<String> findRowWidth() {
        return ofNullable(this.rowWidth);
    }

    public Optional<String> findCombine() {
        return ofNullable(this.combine);
    }

    public Optional<String> findWeightRatioGrainToStraw() {
        return ofNullable(this.weightRatioGrainToStraw);
    }
}
