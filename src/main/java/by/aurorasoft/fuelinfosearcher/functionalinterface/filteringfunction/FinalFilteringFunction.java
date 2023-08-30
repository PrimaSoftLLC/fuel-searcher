package by.aurorasoft.fuelinfosearcher.functionalinterface.filteringfunction;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Optional;

@FunctionalInterface
public interface FinalFilteringFunction extends FilteringFunction<Optional<XWPFTableRow>> {

}
