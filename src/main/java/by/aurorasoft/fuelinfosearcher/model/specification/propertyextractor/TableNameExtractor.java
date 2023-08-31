package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public final class TableNameExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "имя таблицы";

    public TableNameExtractor() {
        super(Specification::findTableName, PROPERTY_NAME);
    }

}
