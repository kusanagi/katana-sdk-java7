package com.katana.api.common.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by juan on 3/01/17.
 */
public class ActionParamSchema {

    @JsonProperty("t")
    private String type;

    @JsonProperty("f")
    private String format;

    @JsonProperty("af")
    private String arrayFormat;

    @JsonProperty("p")
    private String pattern;

    @JsonProperty("e")
    private boolean allowEmpty;

    @JsonProperty("d")
    private String defaultValue;

    @JsonProperty("r")
    private boolean required;

    @JsonProperty("i")
    private String items;

    @JsonProperty("mx")
    private int max;

    @JsonProperty("ex")
    private boolean exclusiveMax;

    @JsonProperty("mn")
    private int min;

    @JsonProperty("en")
    private boolean exclusiveMin;

    @JsonProperty("xl")
    private int maxLength;

    @JsonProperty("nl")
    private int minLength;

    @JsonProperty("xi")
    private int maxItems;

    @JsonProperty("ni")
    private int minItems;

    @JsonProperty("ui")
    private int uniqueItems;

    @JsonProperty("em")
    private List<String> _enum;

    @JsonProperty("mo")
    private int multipleOf;

    @JsonProperty("h")
    private ActionParamHttpSchema http;

    public ActionParamSchema() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getArrayFormat() {
        return arrayFormat;
    }

    public void setArrayFormat(String arrayFormat) {
        this.arrayFormat = arrayFormat;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    public void setAllowEmpty(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isExclusiveMax() {
        return exclusiveMax;
    }

    public void setExclusiveMax(boolean exclusiveMax) {
        this.exclusiveMax = exclusiveMax;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public boolean isExclusiveMin() {
        return exclusiveMin;
    }

    public void setExclusiveMin(boolean exclusiveMin) {
        this.exclusiveMin = exclusiveMin;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public int getMinItems() {
        return minItems;
    }

    public void setMinItems(int minItems) {
        this.minItems = minItems;
    }

    public int getUniqueItems() {
        return uniqueItems;
    }

    public void setUniqueItems(int uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public List<String> get_enum() {
        return _enum;
    }

    public void set_enum(List<String> _enum) {
        this._enum = _enum;
    }

    public int getMultipleOf() {
        return multipleOf;
    }

    public void setMultipleOf(int multipleOf) {
        this.multipleOf = multipleOf;
    }

    public ActionParamHttpSchema getHttp() {
        return http;
    }

    public void setHttp(ActionParamHttpSchema http) {
        this.http = http;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionParamSchema)) {
            return false;
        }

        ActionParamSchema that = (ActionParamSchema) o;

        if (isAllowEmpty() != that.isAllowEmpty()) {
            return false;
        }
        if (isRequired() != that.isRequired()) {
            return false;
        }
        if (getMax() != that.getMax()) {
            return false;
        }
        if (isExclusiveMax() != that.isExclusiveMax()) {
            return false;
        }
        if (getMin() != that.getMin()) {
            return false;
        }
        if (isExclusiveMin() != that.isExclusiveMin()) {
            return false;
        }
        if (getMaxLength() != that.getMaxLength()) {
            return false;
        }
        if (getMinLength() != that.getMinLength()) {
            return false;
        }
        if (getMaxItems() != that.getMaxItems()) {
            return false;
        }
        if (getMinItems() != that.getMinItems()) {
            return false;
        }
        if (getUniqueItems() != that.getUniqueItems()) {
            return false;
        }
        if (getMultipleOf() != that.getMultipleOf()) {
            return false;
        }
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) {
            return false;
        }
        if (getFormat() != null ? !getFormat().equals(that.getFormat()) : that.getFormat() != null) {
            return false;
        }
        if (getArrayFormat() != null ? !getArrayFormat().equals(that.getArrayFormat()) : that.getArrayFormat() != null) {
            return false;
        }
        if (getPattern() != null ? !getPattern().equals(that.getPattern()) : that.getPattern() != null) {
            return false;
        }
        if (getDefaultValue() != null ? !getDefaultValue().equals(that.getDefaultValue()) : that.getDefaultValue() != null) {
            return false;
        }
        if (getItems() != null ? !getItems().equals(that.getItems()) : that.getItems() != null) {
            return false;
        }
        if (get_enum() != null ? !get_enum().equals(that.get_enum()) : that.get_enum() != null) {
            return false;
        }
        return getHttp() != null ? getHttp().equals(that.getHttp()) : that.getHttp() == null;

    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getFormat() != null ? getFormat().hashCode() : 0);
        result = 31 * result + (getArrayFormat() != null ? getArrayFormat().hashCode() : 0);
        result = 31 * result + (getPattern() != null ? getPattern().hashCode() : 0);
        result = 31 * result + (isAllowEmpty() ? 1 : 0);
        result = 31 * result + (getDefaultValue() != null ? getDefaultValue().hashCode() : 0);
        result = 31 * result + (isRequired() ? 1 : 0);
        result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
        result = 31 * result + getMax();
        result = 31 * result + (isExclusiveMax() ? 1 : 0);
        result = 31 * result + getMin();
        result = 31 * result + (isExclusiveMin() ? 1 : 0);
        result = 31 * result + getMaxLength();
        result = 31 * result + getMinLength();
        result = 31 * result + getMaxItems();
        result = 31 * result + getMinItems();
        result = 31 * result + getUniqueItems();
        result = 31 * result + (get_enum() != null ? get_enum().hashCode() : 0);
        result = 31 * result + getMultipleOf();
        result = 31 * result + (getHttp() != null ? getHttp().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionParamSchema{" +
                "type='" + type + '\'' +
                ", format='" + format + '\'' +
                ", arrayFormat='" + arrayFormat + '\'' +
                ", pattern='" + pattern + '\'' +
                ", allowEmpty=" + allowEmpty +
                ", defaultValue='" + defaultValue + '\'' +
                ", required=" + required +
                ", items='" + items + '\'' +
                ", max=" + max +
                ", exclusiveMax=" + exclusiveMax +
                ", min=" + min +
                ", exclusiveMin=" + exclusiveMin +
                ", maxLength=" + maxLength +
                ", minLength=" + minLength +
                ", maxItems=" + maxItems +
                ", minItems=" + minItems +
                ", uniqueItems=" + uniqueItems +
                ", _enum=" + _enum +
                ", multipleOf=" + multipleOf +
                ", http=" + http +
                '}';
    }

    public ActionParamSchema(ActionParamSchema other) {
        this.type = other.type;
        this.format = other.format;
        this.arrayFormat = other.arrayFormat;
        this.pattern = other.pattern;
        this.allowEmpty = other.allowEmpty;
        this.defaultValue = other.defaultValue;
        this.required = other.required;
        this.items = other.items;
        this.max = other.max;
        this.exclusiveMax = other.exclusiveMax;
        this.min = other.min;
        this.exclusiveMin = other.exclusiveMin;
        this.maxLength = other.maxLength;
        this.minLength = other.minLength;
        this.maxItems = other.maxItems;
        this.minItems = other.minItems;
        this.uniqueItems = other.uniqueItems;
        this._enum = other._enum;
        this.multipleOf = other.multipleOf;
        this.http = other.http;
    }
}
