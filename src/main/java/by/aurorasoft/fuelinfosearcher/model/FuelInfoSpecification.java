package by.aurorasoft.fuelinfosearcher.model;

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
public class FuelInfoSpecification {
    String tableName;
    String tractor;

    //Марка плуга, сельскохозяйственная машина
    String machinery;
    String corpusCount;
    String ploughingDepth;
    //длина гона
    String routingLength;
    String specificResistance;
    String soilType;

    //Глубина обработки
    String processingDepth;

    //Ширина захвата
    String workingWidth;

    //Норма высева
    String sowingNorm;

    //Тип удобрения
    String fertilizerType;

    //Способ загрузки удобрений и расстояние транспортировки
    String chargingMethodAndTransportDistance;

    //Норма внесения
    String spreadRate;

    //Группа дорог
    String roadGroup;

    //расстояние транспортировки
    String transportDistance;

    //Класс груза
    String cargoClass;

    //Урожайность
    String yield;

    //Ширина междурядий
    String rowWidth;

    //TODO: объединить с трактором
    //Марка комбайна
    String combine;

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
}
