package by.aurorasoft.fuelsearcher.testutil;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Stream.iterate;

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
                                        final String fieldName,
                                        final Class<P> propertyType) {
        try {
            final Field field = findFields(source, fieldName);
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

    private static Field findFields(final Object source, final String fieldName) {
        final Class<?> sourceType = source.getClass();
        return findInheritanceStream(sourceType)
                .flatMap(ReflectionUtil::findDeclaredFields)
                .filter(field -> Objects.equals(field.getName(), fieldName))
                .findAny()
                .orElseThrow(
                        () -> new NoSuchFieldException(
                                "%s doesn't have field %s".formatted(sourceType, fieldName)
                        )
                );
    }

    private static Stream<Class<?>> findInheritanceStream(final Class<?> root) {
        return iterate(
                root,
                type -> type != Object.class,
                Class::getSuperclass
        );
    }

    private static Stream<Field> findDeclaredFields(final Class<?> type) {
        final Field[] fields = type.getDeclaredFields();
        return stream(fields);
    }

    private static final class NoSuchFieldException extends RuntimeException {

        @SuppressWarnings("unused")
        public NoSuchFieldException() {

        }

        public NoSuchFieldException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public NoSuchFieldException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuchFieldException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
