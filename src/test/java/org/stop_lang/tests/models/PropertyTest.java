package org.stop_lang.tests.models;

import org.junit.Assert;
import org.junit.Test;
import org.stop_lang.models.Property;

import java.nio.ByteBuffer;
import java.time.Instant;

public class PropertyTest {
    @Test
    public void getClassForScalarPropertyType(){
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.DOUBLE), Double.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.FLOAT), Float.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.INT32), Integer.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.INT64), Long.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.UINT32), Integer.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.UINT64), Long.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.SINT32), Integer.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.SINT64), Long.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.FIXED32), Integer.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.FIXED64), Long.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.SFIXED32), Integer.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.SFIXED64), Long.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.BOOL), Boolean.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.STRING), String.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.TIMESTAMP), Instant.class);
        Assert.assertEquals(Property.getClassForScalarPropertyType(Property.PropertyType.BYTES), ByteBuffer.class);
        try {
            Property.getClassForScalarPropertyType(Property.PropertyType.STATE);
            Assert.fail();
        }catch(IllegalArgumentException e){
            // Good
        }
    }

    @Test
    public void getPropertyType(){
        Assert.assertEquals(Property.getPropertyType("double"), Property.PropertyType.DOUBLE);
        Assert.assertEquals(Property.getPropertyType("float"), Property.PropertyType.FLOAT);
        Assert.assertEquals(Property.getPropertyType("int32"), Property.PropertyType.INT32);
        Assert.assertEquals(Property.getPropertyType("int64"), Property.PropertyType.INT64);
        Assert.assertEquals(Property.getPropertyType("uint32"), Property.PropertyType.UINT32);
        Assert.assertEquals(Property.getPropertyType("uint64"), Property.PropertyType.UINT64);
        Assert.assertEquals(Property.getPropertyType("sint32"), Property.PropertyType.SINT32);
        Assert.assertEquals(Property.getPropertyType("sint64"), Property.PropertyType.SINT64);
        Assert.assertEquals(Property.getPropertyType("fixed32"), Property.PropertyType.FIXED32);
        Assert.assertEquals(Property.getPropertyType("fixed64"), Property.PropertyType.FIXED64);
        Assert.assertEquals(Property.getPropertyType("sfixed32"), Property.PropertyType.SFIXED32);
        Assert.assertEquals(Property.getPropertyType("sfixed64"), Property.PropertyType.SFIXED64);
        Assert.assertEquals(Property.getPropertyType("bool"), Property.PropertyType.BOOL);
        Assert.assertEquals(Property.getPropertyType("string"), Property.PropertyType.STRING);
        Assert.assertEquals(Property.getPropertyType("timestamp"), Property.PropertyType.TIMESTAMP);
        Assert.assertEquals(Property.getPropertyType("bytes"), Property.PropertyType.BYTES);
        Assert.assertEquals(Property.getPropertyType("Test"), Property.PropertyType.STATE);
    }
}
