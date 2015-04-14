package com.surefor.utils.properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Manages properties
 *
 * Created by chae on 4/10/2015.
 */
public class Properties {
    private Map<String, Configuration> configs = new HashMap();
    private static Properties instance = null ;
    private final String SYSTEM_PROPERTIES = "_SYSTEM_" ;

    /**
     * Singleton implementation.
     */
    public static synchronized Properties instance() {
        if(instance == null) {
            instance = new Properties() ;
        }
        return instance ;
    }

    /**
     * Private class constructor
     */
    private Properties() {
        // Load system properties
        loadProperties();
    }

    /**
     * Number of properties loaded
     */
    public Integer count() {
        return configs.size() ;
    }

    /**
     * Searches properteis and return Apache Common Configuration object
     * @param properites File name of properties e.g. app.properties
     * @throws NullPointerException If null valued parameter is passed.
     * @throws ProperitesNotFondException If it fails to search with specified properties name
     */
    public Configuration getConfig(String properites)
            throws NullPointerException, ProperitesNotFondException {
        Objects.requireNonNull(properites) ;

        if(configs.containsKey(properites)) {
            return configs.get(properites) ;
        } else {
            throw new ProperitesNotFondException(properites) ;
        }
    }

    /**
     * Loads system properites. If system properties is already loaded, it would replace existing configuration.
     */
    public void loadProperties() {
        load();
    }

    /**
     * Loads a properties file and add it to internal list.
     * @param properties Properties file name include path
     * @throws NullPointerException Null valued parameter passed
     * @throws ProperitesAlreadyExistException AlreadyExistException Specified properties exists in the list.
     * @throws ConfigurationException Failed to load properties file.
     */
    public void loadProperties(String properties)
            throws NullPointerException, ProperitesAlreadyExistException, ConfigurationException {
        Objects.requireNonNull(properties) ;

        load(properties, false) ;
    }

    /**
     * Clears configuration map.
     */
    private void clear() {
        configs.clear();
    }

    /**
     * Loads system properties.
     */
    private void load() {
        configs.put(SYSTEM_PROPERTIES, new SystemConfiguration()) ;
    }

    /**
     *
     * @param properties Properties name to load
     * @param replace If
     * @throws ConfigurationException It failed to load properites file.
     * @throws ProperitesAlreadyExistException Specified properties is already loaded and caller does not want to replace existing configuration.
     */
    private void load(String properties, Boolean replace)
            throws ConfigurationException, ProperitesAlreadyExistException {
        assert(properties != null) ;

        String key = Paths.get(properties).getFileName().toString() ;
        PropertiesConfiguration config = new PropertiesConfiguration(properties) ;
        if(!configs.containsKey(properties) || (configs.containsKey(properties) && replace)) {
            configs.put(key, config) ;
        } else {
            throw new ProperitesAlreadyExistException(properties) ;
        }
    }

    /**
     *
     * @param key
     * @return Apache (@link org.apache.commons.configuration.Configuration) object
     * @throws ProperitesNotFondException
     */
    public Configuration get(String key) throws ProperitesNotFondException{
        if(configs.containsKey(key)) {
            return configs.get(key) ;
        } else {
            throw new ProperitesNotFondException(key) ;
        }
    }

    /**
     * Determines specified properties is loaded.
     * @param key Properties name
     * @return true if specified properties is found, false if not.
     */
    public Boolean isLoaded(String key)  {
        return configs.containsKey(key) ;
    }

    public String get(String key, String property) throws ProperitesNotFondException {
        if(isLoaded(key)) {
            Configuration config = configs.get(key) ;
            config.getProperty(property) ;
            if (config.containsKey(property)) {
                config.getString(key) ;
            } else {
                return null ;
            }
        } else {
            throw new ProperitesNotFondException(key) ;
        }
        return null ;
    }
}
