package by.aurorasoft.fuelinfosearcher.model;

import lombok.Value;

import java.util.List;

@Value
public class FuelDocument {
    List<FuelTable> tables;
}
