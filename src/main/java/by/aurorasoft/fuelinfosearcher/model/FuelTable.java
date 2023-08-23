package by.aurorasoft.fuelinfosearcher.model;

import lombok.Value;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

@Value
public class FuelTable {
    String name;

    //paragraphs and tables
    //TODO: validate for simle and composite table
    List<IBodyElement> elements;
}
