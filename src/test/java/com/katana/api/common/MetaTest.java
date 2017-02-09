package com.katana.api.common;

import com.katana.api.Meta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by juan on 12/11/16.
 */
public class MetaTest {

    private Meta meta;

    @Before
    public void setup() {
        meta = new Meta();
    }

    @Test
    public void getVersion() {
        // SETUP
        String version = "version";
        this.meta.setVersion(version);

        // ACTION
        String versionObtained = this.meta.getVersion();

        // EXPECTED
        Assert.assertEquals(version, versionObtained);
    }

    @Test
    public void setVersion() {
        // SETUP
        String version = "version";

        // ACTION
        this.meta.setVersion(version);

        // EXPECTED
        Assert.assertEquals(version, this.meta.getVersion());
    }

    @Test
    public void getId() {
        // SETUP
        String id = "Id";
        this.meta.setId(id);

        // ACTION
        String idObtained = this.meta.getId();

        // EXPECTED
        Assert.assertEquals(id, idObtained);
    }

    @Test
    public void setId() {
        // SETUP
        String id = "Id";

        // ACTION
        this.meta.setId(id);

        // EXPECTED
        Assert.assertEquals(id, this.meta.getId());
    }

    @Test
    public void getDatetime() {
        // SETUP
        String datetime = "Datetime";
        this.meta.setDatetime(datetime);

        // ACTION
        String datetimeObtained = this.meta.getDatetime();

        // EXPECTED
        Assert.assertEquals(datetime, datetimeObtained);
    }

    @Test
    public void setDatetime() {
        // SETUP
        String datetime = "Datetime";

        // ACTION
        this.meta.setDatetime(datetime);

        // EXPECTED
        Assert.assertEquals(datetime, this.meta.getDatetime());
    }

    @Test
    public void getType() {
        // SETUP
        int type = 2;
        this.meta.setType(type);

        // ACTION
        int typeObtained = this.meta.getType();

        // EXPECTED
        Assert.assertEquals(type, typeObtained);
    }

    @Test
    public void setType() {
        // SETUP
        int type = 2;

        // ACTION
        this.meta.setType(type);

        // EXPECTED
        Assert.assertEquals(type, this.meta.getType());
    }

    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Meta mockMeta1 = podamFactory.manufacturePojoWithFullData(Meta.class);
        Meta mockMeta2 = podamFactory.manufacturePojoWithFullData(Meta.class);
        Meta mockMeta3 = new Meta(mockMeta1);
        Assert.assertNotEquals(mockMeta1, mockMeta2);
        Assert.assertNotEquals(mockMeta1, new Object());
        Assert.assertEquals(mockMeta1, mockMeta3);
    }
}