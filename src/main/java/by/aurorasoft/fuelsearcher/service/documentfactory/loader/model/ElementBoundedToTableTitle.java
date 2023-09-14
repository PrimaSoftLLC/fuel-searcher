package by.aurorasoft.fuelsearcher.service.documentfactory.loader.model;

import lombok.Value;
import org.apache.poi.xwpf.usermodel.IBodyElement;

@Value
public class ElementBoundedToTableTitle {
    String tableName;
    IBodyElement element;
}
