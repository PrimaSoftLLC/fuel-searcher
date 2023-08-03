package by.aurorasoft.fuelinfosearcher.model;

import lombok.Value;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

@Value
public class FuelInfoLocation {
    XWPFTableRow row;
    int cellIndexGenerationNorm;
    int cellIndexConsumption;
}
