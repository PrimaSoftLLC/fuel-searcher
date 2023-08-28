//package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;
//
//import by.aurorasoft.fuelinfosearcher.model.Specification;
//
//import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSoilType;
//
//public final class SoilTypeRowFilter extends GroupFilter {
//    private static final String GROUP_VALUE_REGEX = "(Минеральные почвы)|(Торфяные почвы)|(Легкие почвы)|(Средние почвы)|(Тяжелые почвы)";
//
//    @Override
//    protected String extractFilteringValue(final Specification specification) {
//        return extractSoilType(specification);
//    }
//
//    @Override
//    protected String findGroupValueRegex() {
//        return GROUP_VALUE_REGEX;
//    }
//
//}
