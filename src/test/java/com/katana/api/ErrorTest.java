package com.katana.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ErrorTest {

    private Error error;

    @Before
    public void setup() {
        this.error = new Error();
    }

    @Test
    public void getMessage() {
        // SETUP
        String message = "Message";
        this.error.setMessage(message);

        // ACTION
        String messageObtained = this.error.getMessage();

        // EXPECTED
        Assert.assertEquals(message, messageObtained);
    }

    @Test
    public void setMessage() {
        // SETUP
        String message = "Message";

        // ACTION
        this.error.setMessage(message);

        // EXPECTED
        Assert.assertEquals(message, this.error.getMessage());
    }

    @Test
    public void getCode() {
        // SETUP
        int code = 500;
        this.error.setCode(code);

        // ACTION
        int codeObtained = this.error.getCode();

        // EXPECTED
        Assert.assertEquals(code, codeObtained);
    }

    @Test
    public void setCode() {
        // SETUP
        int code = 500;

        // ACTION
        this.error.setCode(code);

        // EXPECTED
        Assert.assertEquals(code, this.error.getCode());
    }

    @Test
    public void getStatus() {
        // SETUP
        String status = "Status";
        this.error.setStatus(status);

        // ACTION
        String statusObtained = this.error.getStatus();

        // EXPECTED
        Assert.assertEquals(status, statusObtained);
    }

    @Test
    public void setStatus() {
        // SETUP
        String status = "Status";

        // ACTION
        this.error.setStatus(status);

        // EXPECTED
        Assert.assertEquals(status, this.error.getStatus());
    }

}