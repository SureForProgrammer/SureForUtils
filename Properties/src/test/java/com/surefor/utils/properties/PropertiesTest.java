package com.surefor.utils.properties;

import static org.junit.Assert.*;
import org.junit.Test ;

import java.net.URL;
import java.net.URLClassLoader;

import com.surefor.utils.properties.Properties ;

public class PropertiesTest {

    @Test
    public void testInstance() throws Exception {
        Properties properties = Properties.instance() ;
        assertNotNull(properties) ;
    }

    @Test
    public void testCount() throws Exception {
        Properties properties = Properties.instance() ;
        assertTrue(properties.count() == 1) ;
    }

    @Test
    public void testGetConfig() throws Exception {

    }

    @Test
    public void testLoadProperties() throws Exception {

    }

    @Test
    public void testLoadProperties1() throws Exception {

    }

    @Test
    public void testGetClassPath() {
        System.out.println(System.getProperty("java.class.path")) ;

        System.out.println("-----------------------------------------------------------------------------------") ;
        // ClassLoader cl = ClassLoader.getSystemClassLoader();
        ClassLoader cl = this.getClass().getClassLoader() ;
        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }
    }
}