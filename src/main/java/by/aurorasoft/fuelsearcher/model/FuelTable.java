package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.Builder;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

@Builder
public record FuelTable(String name, List<IBodyElement> elements) implements Translatable {
    @Override
    public String findAlias() {
        return this.name;
    }
}
