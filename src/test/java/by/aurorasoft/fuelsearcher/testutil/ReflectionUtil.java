package by.aurorasoft.fuelsearcher.testutil;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@UtilityClass
public final class ReflectionUtil {

    public static <T> T createObject(final Class<T> objectType,
                                     final Class<?>[] constructorParameterTypes,
                                     final Object[] constructorParameterValues) {
        try {
            final Constructor<T> constructor = objectType.getDeclaredConstructor(constructorParameterTypes);
            constructor.setAccessible(true);
            try {
                return constructor.newInstance(constructorParameterValues);
            } finally {
                constructor.setAccessible(false);
            }
        } catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }

    public static <T> void setProperty(final T object,
                                       final Object propertyValue,
                                       final Class<? super T> objectType,
                                       final String fieldName) {
        try {
            final Field field = objectType.getDeclaredField(fieldName);
            field.setAccessible(true);
            try {
                field.set(object, propertyValue);
            } finally {
                field.setAccessible(false);
            }
        } catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }

    public static <S, P> P findProperty(final S source,
                                        final Class<? super S> sourceType,
                                        final String fieldName,
                                        final Class<P> propertyType) {
        try {
            final Field field = sourceType.getDeclaredField(fieldName);
            field.setAccessible(true);
            try {
                final Object property = field.get(source);
                return propertyType.cast(property);
            } finally {
                field.setAccessible(false);
            }
        } catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }

}
