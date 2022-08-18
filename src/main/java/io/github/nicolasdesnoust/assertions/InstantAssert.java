package io.github.nicolasdesnoust.assertions;

import java.time.Instant;

public class InstantAssert {
    private static final String SHOULD_BE_BEFORE_OTHER_INSTANT = "\"%s\" date \"%s\" should be before \"%s\" date \"%s\"";
    private final AssertContext<Instant> context;

    public InstantAssert(AssertContext<Instant> context) {
        this.context = context;
    }

    public InstantAssert notNull() {
        new ObjectAssert(context).notNull();

        return this;
    }

    public InstantAssert isBefore(String otherFieldOrPropertyName, Instant otherValue) {
        this.notNull();

        AssertContext<Instant> otherContext = AssertContext.<Instant>builder()
                .withFieldOrPropertyName(otherFieldOrPropertyName)
                .withValue(otherValue)
                .build();

        new InstantAssert(otherContext).notNull();

        if (!this.context.value().isBefore(otherValue)) {
            throw AssertException.builder()
                    .withFieldOrProperty(this.context.fieldOrPropertyName())
                    .withRejectedValue(this.context.value())
                    .withReason(SHOULD_BE_BEFORE_OTHER_INSTANT, this.context.fieldOrPropertyName(), this.context.value(), otherFieldOrPropertyName, otherValue)
                    .build();
        }

        return this;
    }

}
