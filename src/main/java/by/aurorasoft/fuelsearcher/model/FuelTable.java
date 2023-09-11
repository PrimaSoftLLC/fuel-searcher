package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class FuelTable implements Translatable {
    String name;
    List<IBodyElement> elements;

    @Override
    public String findAlias() {
        return this.name;
    }
}
