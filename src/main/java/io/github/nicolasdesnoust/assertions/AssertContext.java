package io.github.nicolasdesnoust.assertions;

public record AssertContext<T>(String fieldOrPropertyName, T value) {

    public static <T> AssertContextBuilder<T> builder() {
        return new AssertContextBuilder<>();
    }

    public static class AssertContextBuilder<T> {
        private String fieldOrPropertyName;
        private T value;

        AssertContextBuilder() {
        }

        public AssertContextBuilder<T> withFieldOrPropertyName(String fieldOrPropertyName) {
            this.fieldOrPropertyName = fieldOrPropertyName;
            return this;
        }

        public AssertContextBuilder<T> withValue(T value) {
            this.value = value;
            return this;
        }

        public AssertContext<T> build() {
            return new AssertContext<>(this.fieldOrPropertyName, this.value);
        }

        public String toString() {
            return "AssertContext.AssertContextBuilder(fieldOrPropertyName=" + this.fieldOrPropertyName + ", value=" + this.value + ")";
        }
    }
}
