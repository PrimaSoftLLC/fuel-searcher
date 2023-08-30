package by.aurorasoft.fuelinfosearcher.functionalinterface.filteringfunction;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface FilteringFunction<R> extends Function<List<XWPFTableRow>, R> {

}
