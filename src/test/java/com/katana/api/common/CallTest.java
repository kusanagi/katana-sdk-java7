package com.katana.api.common;

import com.katana.api.Call;
import com.katana.api.Param;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 22/11/16.
 */
public class CallTest {

    private Call call;

    @Before
    public void setup() {
        this.call = new Call();
    }

    @Test
    public void getName() {
        //SETUP
        String newName = "New name";
        this.call.setName(newName);

        // ACTION
        String name = this.call.getName();

        // EXPECTED
        Assert.assertEquals(name, newName);
    }

    @Test
    public void setName() {
        //SETUP
        String name = "New name";

        // ACTION
        this.call.setName(name);

        // EXPECTED
        Assert.assertEquals(this.call.getName(), name);
    }

    @Test
    public void getVersion() {
        //SETUP
        String newVersion = "New version";
        this.call.setVersion(newVersion);

        // ACTION
        String version = this.call.getVersion();

        // EXPECTED
        Assert.assertEquals(version, newVersion);
    }

    @Test
    public void setVersion() {
        //SETUP
        String version = "Version";

        // ACTION
        this.call.setVersion(version);

        // EXPECTED
        Assert.assertEquals(this.call.getVersion(), version);
    }

    @Test
    public void getAction() {
        //SETUP
        String newAction = "New action";
        this.call.setAction(newAction);

        // ACTION
        String action = this.call.getAction();

        // EXPECTED
        Assert.assertEquals(action, newAction);
    }

    @Test
    public void setAction() {
        //SETUP
        String action = "New action";

        // ACTION
        this.call.setAction(action);

        // EXPECTED
        Assert.assertEquals(this.call.getAction(), action);
    }

    @Test
    public void getParams() {
        //SETUP
        List<Param> newParams = new ArrayList<>();
        this.call.setParams(newParams);

        // ACTION
        List<Param> params = this.call.getParams();

        // EXPECTED
        Assert.assertEquals(params, newParams);
    }

    @Test
    public void setParams() {
        //SETUP
        List<Param> params = new ArrayList<>();

        // ACTION
        this.call.setParams(params);

        // EXPECTED
        Assert.assertEquals(this.call.getParams(), params);
    }

    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Call mockCall1 = podamFactory.manufacturePojoWithFullData(Call.class);
        Call mockCall2 = podamFactory.manufacturePojoWithFullData(Call.class);
        Call mockCall3 = new Call(mockCall1);
        Assert.assertNotEquals(mockCall1, mockCall2);
        Assert.assertNotEquals(mockCall1, new Object());
        Assert.assertEquals(mockCall1, mockCall3);
    }
}