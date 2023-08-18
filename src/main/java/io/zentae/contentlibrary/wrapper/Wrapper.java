package io.zentae.contentlibrary.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Wrapper<T> {

    @JsonValue
    private T value;

    @JsonCreator
    public Wrapper(T parameter) {
        this.value = parameter;
    }

    /**
     * Set the given {@link Object}.
     * @param value the {@link Object} that needs to be stored.
     * @return {@link Wrapper this}.
     */
    public Wrapper<T> setValue(T value) {
        this.value = value;
        return this;
    }

    /**
     * @return the stored {@link Object}.
     */
    public T getValue() {
        return value;
    }

    /**
     * Cast the stored {@link Object} into a super class type.
     * @param eClass the super class type.
     * @param <E> the super type.
     * @return the {@link Object} cast into a super class.
     */
    public <E extends T> E wrap(Class<E> eClass) {
        if(value.getClass() == eClass)
            return eClass.cast(value);
        throw new ClassCastException("The class you provided cannot be used to cast the wrapped element !");
    }

    @Override
    public String toString() {
        return "Wrapper@" + this.hashCode() + "{" +
                "object=" + value +
                "}";
    }
}
