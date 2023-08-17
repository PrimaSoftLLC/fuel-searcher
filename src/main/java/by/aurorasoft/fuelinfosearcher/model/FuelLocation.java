package by.aurorasoft.fuelinfosearcher.model;

import lombok.Value;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

@Value
public class FuelLocation {
    XWPFTableRow row;
    int cellIndexGenerationNorm;
    int cellIndexConsumption;
}
