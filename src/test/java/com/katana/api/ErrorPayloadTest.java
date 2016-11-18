package com.katana.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ErrorPayloadTest {

    private ErrorPayload errorPayload;

    @Before
    public void setup() {
        this.errorPayload = new ErrorPayload();
    }

    @Test
    public void getError() {
        // SETUP
        Error error = new Error();
        this.errorPayload.setError(error);

        // ACTION
        Error errorObtained = this.errorPayload.getError();

        // EXPECTED
        Assert.assertEquals(error, errorObtained);
    }

    @Test
    public void setError() {
        // SETUP
        Error error = new Error();

        // ACTION
        this.errorPayload.setError(error);

        // EXPECTED
        Assert.assertEquals(error, this.errorPayload.getError());
    }

}