package by.aurorasoft.fuelinfosearcher.model;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import lombok.Value;

import java.util.List;

@Value
public class FuelHeaderMetadata {
    String name;
    List<String> values;
    SpecificationPropertyExtractor fuelHeaderValueExtractor;
}
