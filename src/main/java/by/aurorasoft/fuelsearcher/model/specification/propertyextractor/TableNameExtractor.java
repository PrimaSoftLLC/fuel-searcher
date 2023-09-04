package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.springframework.stereotype.Component;

@Component
public final class TableNameExtractor extends SpecificationPropertyExtractor {
    private static final String PROPERTY_NAME = "имя таблицы";

    public TableNameExtractor() {
        super(FuelSpecification::findTableName, PROPERTY_NAME);
    }

}
