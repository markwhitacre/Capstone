package com.example.abcvanstock;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import Components.Part;

public class PartTest extends TestCase {
    Part part;

    @Before
    public void setUp() {
        part = new Part(1, "2020-01-01");
    }



    
    public void testSetPartNumber() {
        part.setPartNumber(1234);
        Assert.assertEquals(1234, part.getPartNumber());
    }

  
    public void testSetSerialNumber() {
        part.setSerialNumber("12345");
        Assert.assertEquals("12345", part.getSerialNumber());
    }

    
    public void testSetDescription() {
        part.setDescription("test");
        Assert.assertEquals("test", part.getDescription());
    }

    public void testSetCost() {
        part.setCost(1.0);
        Assert.assertEquals(1.0, part.getCost(), 0.0);
    }
    
    public void testSetAssociatedProduct() {
        part.setAssociatedProduct(1);
        Assert.assertEquals(1, part.getAssociatedProduct());
    }

    public void testSetLastUpdated() {
        part.setLastUpdated(ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        Assert.assertEquals(ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)), part.getLastUpdated());
    }

    @After
    public void tearDown() {
        part = null;
    }
}