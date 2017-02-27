package com.katana.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ErrorTest {

    private Error error;

    @Before
    public void setup() {
        this.error = new Error();
    }

    @Test
    public void defaultValues() {
        Assert.assertEquals("Unknown error", error.getMessage());
        Assert.assertEquals(0, error.getCode());
        Assert.assertEquals("500 Internal Server Error", error.getStatus());
    }

}