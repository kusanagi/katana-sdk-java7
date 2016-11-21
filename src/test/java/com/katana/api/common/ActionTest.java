package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 11/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActionTest {

    private Action action;

    @Before
    public void setup() {
        action = new Action();
    }

    @Test
    public void setActionName() {
        //SETUP
        String actionName = "New Action Name";

        // ACTION
        this.action.setActionName(actionName);

        // EXPECTED
        Assert.assertEquals(this.action.getActionName(), actionName);
    }

    @Test
    public void getParams() {
        //SETUP
        Map<String, Map<String, Map<String, String>>> newParams = new HashMap<>();
        this.action.setParams(newParams);

        // ACTION
        Map<String, Map<String, Map<String, String>>> params = this.action.getParams();

        // EXPECTED
        Assert.assertEquals(params, newParams);
    }

    @Test
    public void setParams() {
        //SETUP
        Map<String, Map<String, Map<String, String>>> newParams = new HashMap<>();

        // ACTION
        this.action.setParams(newParams);

        // EXPECTED
        Assert.assertEquals(this.action.getParams(), newParams);
    }

    @Test
    public void getTransport() {
        //SETUP
        Transport newTransport = new Transport();
        this.action.setTransport(newTransport);

        // ACTION
        Transport transport = this.action.getTransport();

        // EXPECTED
        Assert.assertEquals(transport, newTransport);
    }

    @Test
    public void setTransport() {
        //SETUP
        Transport newTransport = new Transport();

        // ACTION
        this.action.setTransport(newTransport);

        // EXPECTED
        Assert.assertEquals(this.action.getTransport(), newTransport);
    }

    @Test
    public void isOrigin_isOrigin_True() {
        //SETUP
        String originService = "Origin Service";
        String[] origin = {originService};

        Transport transport = Mockito.mock(Transport.class);

        Mockito.when(transport.getOriginService()).thenReturn(origin);

        this.action.setName(originService);
        this.action.setTransport(transport);

        // ACTION EXPECTED
        Assert.assertTrue(this.action.isOrigin());
    }

    @Test
    public void isOrigin_isNotOrigin_False() {
        //SETUP
        String originService = "Origin Service";
        String serviceName = "Other service";
        String[] origin = {originService};

        Transport transport = Mockito.mock(Transport.class);

        Mockito.when(transport.getOriginService()).thenReturn(origin);

        this.action.setName(serviceName);
        this.action.setTransport(transport);

        // ACTION EXPECTED
        Assert.assertTrue(!this.action.isOrigin());
    }

    @Test
    public void getActionName() {
        // SETUP
        String actionName = "Action name";
        this.action.setActionName(actionName);

        // ACTION
        String newActionName = this.action.getActionName();

        // EXPECTED
        Assert.assertEquals(actionName, newActionName);
    }

    @Test
    public void setProperty() {
        // SETUP
        Transport transport = Mockito.mock(Transport.class);
        TransportMeta transportMeta = Mockito.mock(TransportMeta.class);
        Map<String, String> properties = Mockito.spy(new HashMap<>());

        Mockito.when(transport.getMeta()).thenReturn(transportMeta);
        Mockito.when(transportMeta.getProperties()).thenReturn(properties);

        this.action.setTransport(transport);

        String propertyName = "name";
        String propertyValue = "value";

        // ACTION
        this.action.setProperty(propertyName, propertyValue);

        // EXPECTED
        Mockito.verify(properties).put(propertyName, propertyValue);
    }

    @Test
    public void hasParam_hasParam_true() {
        // SETUP
        String location = "location";
        String name = "name";
        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();
        Map<String, Map<String, String>> params = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        params.put(name, param);
        locations.put(location, params);
        this.action.setParams(locations);

        // ACTION EXPECTED
        Assert.assertTrue(this.action.hasParam(location, name));
    }

    @Test
    public void hasParam_doesNotHasParam_false() {
        // SETUP
        String location = "location";
        String name = "name";
        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();
        Map<String, Map<String, String>> params = new HashMap<>();
        locations.put(location, params);
        this.action.setParams(locations);

        // ACTION EXPECTED
        Assert.assertTrue(!this.action.hasParam(location, name));
    }

    @Test
    public void hasParam_nullLocation_locationDefaultsToQuery() {
        // SETUP
        String location = "query";
        String name = "name";
        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();
        Map<String, Map<String, String>> params = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        params.put(name, param);
        locations.put(location, params);
        this.action.setParams(locations);

        // ACTION EXPECTED
        Assert.assertTrue(this.action.hasParam(null, name));
    }

    @Test
    public void getParam() {
        // SETUP
        String name = "name";
        String location = "location";

        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();
        Map<String, Map<String, String>> params = new HashMap<>();
        Map<String, String> param = new HashMap<>();

        params.put(name, param);
        locations.put(location, params);

        this.action.setParams(locations);

        // ACTION
        Map<String, String> paramObtained = this.action.getParam(location, name);

        //EXPECTED
        Assert.assertEquals(paramObtained, param);
    }

    @Test
    public void getParam_nullLocation_locationDefaultsToQuery() {
        // SETUP
        String location = "query";
        String name = "name";

        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();
        Map<String, Map<String, String>> params = new HashMap<>();
        Map<String, String> param = new HashMap<>();

        params.put(name, param);
        locations.put(location, params);

        this.action.setParams(locations);

        // ACTION
        Map<String, String> paramObtained = this.action.getParam(null, name);

        //EXPECTED
        Assert.assertEquals(paramObtained, param);
    }

    @Test
    public void getParams_hasParamWithLocation_paramsAtLocation() {
        // SETUP
        String location = "location";

        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();
        Map<String, Map<String, String>> params = new HashMap<>();

        locations.put(location, params);

        this.action.setParams(locations);

        // ACTION
        Map<String, Map<String, String>> paramsObtained = this.action.getParams(location);

        // EXPECTED
        Assert.assertEquals(params, paramsObtained);
    }

    @Test
    public void getParams_nullLocation_locationDefaultsToQuery() {
        // SETUP
        String location = "query";

        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();
        Map<String, Map<String, String>> params = new HashMap<>();

        locations.put(location, params);

        this.action.setParams(locations);

        // ACTION
        Map<String, Map<String, String>> paramsObtained = this.action.getParams(null);

        // EXPECTED
        Assert.assertEquals(params, paramsObtained);
    }

    @Test
    public void newParam() {
        //SETUP
        String name = "name";
        String location = "location";
        String paramValue = "value";

        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();

        this.action.setParams(locations);

        //ACTION
        Map<String, String> paramObtained = this.action.newParam(location, name, paramValue, null);

        // EXPECTED
        Assert.assertEquals(this.action.getParams().get(location).get(name).get("v"), paramValue);
        Assert.assertEquals(paramObtained.get("v"), paramValue);
    }

    @Test
    public void newParam_nullLocation_locationDefaultsToQuery() {
        //SETUP
        String name = "name";
        String location = "query";
        String paramValue = "value";

        Map<String, Map<String, Map<String, String>>> locations = new HashMap<>();

        this.action.setParams(locations);

        //ACTION
        Map<String, String> paramObtained = this.action.newParam(null, name, paramValue, null);

        // EXPECTED
        Assert.assertEquals(this.action.getParams().get(location).get(name).get("v"), paramValue);
        Assert.assertEquals(paramObtained.get("v"), paramValue);
    }

    @Test
    public void hasFile_hasFile_true() {
        //SETUP
        Transport transport = Mockito.mock(Transport.class);

        File file = new File();
        String fileName = "File name";
        file.setName(fileName);
        Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = new HashMap<>();
        Map<String, Map<String, Map<String, File>>> versionFiles = new HashMap<>();
        Map<String, Map<String, File>> actionFiles = new HashMap<>();
        Map<String, File> nameFiles = new HashMap<>();

        nameFiles.put(fileName, file);
        actionFiles.put("action", nameFiles);
        versionFiles.put("version", actionFiles);
        serviceFiles.put("service", versionFiles);

        Mockito.when(transport.getFiles()).thenReturn(serviceFiles);

        this.action.setTransport(transport);

        // ACTION EXPECTED
        Assert.assertTrue(this.action.hasFile(fileName));
    }

    @Test
    public void hasFile_doesNotHasFile_false() {
        //SETUP
        Transport transport = Mockito.mock(Transport.class);

        String fileName = "File name";
        Map<String, Map<String, Map<String, Map<String, File>>>> files = new HashMap<>();

        Mockito.when(transport.getFiles()).thenReturn(files);

        this.action.setTransport(transport);

        // ACTION EXPECTED
        Assert.assertTrue(!this.action.hasFile(fileName));
    }

    @Test
    public void getFile_fileExists_file() {
        //SETUP
        Transport transport = Mockito.mock(Transport.class);

        File file = new File();
        String fileName = "File name";
        file.setName(fileName);

        Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = new HashMap<>();
        Map<String, Map<String, Map<String, File>>> versionFiles = new HashMap<>();
        Map<String, Map<String, File>> actionFiles = new HashMap<>();
        Map<String, File> nameFiles = new HashMap<>();

        nameFiles.put(fileName, file);
        actionFiles.put("action", nameFiles);
        versionFiles.put("version", actionFiles);
        serviceFiles.put("service", versionFiles);

        Mockito.when(transport.getFiles()).thenReturn(serviceFiles);

        this.action.setTransport(transport);

        // ACTION
        File fileObtained = this.action.getFile(fileName);

        // EXPECTED
        Assert.assertEquals(file, fileObtained);
    }

    @Test
    public void getFile_fileDoesNotExists_fileWithTheSameNameAndEmptyPath() {
        //SETUP
        Transport transport = Mockito.mock(Transport.class);

        String fileName = "File name";
        Map<String, Map<String, Map<String, Map<String, File>>>> files = new HashMap<>();

        Mockito.when(transport.getFiles()).thenReturn(files);

        this.action.setTransport(transport);

        // ACTION
        File fileObtained = this.action.getFile(fileName);

        // EXPECTED
        Assert.assertEquals(fileName, fileObtained.getName());
        //Assert.assertEquals("", fileObtained.getPath()); // TODO path
    }

    @Test
    public void getFiles() {
        //SETUP
        Transport transport = Mockito.mock(Transport.class);

        File file1 = new File();
        File file2 = new File();
        File file3 = new File();
        Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = new HashMap<>();
        Map<String, Map<String, Map<String, File>>> versionFiles = new HashMap<>();
        Map<String, Map<String, File>> actionFiles = new HashMap<>();
        Map<String, File> nameFiles = new HashMap<>();

        nameFiles.put("name1", file1);
        nameFiles.put("name2", file2);
        nameFiles.put("name3", file3);
        actionFiles.put("action", nameFiles);
        versionFiles.put("version", actionFiles);
        serviceFiles.put("service", versionFiles);

        Mockito.when(transport.getFiles()).thenReturn(serviceFiles);

        this.action.setTransport(transport);

        // ACTION
        List<File> filesObtained = this.action.getFiles();

        // EXPECTED
        Assert.assertEquals(3, filesObtained.size());
        Assert.assertEquals(file1, filesObtained.get(0));
        Assert.assertEquals(file2, filesObtained.get(1));
        Assert.assertEquals(file3, filesObtained.get(2));
    }

    @Test
    public void getFiles_noFiles_emptyListOfFiles() {
        //SETUP
        Transport transport = Mockito.mock(Transport.class);

        Map<String, Map<String, Map<String, Map<String, File>>>> files = new HashMap<>();

        Mockito.when(transport.getFiles()).thenReturn(files);

        this.action.setTransport(transport);

        // ACTION
        List<File> filesObtained = this.action.getFiles();

        // EXPECTED
        Assert.assertTrue(filesObtained.isEmpty());
    }

    @Test
    public void newFile() {
        //SETUP
        Transport transport = Mockito.mock(Transport.class);

        File file = new File();
        String fileName = "File name";
        file.setName(fileName);
        Map<String, Map<String, Map<String, Map<String, File>>>> files = Mockito.spy(new HashMap<String, Map<String, Map<String, Map<String, File>>>>());

        Mockito.when(transport.getFiles()).thenReturn(files);

        this.action.setTransport(transport);

        // ACTION
        String newFileName = "New file name";
        String newPath = "New path";
        String newMime = "New mime";
        File fileObtained = this.action.newFile(newFileName, newPath, newMime);

        // EXPECTED
        Assert.assertEquals(this.action.getFile(newFileName), fileObtained);
        Assert.assertEquals(newFileName, fileObtained.getName());
    }

    @Test
    public void setEntity() {
        // SETUP
        Object entity = new Object();
        String serviceName = "Service name";
        String serviceVersion = "Service version";
        String actionName = "Action name";

        Transport transport = Mockito.mock(Transport.class);

        this.action.setName(serviceName);
        this.action.setVersion(serviceVersion);
        this.action.setActionName(actionName);
        this.action.setTransport(transport);

        // ACTION
        this.action.setEntity(entity);

        // EXPECTED
        Mockito.verify(transport).addData(serviceName, serviceVersion, actionName, entity);
    }

    @Test
    public void setCollection() {
        // SETUP
        Object entity = new Object();
        String serviceName = "Service name";
        String serviceVersion = "Service version";
        String actionName = "Action name";

        Transport transport = Mockito.mock(Transport.class);

        this.action.setName(serviceName);
        this.action.setVersion(serviceVersion);
        this.action.setActionName(actionName);
        this.action.setTransport(transport);

        // ACTION
        this.action.setEntity(entity);

        // EXPECTED
        Mockito.verify(transport).addData(serviceName, serviceVersion, actionName, entity);
    }

    @Test
    public void setLink() {
        // SETUP
        String link = "Link name";
        String uri = "uri";
        String serviceName = "Service name";

        Transport transport = Mockito.mock(Transport.class);

        this.action.setName(serviceName);
        this.action.setTransport(transport);

        // ACTION
        this.action.setLink(link, uri);

        // EXPECTED
        Mockito.verify(transport).addLink(serviceName, link, uri);
    }

    @Test
    public void error() {
        // SETUP
        String message = "message";
        int code = 500;
        String status = "500 message";

        String service = "service";
        String version = "version";

        Transport transport = Mockito.mock(Transport.class);
        List<Error> errors = new ArrayList<>();

        Map<String, Map<String, List<Error>>> serviceErrors = new HashMap<>();
        Map<String, List<Error>> versionErrors = new HashMap<>();
        serviceErrors.put(service, versionErrors);
        versionErrors.put(version, errors);

        Mockito.when(transport.getErrors()).thenReturn(serviceErrors);

        this.action.setTransport(transport);
        this.action.setName(service);
        this.action.setVersion(version);

        // ACTION
        this.action.error(message, code, status);

        // EXPECTED
        Assert.assertEquals(message, errors.get(0).getMessage());
        Assert.assertEquals(code, errors.get(0).getCode());
        Assert.assertEquals(status, errors.get(0).getStatus());
    }

}