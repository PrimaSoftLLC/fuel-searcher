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
}
