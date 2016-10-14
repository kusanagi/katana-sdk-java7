package com.katana.sdk.common;

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
}
