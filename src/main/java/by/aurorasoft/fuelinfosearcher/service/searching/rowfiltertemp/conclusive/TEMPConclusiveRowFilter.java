package by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.conclusive;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.RowFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Optional;

@FunctionalInterface
public interface TEMPConclusiveRowFilter extends RowFilter<Optional<XWPFTableRow>> {

}
