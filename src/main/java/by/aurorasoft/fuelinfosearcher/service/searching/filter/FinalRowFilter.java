package by.aurorasoft.fuelinfosearcher.service.searching.filter;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Optional;

@FunctionalInterface
public interface FinalRowFilter extends RowFilter<Optional<XWPFTableRow>> {

}
