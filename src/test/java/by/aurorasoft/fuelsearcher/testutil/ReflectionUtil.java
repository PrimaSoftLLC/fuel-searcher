package by.aurorasoft.fuelsearcher.testutil;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@UtilityClass
public final class ReflectionUtil {

    public static <T> T createObject(final Class<T> objectType,
                                     final Class<?> constructorParameterTypes,
                                     final Object[] constructorParameterValues)
            throws Exception {
        final Constructor<T> constructor = objectType.getDeclaredConstructor(constructorParameterTypes);
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(constructorParameterValues);
        } finally {
            constructor.setAccessible(false);
        }
    }

    public static <T> void setProperty(final T object,
                                       final Class<? super T> objectType,
                                       final String fieldName)
            throws Exception {
        final Field field = objectType.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            field.set(object, objectType);
        } finally {
            field.setAccessible(false);
        }
    }

    public static <S, P> P findProperty(final S source,
                                        final Class<? super S> sourceType,
                                        final String fieldName,
                                        final Class<P> propertyType)
            throws Exception {
        final Field field = sourceType.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            final Object property = field.get(source);
            return propertyType.cast(property);
        } finally {
            field.setAccessible(false);
        }
    }

}
