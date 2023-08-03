package by.aurorasoft.fuelinfosearcher.functionalinterface;

@FunctionalInterface
public interface ThreeArgumentPredicate<F, S, T> {
    boolean test(final F first, final S second, final T third);
}
