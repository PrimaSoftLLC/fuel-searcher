package by.aurorasoft.fuelinfosearcher.service.searching.filter;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@FunctionalInterface
public interface StartRowFilter extends RowFilter<List<XWPFTableRow>> {

}
