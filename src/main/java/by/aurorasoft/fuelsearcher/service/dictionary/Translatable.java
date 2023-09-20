package by.aurorasoft.fuelsearcher.service.dictionary;

@FunctionalInterface
public interface Translatable<A> {
    A findAlias();
}
