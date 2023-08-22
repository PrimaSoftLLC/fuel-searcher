package by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.start;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.RowFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@FunctionalInterface
public interface StartRowFilter extends RowFilter<List<XWPFTableRow>> {

}
