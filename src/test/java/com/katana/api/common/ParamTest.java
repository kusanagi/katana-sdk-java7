package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by juan on 22/11/16.
 */
public class ParamTest {

    private Param param;

    @Before
    public void setup() {
        this.param = new Param();
    }

    @Test
    public void getName() {
        //SETUP
        String newName = "New name";
        this.param.setName(newName);

        // ACTION
        String name = this.param.getName();

        // EXPECTED
        Assert.assertEquals(name, newName);
    }

    @Test
    public void setName() {
        //SETUP
        String name = "New name";

        // ACTION
        this.param.setName(name);

        // EXPECTED
        Assert.assertEquals(this.param.getName(), name);
    }

    @Test
    public void getValue() {
        //SETUP
        String newValue = "New value";
        this.param.setValue(newValue);

        // ACTION
        String value = (String) this.param.getValue();

        // EXPECTED
        Assert.assertEquals(value, newValue);
    }

    @Test
    public void setValue() {
        //SETUP
        String value = "New value";

        // ACTION
        this.param.setValue(value);

        // EXPECTED
        Assert.assertEquals(this.param.getValue(), value);
    }

    @Test
    public void getType() {
        //SETUP
        String newType = "New type";
        this.param.setType(newType);

        // ACTION
        String type = this.param.getType();

        // EXPECTED
        Assert.assertEquals(type, newType);
    }

    @Test
    public void setType() {
        //SETUP
        String type = "New type";

        // ACTION
        this.param.setType(type);

        // EXPECTED
        Assert.assertEquals(this.param.getType(), type);
    }


    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Param mockParam1 = podamFactory.manufacturePojoWithFullData(Param.class);
        Param mockParam2 = podamFactory.manufacturePojoWithFullData(Param.class);
        Param mockParam3 = new Param(mockParam1);
        Assert.assertNotEquals(mockParam1, mockParam2);
        Assert.assertNotEquals(mockParam1, new Object());
        Assert.assertEquals(mockParam1, mockParam3);
    }
}