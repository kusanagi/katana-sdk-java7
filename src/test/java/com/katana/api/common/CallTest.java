package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class CallTest {

    private Call call;

    @Before
    public void setup() {
        this.call = new Call();
    }

    @Test
    public void getService() {
        // SETUP
        String service = "Service";
        this.call.setService(service);

        // ACTION
        String serviceObtained = this.call.getService();

        // EXPECTED
        Assert.assertEquals(service, serviceObtained);
    }

    @Test
    public void setService() {
        // SETUP
        String service = "Service";

        // ACTION
        this.call.setService(service);

        // EXPECTED
        Assert.assertEquals(service, this.call.getService());
    }

    @Test
    public void getVersion() {
        // SETUP
        String version = "Version";
        this.call.setVersion(version);

        // ACTION
        String versionObtained = this.call.getVersion();

        // EXPECTED
        Assert.assertEquals(version, versionObtained);
    }

    @Test
    public void setVersion() {
        // SETUP
        String version = "Version";

        // ACTION
        this.call.setVersion(version);

        // EXPECTED
        Assert.assertEquals(version, this.call.getVersion());
    }

    @Test
    public void getAction() {
        // SETUP
        String action = "Action";
        this.call.setAction(action);

        // ACTION
        String actionObtained = this.call.getAction();

        // EXPECTED
        Assert.assertEquals(action, actionObtained);
    }

    @Test
    public void setAction() {
        // SETUP
        String action = "Action";

        // ACTION
        this.call.setAction(action);

        // EXPECTED
        Assert.assertEquals(action, this.call.getAction());
    }

}