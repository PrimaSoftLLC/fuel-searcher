package by.aurorasoft.fuelinfosearcher.util;

import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findUnitedRowsByContent;

@UtilityClass
public final class FuelDocumentRowFilterUtil {
    private static final int CELL_INDEX_TRACTOR = 1;

    public static Optional<List<XWPFTableRow>> findRowsByTractor(final List<XWPFTableRow> rows,
                                                                 final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_TRACTOR,
                specification,
                FuelInfoSpecificationUtil::extractTractor
        );
    }

}
