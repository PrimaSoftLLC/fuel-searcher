package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.IntPair;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.AbstractInterimFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Collection;
import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

public abstract class GroupFilter extends AbstractInterimFilter {
    public GroupFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                        final String filtrationCellIndex,
                                        final int filteringCellIndex) {
        return findRowsByGroup(
                rows,
                filtrationCellIndex,
                this.findGroupValueRegex(),
                filteringCellIndex
        );
    }

    protected abstract String findGroupValueRegex();

    private static List<XWPFTableRow> findRowsByGroup(final List<XWPFTableRow> rows,
                                                      final String groupValue,
                                                      final String groupValueRegex,
                                                      final int filtrationCellIndex) {
        return findRowIndexesByContent(rows, filtrationCellIndex, groupValue)
                .map(indexRowGroupValue -> indexRowGroupValue + 1)
                .mapToObj(
                        indexFirstMatchingRow -> findBorderRowIndexesMatchingGroupValue(
                                rows, indexFirstMatchingRow, filtrationCellIndex, groupValueRegex
                        )
                )
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .flatMap(Collection::stream)
                .toList();
    }

    private static IntPair findBorderRowIndexesMatchingGroupValue(final List<XWPFTableRow> rows,
                                                                  final int indexFirstMatchingRow,
                                                                  final int filtrationCellIndex,
                                                                  final String groupValueRegex) {
        final int nextIndexLastMatchingRow = findIndexRowNextGroupValueOrNextIndexLastRow(
                rows, indexFirstMatchingRow, filtrationCellIndex, groupValueRegex
        );
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private static int findIndexRowNextGroupValueOrNextIndexLastRow(final List<XWPFTableRow> rows,
                                                                    final int startSearchingIndex,
                                                                    final int filtrationCellIndex,
                                                                    final String groupValueRegex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                filtrationCellIndex,
                groupValueRegex
        ).orElse(rows.size());
    }
}
