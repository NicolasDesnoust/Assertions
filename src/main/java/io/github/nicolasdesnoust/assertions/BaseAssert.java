package io.github.nicolasdesnoust.assertions;

public abstract class BaseAssert<T> {

    protected final AssertContext<T> context;

    protected BaseAssert(AssertContext<T> context) {
        this.context = context;
    }

    public BaseAssert<T> notNull() {
        new ObjectAssert(context).notNull();

        return this;
    }
}
