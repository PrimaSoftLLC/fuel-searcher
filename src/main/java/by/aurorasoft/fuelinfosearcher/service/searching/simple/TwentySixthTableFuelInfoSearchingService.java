//package by.aurorasoft.fuelinfosearcher.service.searching.simple;
//
//import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
//import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public final class TwentySixthTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
//    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";
//    private static final String[] ROUTING_LENGTHS = new String[]{
//            "Менее 150", "151…200", "201…300", "301…400", "401…600", "601…1000", "Более 1000"
//    };
//    private static final int FIRST_FUEL_INFO_OFFSET = 0;
//
//    public TwentySixthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
//        super(fuelDocument, TABLE_NAME, ROUTING_LENGTHS, FIRST_FUEL_INFO_OFFSET);
//    }
//
//    @Override
//    protected Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
//                                                        final FuelInfoSpecification specification) {
//        return Optional.empty();
//    }
//}
