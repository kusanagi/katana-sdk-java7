package com.katana.api.common;

import com.katana.api.*;
import com.katana.api.Error;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 12/11/16.
 */
public class TransportTest {

    private Transport transport;

    @Before
    public void setUp() {
        this.transport = new Transport();
    }

    @Test
    public void getMeta() {
        // SETUP
        TransportMeta meta = new TransportMeta();
        this.transport.setMeta(meta);

        // ACTION
        TransportMeta metaObtained = this.transport.getMeta();

        // EXPECTED
        Assert.assertEquals(meta, metaObtained);
    }

    @Test
    public void setMeta() {
        // SETUP
        TransportMeta meta = new TransportMeta();

        // ACTION
        this.transport.setMeta(meta);

        // EXPECTED
        Assert.assertEquals(meta, this.transport.getMeta());
    }

    @Test
    public void getBody() {
        // SETUP
        File body = new File();
        this.transport.setBody(body);

        // ACTION
        File bodyObtained = this.transport.getBody();

        // EXPECTED
        Assert.assertEquals(body, bodyObtained);
    }

    @Test
    public void setBody() {
        // SETUP
        File body = new File();

        // ACTION
        this.transport.setBody(body);

        // EXPECTED
        Assert.assertEquals(body, this.transport.getBody());
    }

    @Test
    public void getFiles() {
        // SETUP
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> files = new HashMap<>();
        this.transport.setFiles(files);

        // ACTION
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> filesObtained = this.transport.getFiles();

        // EXPECTED
        Assert.assertEquals(files, filesObtained);
    }

    @Test
    public void setFiles() {
        // SETUP
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> files = new HashMap<>();

        // ACTION
        this.transport.setFiles(files);

        // EXPECTED
        Assert.assertEquals(files, this.transport.getFiles());
    }

    @Test
    public void getData() {
        // SETUP
        Map<String, Map<String, Map<String, Map<String, Object>>>> data = new HashMap<>();
        this.transport.setData(data);

        // ACTION
        Map<String, Map<String, Map<String, Map<String, Object>>>> dataObtained = this.transport.getData();

        // EXPECTED
        Assert.assertEquals(data, dataObtained);
    }

    @Test
    public void setData() {
        // SETUP
        Map<String, Map<String, Map<String, Map<String, Object>>>> data = new HashMap<>();

        // ACTION
        this.transport.setData(data);

        // EXPECTED
        Assert.assertEquals(data, this.transport.getData());
    }

    @Test
    public void getRelations() {
        // SETUP
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = new HashMap<>();
        this.transport.setRelations(relations);

        // ACTION
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relationsObtained = this.transport.getRelations();

        // EXPECTED
        Assert.assertEquals(relations, relationsObtained);
    }

    @Test
    public void setRelations() {
        // SETUP
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = new HashMap<>();

        // ACTION
        this.transport.setRelations(relations);

        // EXPECTED
        Assert.assertEquals(relations, this.transport.getRelations());
    }

    @Test
    public void getLinks() {
        // SETUP
        Map<String, Map<String, Map<String, String>>> links = new HashMap<>();
        this.transport.setLinks(links);

        // ACTION
        Map<String, Map<String, Map<String, String>>> linksObtained = this.transport.getLinks();

        // EXPECTED
        Assert.assertEquals(links, linksObtained);
    }

    @Test
    public void setLinks() {
        // SETUP
        Map<String, Map<String, Map<String, String>>> links = new HashMap<>();

        // ACTION
        this.transport.setLinks(links);

        // EXPECTED
        Assert.assertEquals(links, this.transport.getLinks());
    }

    @Test
    public void getCalls() {
        // SETUP
        Map<String, Map<String, List<Call>>> requestCalls = new HashMap<>();
        this.transport.setCalls(requestCalls);

        // ACTION
        Map<String, Map<String, List<Call>>> callsObtained = this.transport.getCalls();

        // EXPECTED
        Assert.assertEquals(requestCalls, callsObtained);
    }

    @Test
    public void setCalls() {
        // SETUP
        Map<String, Map<String, List<Call>>> requestCalls = new HashMap<>();

        // ACTION
        this.transport.setCalls(requestCalls);

        // EXPECTED
        Assert.assertEquals(requestCalls, this.transport.getCalls());
    }

    @Test
    public void getTransactions() {
        // SETUP
        Transaction transactions = new Transaction();
        this.transport.setTransactions(transactions);

        // ACTION
        Transaction transactionsObtained = this.transport.getTransactions();

        // EXPECTED
        Assert.assertEquals(transactions, transactionsObtained);
    }

    @Test
    public void setTransactions() {
        // SETUP
        Transaction transactions = new Transaction();

        // ACTION
        this.transport.setTransactions(transactions);

        // EXPECTED
        Assert.assertEquals(transactions, this.transport.getTransactions());
    }

    @Test
    public void getErrors() {
        // SETUP
        Map<String, Map<String, Map<String, List<Error>>>> errors = new HashMap<>();
        this.transport.setErrors(errors);

        // ACTION
        Map<String, Map<String, Map<String, List<Error>>>> errorsObtained = this.transport.getErrors();

        // EXPECTED
        Assert.assertEquals(errors, errorsObtained);
    }

    @Test
    public void setErrors() {
        // SETUP
        Map<String, Map<String, Map<String, List<Error>>>> errors = new HashMap<>();

        // ACTION
        this.transport.setErrors(errors);

        // EXPECTED
        Assert.assertEquals(errors, this.transport.getErrors());
    }

    @Test
    public void getRequestId() {
        Assert.assertTrue(true);
    }

    @Test
    public void getRequestTimeStamp() {
        Assert.assertTrue(true);
    }

    @Test
    public void getOriginService() {
        Assert.assertTrue(true);
    }

    @Test
    public void getProperty() {
        Assert.assertTrue(true);
    }

    @Test
    public void hasDownload() {
        Assert.assertTrue(true);
    }

    @Test
    public void getDownload() {
        Assert.assertTrue(true);
    }

    @Test
    public void getData1() {
        Assert.assertTrue(true);
    }

    @Test
    public void getRelations1() {
        Assert.assertTrue(true);
    }

    @Test
    public void addLink() {
        Assert.assertTrue(true);
    }

    @Test
    public void getLinks1() {
        Assert.assertTrue(true);
    }

    @Test
    public void gettransports1() {
        Assert.assertTrue(true);
    }

    @Test
    public void getTransactions1() {
        Assert.assertTrue(true);
    }

    @Test
    public void getErrors1() {
        Assert.assertTrue(true);
    }

    @Test
    public void addData() {
        Assert.assertTrue(true);
    }

    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Transport mockTransport1 = podamFactory.manufacturePojoWithFullData(Transport.class);
        Transport mockTransport2 = podamFactory.manufacturePojoWithFullData(Transport.class);
        Transport mockTransport3 = new Transport(mockTransport1);
        Assert.assertNotEquals(mockTransport1, mockTransport2);
        Assert.assertNotEquals(mockTransport1, new Object());
        Assert.assertEquals(mockTransport1, mockTransport3);
    }
}