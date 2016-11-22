package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by juan on 22/11/16.
 */
public class FileTest {

    private File file;

    @Before
    public void setup() {
        this.file = new File();
    }

    @Test
    public void getName() {
        //SETUP
        String newName = "New name";
        this.file.setName(newName);

        // ACTION
        String name = this.file.getName();

        // EXPECTED
        Assert.assertEquals(name, newName);
    }

    @Test
    public void setName() {
        //SETUP
        String name = "New name";

        // ACTION
        this.file.setName(name);

        // EXPECTED
        Assert.assertEquals(this.file.getName(), name);
    }

    @Test
    public void getPath() {
        //SETUP
        String newPath = "New path";
        this.file.setPath(newPath);

        // ACTION
        String path = this.file.getPath();

        // EXPECTED
        Assert.assertEquals(path, newPath);
    }

    @Test
    public void setPath() {
        //SETUP
        String path = "New path";

        // ACTION
        this.file.setPath(path);

        // EXPECTED
        Assert.assertEquals(this.file.getPath(), path);
    }

    @Test
    public void getToken() {
        //SETUP
        String newToken = "New token";
        this.file.setToken(newToken);

        // ACTION
        String token = this.file.getToken();

        // EXPECTED
        Assert.assertEquals(token, newToken);
    }

    @Test
    public void setToken() {
        //SETUP
        String token = "New token";

        // ACTION
        this.file.setToken(token);

        // EXPECTED
        Assert.assertEquals(this.file.getToken(), token);
    }

    @Test
    public void getFilename() {
        //SETUP
        String newFilename = "New filename";
        this.file.setFilename(newFilename);

        // ACTION
        String filename = this.file.getFilename();

        // EXPECTED
        Assert.assertEquals(filename, newFilename);
    }

    @Test
    public void setFilename() {
        //SETUP
        String fileName = "New filename";

        // ACTION
        this.file.setFilename(fileName);

        // EXPECTED
        Assert.assertEquals(this.file.getFilename(), fileName);
    }

    @Test
    public void getSize() {
        //SETUP
        String newSize = "New size";
        this.file.setSize(newSize);

        // ACTION
        String size = this.file.getSize();

        // EXPECTED
        Assert.assertEquals(size, newSize);
    }

    @Test
    public void setSize() {
        //SETUP
        String size = "New size";

        // ACTION
        this.file.setSize(size);

        // EXPECTED
        Assert.assertEquals(this.file.getSize(), size);
    }

    @Test
    public void getMime() {
        //SETUP
        String newMime = "New mime";
        this.file.setMime(newMime);

        // ACTION
        String mime = this.file.getMime();

        // EXPECTED
        Assert.assertEquals(mime, newMime);
    }

    @Test
    public void setMime() {
        //SETUP
        String mime = "New mime";

        // ACTION
        this.file.setMime(mime);

        // EXPECTED
        Assert.assertEquals(this.file.getMime(), mime);
    }


    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        File mockFile1 = podamFactory.manufacturePojoWithFullData(File.class);
        File mockFile2 = podamFactory.manufacturePojoWithFullData(File.class);
        File mockFile3 = new File(mockFile1);
        Assert.assertNotEquals(mockFile1, mockFile2);
        Assert.assertNotEquals(mockFile1, new Object());
        Assert.assertEquals(mockFile1, mockFile3);
    }
}