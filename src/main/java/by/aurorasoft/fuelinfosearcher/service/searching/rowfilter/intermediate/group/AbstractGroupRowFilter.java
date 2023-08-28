package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.IntPair;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Collection;
import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

public abstract class AbstractGroupRowFilter extends AbstractInterimFilter {
    public AbstractGroupRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                        final String filteringValue,
                                        final int filteringCellIndex) {
        return findRowsByGroup(
                rows,
                filteringValue,
                this.findGroupValueRegex(),
                filteringCellIndex
        );
    }

    protected abstract String findGroupValueRegex();

    private static List<XWPFTableRow> findRowsByGroup(final List<XWPFTableRow> rows,
                                                      final String groupValue,
                                                      final String groupValueRegex,
                                                      final int filteringCellIndex) {
        return findRowIndexesByContent(rows, filteringCellIndex, groupValue)
                .map(indexRowGroupValue -> indexRowGroupValue + 1)
                .mapToObj(
                        indexFirstMatchingRow -> findBorderRowIndexesMatchingGroupValue(
                                rows, indexFirstMatchingRow, filteringCellIndex, groupValueRegex
                        )
                )
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .flatMap(Collection::stream)
                .toList();
    }

    private static IntPair findBorderRowIndexesMatchingGroupValue(final List<XWPFTableRow> rows,
                                                                  final int indexFirstMatchingRow,
                                                                  final int filteringCellIndex,
                                                                  final String groupValueRegex) {
        final int nextIndexLastMatchingRow = findIndexRowNextGroupValueOrNextIndexLastRow(
                rows, indexFirstMatchingRow, filteringCellIndex, groupValueRegex
        );
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private static int findIndexRowNextGroupValueOrNextIndexLastRow(final List<XWPFTableRow> rows,
                                                                    final int startSearchingIndex,
                                                                    final int filteringCellIndex,
                                                                    final String groupValueRegex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                filteringCellIndex,
                groupValueRegex
        ).orElse(rows.size());
    }
}
