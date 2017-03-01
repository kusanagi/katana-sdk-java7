package com.katana.sdk;

import com.katana.sdk.File;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FileTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        File object = mockFactory.getFile();
        Assert.assertEquals(object, new File(object));
        Assert.assertEquals(397391610, object.hashCode());
    }

}