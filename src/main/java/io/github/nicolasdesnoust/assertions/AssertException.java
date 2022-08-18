package io.github.nicolasdesnoust.assertions;

import java.io.Serializable;

public class AssertException extends RuntimeException {
    private final String fieldOrProperty;
    private final Serializable rejectedValue;
    private final String reason;

    private AssertException(String fieldOrProperty, Serializable rejectedValue, String reason) {
        super(reason);
        this.fieldOrProperty = fieldOrProperty;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
    }

    public static AssertExceptionBuilder builder() {
        return new AssertExceptionBuilder();
    }

    public static class AssertExceptionBuilder {
        private String fieldOrProperty;
        private Serializable rejectedValue;
        private String reason;

        AssertExceptionBuilder() {
        }

        public AssertExceptionBuilder withFieldOrProperty(String fieldOrProperty) {
            this.fieldOrProperty = fieldOrProperty;
            return this;
        }

        public AssertExceptionBuilder withRejectedValue(Serializable rejectedValue) {
            this.rejectedValue = rejectedValue;
            return this;
        }

        public AssertExceptionBuilder withReason(String reasonTemplate, Object... arguments) {
            this.reason = String.format(reasonTemplate, arguments);
            return this;
        }

        public AssertException build() {
            return new AssertException(this.fieldOrProperty, this.rejectedValue, this.reason);
        }
    }

    public String getFieldOrProperty() {
        return fieldOrProperty;
    }

    public Serializable getRejectedValue() {
        return rejectedValue;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "AssertException(fieldOrProperty=" + this.getFieldOrProperty() + ", rejectedValue=" + this.getRejectedValue() + ", reason=" + this.getReason() + ")";
    }
}
