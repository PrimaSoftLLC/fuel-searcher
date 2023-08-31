package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import by.aurorasoft.fuelsearcher.model.IntPair;
import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Collection;
import java.util.List;

import static by.aurorasoft.fuelsearcher.util.XWPFUtil.*;

public abstract class GroupFilter extends InterimFilter {

    public GroupFilter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor, filtrationCellIndex);
    }

    @Override
    protected List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                        final String groupValue,
                                        final int groupValueCellIndex) {
        final String groupValueRegex = this.findGroupValueRegex();
        return findRowsByGroup(
                rows,
                groupValue,
                groupValueRegex,
                groupValueCellIndex
        );
    }

    protected abstract String findGroupValueRegex();

    private static List<XWPFTableRow> findRowsByGroup(final List<XWPFTableRow> rows,
                                                      final String groupValue,
                                                      final String groupValueRegex,
                                                      final int groupValueCellIndex) {
        return findRowIndexesByContent(rows, groupValueCellIndex, groupValue)
                .map(indexRowGroupValue -> indexRowGroupValue + 1)
                .mapToObj(
                        indexFirstMatchingRow -> findBorderRowIndexesMatchingGroupValue(
                                rows, indexFirstMatchingRow, groupValueCellIndex, groupValueRegex
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
        final int nextIndexLastRow = rows.size();
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                filtrationCellIndex,
                groupValueRegex
        ).orElse(nextIndexLastRow);
    }
}
