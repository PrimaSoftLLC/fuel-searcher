package by.aurorasoft.fuelinfosearcher.functionalinterface.filteringfunction;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@FunctionalInterface
public interface InterimFilteringFunction extends FilteringFunction<List<XWPFTableRow>> {

}
