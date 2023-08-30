package by.aurorasoft.fuelinfosearcher.functionalinterface.filteringfunction;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@FunctionalInterface
public interface InterimFilteringFunction extends FilteringFunction<List<XWPFTableRow>> {

    default InterimFilteringFunction andThenInterimFilter(final InterimFilteringFunction after) {
        return rows -> after.apply(this.apply(rows));
    }

    default FinalFilteringFunction andThenFinalFilter(final FinalFilteringFunction after) {
        return rows -> after.apply(this.apply(rows));
    }

}
