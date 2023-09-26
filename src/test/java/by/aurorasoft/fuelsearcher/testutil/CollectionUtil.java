package by.aurorasoft.fuelsearcher.testutil;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@UtilityClass
public final class CollectionUtil {

    public static boolean isImmutableList(final List<?> research) {
        final List<?> tempImmutableList = unmodifiableList(new ArrayList<>());
        final Class<?> typeImmutableList = tempImmutableList.getClass();
        return typeImmutableList.isInstance(research);
    }

}
