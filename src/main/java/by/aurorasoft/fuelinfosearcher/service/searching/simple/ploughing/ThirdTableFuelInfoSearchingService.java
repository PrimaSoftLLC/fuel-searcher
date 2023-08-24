//package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;
//
//import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.SoilTypeRowFilter;
//import org.springframework.stereotype.Service;
//
//@Service
//public final class ThirdTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingService {
//    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";
//
//    public ThirdTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
//        super(fuelDocument, TABLE_NAME);
//    }
//
//    @Override
//    protected AbstractGroupRowFilter createGroupRowFilter() {
//        return new SoilTypeRowFilter();
//    }
//}
