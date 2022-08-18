package io.github.nicolasdesnoust.assertions;

import java.time.Instant;
import java.util.function.Function;

public final class Assert {

    private Assert() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ObjectAssert fieldOrProperty(String fieldOrPropertyName, Object value) {
        AssertContext<Object> context = AssertContext.builder()
                .withFieldOrPropertyName(fieldOrPropertyName)
                .withValue(value)
                .build();

        return new ObjectAssert(context);
    }

    public static InstantAssert fieldOrProperty(String fieldOrPropertyName, Instant value) {
        AssertContext<Instant> context = AssertContext.<Instant>builder()
                .withFieldOrPropertyName(fieldOrPropertyName)
                .withValue(value)
                .build();

        return new InstantAssert(context);
    }

    public static StringAssert fieldOrProperty(String fieldOrPropertyName, String value) {
        AssertContext<String> context = AssertContext.<String>builder()
                .withFieldOrPropertyName(fieldOrPropertyName)
                .withValue(value)
                .build();

        return new StringAssert(context);
    }

    public static <T, U extends BaseAssert<T>> U fieldOrProperty(
            String fieldOrPropertyName,
            T value,
            Function<AssertContext<T>, U> assertSupplier
    ) {
        AssertContext<T> context = AssertContext.<T>builder()
                .withFieldOrPropertyName(fieldOrPropertyName)
                .withValue(value)
                .build();

        return assertSupplier.apply(context);
    }

}
