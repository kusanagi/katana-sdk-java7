package com.katana.api.common.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 3/01/17.
 */
public class ValueSchema {

    @JsonProperty("t")
    private String type;

    @JsonProperty("v")
    private Object value;
}
