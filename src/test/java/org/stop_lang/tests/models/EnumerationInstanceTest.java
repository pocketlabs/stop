package org.stop_lang.tests.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.stop_lang.models.Enumeration;
import org.stop_lang.models.EnumerationInstance;

import java.util.ArrayList;
import java.util.List;

public class EnumerationInstanceTest {
    @Test
    public void model(){
        List<String> values = new ArrayList<>();
        values.add("DRAFT");
        values.add("PUBLISHED");
        Enumeration enumeration = new Enumeration("name", values);

        try {
            EnumerationInstance enumerationInstance = new EnumerationInstance(enumeration, "PUBLISHED");
            Assert.assertNotNull(enumerationInstance);
            Assert.assertTrue(enumerationInstance.getEnumeration().equals(enumeration));
            Assert.assertTrue(enumerationInstance.getValue().equals("PUBLISHED"));
        }catch(Exception e){
            Assert.fail();
        }

        try {
            new EnumerationInstance(enumeration, "INVALID");
            Assert.fail();
        }catch(Exception e){
            // Success
        }
    }
}
