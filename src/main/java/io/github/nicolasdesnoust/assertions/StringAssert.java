package io.github.nicolasdesnoust.assertions;

import java.util.regex.Pattern;

public class StringAssert {
    private static final String MUST_NOT_BE_BLANK = "The field or property \"%s\" must not be blank";
    private static final String LENGTH_MUST_BE_UNDER_MAXIMUM = "Length of \"%s\" must be under %s but was %s";
    private final AssertContext<String> context;

    public StringAssert(AssertContext<String> context) {
        this.context = context;
    }

    public StringAssert notNull() {
        new ObjectAssert(context).notNull();

        return this;
    }

    public StringAssert notBlank() {
        this.notNull();

        if (context.value().isBlank()) {
            throw AssertException.builder()
                    .withFieldOrProperty(context.fieldOrPropertyName())
                    .withRejectedValue(context.value())
                    .withReason(MUST_NOT_BE_BLANK, context.fieldOrPropertyName())
                    .build();
        }

        return this;
    }

    public StringAssert maxLength(int maxLength) {
        if (context.value() != null && context.value().length() > maxLength) {
            throw AssertException.builder()
                    .withFieldOrProperty(context.fieldOrPropertyName())
                    .withRejectedValue(context.value())
                    .withReason(LENGTH_MUST_BE_UNDER_MAXIMUM, context.fieldOrPropertyName(), maxLength, context.value().length())
                    .build();
        }

        return this;
    }

    public StringAssert matchPattern(Pattern regexPattern, String reason, Object... reasonArguments) {
        if (context.value() == null) {
            return this;
        }

        if (!regexPattern.matcher(context.value()).matches()) {
            throw AssertException.builder()
                    .withFieldOrProperty(context.fieldOrPropertyName())
                    .withRejectedValue(context.value())
                    .withReason(reason, reasonArguments)
                    .build();
        }

        return this;
    }

}
