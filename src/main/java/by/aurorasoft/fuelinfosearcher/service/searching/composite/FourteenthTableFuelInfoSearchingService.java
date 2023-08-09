//package by.aurorasoft.fuelinfosearcher.service.searching.composite;
//
//import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
//import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
//import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
//import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//import java.util.stream.Stream;
//
//@Service
//public final class FourteenthTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
//
//    public FourteenthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
//                                                   final FuelDocument fuelDocument) {
//        super(offsetStorage, fuelDocument, fuelTableName);
//    }
//
//    @Override
//    protected Optional<FuelInfo> find(final List<XWPFTableRow> elementTableRows,
//                                      final FuelInfoSpecification specification) {
//        return Optional.empty();
//    }
//
//    @Override
//    protected String findElementTableTitleTemplate() {
//        return null;
//    }
//
//    @Override
//    protected Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
//        return null;
//    }
//}
