package com.github.mcanessa.validraptor;

public interface Validator<T> {
    public void validate(T target);
}
