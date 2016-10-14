package com.katana.sdk.common;

import java.util.Arrays;

/**
 * Created by juan on 27/08/16.
 */
public class Option {

    private String[] names;
    private boolean unique;
    private boolean required;
    private boolean hasValue;

    private String value;

    public Option(String[] names, boolean unique, boolean required, boolean hasValue) {
        this.names = names;
        this.unique = unique;
        this.required = required;
        this.hasValue = hasValue;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Option clone() {
        Option option = new Option(
                getNames(),
                isUnique(),
                isRequired(),
                isHasValue()
        );
        option.setValue(getValue());
        return option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option option = (Option) o;

        if (isUnique() != option.isUnique()) return false;
        if (isRequired() != option.isRequired()) return false;
        if (isHasValue() != option.isHasValue()) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getNames(), option.getNames())) return false;
        return getValue().equals(option.getValue());

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(getNames());
        result = 31 * result + (isUnique() ? 1 : 0);
        result = 31 * result + (isRequired() ? 1 : 0);
        result = 31 * result + (isHasValue() ? 1 : 0);
        result = 31 * result + getValue().hashCode();
        return result;
    }
}
