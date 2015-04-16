package com.surefor.utils.properties;

import static org.junit.Assert.*;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test ;

import java.net.URL;
import java.net.URLClassLoader;

import com.surefor.utils.properties.Properties ;

public class PropertiesTest {
	private static String PROPERTIES = "test.properties" ; 
	private static String config1 = "config.name1" ; 
	private static String config2 = "config.name2" ; 
	private static String config3 = "config.name3" ; 
	private static String config4 = "config.name4" ;
	private static String value1 = "value1" ;
	private static String value2 = "value2" ;
	private static String value3 = "value3" ;
	private static String value4 = "value4" ;
	
	@Before
	public void setup() {
		createTestProperties() ;
	}
	
	private void createTestProperties() {
		Properties properties = Properties.instance() ;
		try {
			properties.put(PROPERTIES, config1, value1) ;
			properties.put(PROPERTIES, config2, value2) ;
			properties.put(PROPERTIES, config3, value3) ;
			properties.put(PROPERTIES, config4, value4) ;
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ProperitesNotFondException e) {
			e.printStackTrace();
		}
	}
	
    @Test
    public void testInstance() throws Exception {
        Properties properties = Properties.instance() ;
        assertNotNull(properties) ;
    }

    @Test
    public void testCount() throws Exception {
        Properties properties = Properties.instance() ;
        
        properties.clear() ;
        assertTrue(properties.count() == 1) ;
        
        createTestProperties() ;
        assertTrue(properties.count() == 2) ;
    }

    @Test
    public void testGetConfig() throws Exception {
        Properties properties = Properties.instance() ;
        
        try {
        	properties.loadProperties(PROPERTIES) ;
        } catch(ProperitesExistException e) {
        	
        }
        
        String value = properties.get(PROPERTIES, config1) ;
        assertEquals(value, value1) ;
        
        value = properties.get(PROPERTIES, config2) ;
        assertEquals(value, value2) ;
        
        value = properties.get(PROPERTIES, config3) ;
        assertEquals(value, value3) ;
        
        value = properties.get(PROPERTIES, config4) ;
        assertEquals(value, value4) ;
    }

    @Test
    public void testLoadProperties() throws Exception {

    }

    @Test
    public void testLoadProperties1() throws Exception {

    }
}