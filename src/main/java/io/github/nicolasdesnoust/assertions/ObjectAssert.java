package io.github.nicolasdesnoust.assertions;

public class ObjectAssert {
    private static final String MANDATORY = "The field or property \"%s\" is mandatory and wasn't set";
    private final AssertContext<?> context;

    public ObjectAssert(AssertContext<?> context) {
        this.context = context;
    }

    public void notNull() {
        if (context.value() == null) {
            throw AssertException.builder()
                    .withFieldOrProperty(context.fieldOrPropertyName())
                    .withRejectedValue(null)
                    .withReason(MANDATORY, context.fieldOrPropertyName())
                    .build();
        }
    }
}
