package by.aurorasoft.fuelinfosearcher.model;

import lombok.Value;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

@Value
public class FuelTable {
    String name;

    //paragraphs and tables
    List<IBodyElement> elements;
}
