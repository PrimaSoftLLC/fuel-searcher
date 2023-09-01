package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.Value;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

@Value
public class FuelTable implements Translatable {
    String name;
    List<IBodyElement> elements;

    @Override
    public String findAlias() {
        return this.name;
    }
}
