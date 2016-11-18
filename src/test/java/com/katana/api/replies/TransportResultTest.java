package com.katana.api.replies;

import com.katana.api.common.Transport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class TransportResultTest {

    private TransportReplyPayload.TransportResult transportResult;

    @Before
    public void setup() {
        this.transportResult = new TransportReplyPayload.TransportResult();
    }

    @Test
    public void getTransport() {
        // SETUP
        Transport transport = new Transport();
        this.transportResult.setTransport(transport);

        // ACTION
        Transport transportObtained = this.transportResult.getTransport();

        // EXPECTED
        Assert.assertEquals(transport, transportObtained);
    }

    @Test
    public void setTransport() {
        // SETUP
        Transport transport = new Transport();

        // ACTION
        this.transportResult.setTransport(transport);

        // EXPECTED
        Assert.assertEquals(transport, this.transportResult.getTransport());
    }

}