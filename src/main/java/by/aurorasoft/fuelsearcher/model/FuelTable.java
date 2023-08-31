package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.dictionary.Translatable;
import lombok.Value;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

@Value
public class FuelTable implements Translatable {
    String name;

    //paragraphs and tables
    //TODO: validate for simle and composite table
    List<IBodyElement> elements;

    @Override
    public String findAlias() {
        return this.name;
    }
}
