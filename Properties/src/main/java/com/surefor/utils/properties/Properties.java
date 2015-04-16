package com.surefor.utils.properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.lang.StringUtils;

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
    public final String SYSTEM_PROPERTIES = "_SYSTEM_" ;

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
        loadProperties(); // Load system properties as default
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
    private Configuration getConfig(String properites)
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
     * @throws ProperitesExistException AlreadyExistException Specified properties exists in the list.
     * @throws ConfigurationException Failed to load properties file.
     */
    public void loadProperties(String properties)
            throws NullPointerException, ProperitesExistException, ConfigurationException {
        Objects.requireNonNull(properties) ;

        load(properties, false) ;
    }

    /**
     * Clears configuration map.
     */
    public void clear() {
        configs.clear();
        load() ;
    }

    /**
     * Loads system properties.
     */
    private void load() {
        configs.put(SYSTEM_PROPERTIES, new SystemConfiguration()) ;
    }

    /**
     * Load properties file 
     * 
     * @param properties Properties name to load
     * @param replace true - replace it if specified properties is already loaded. 
     * @throws ConfigurationException It failed to load properites file.
     */
    private void load(String properties, Boolean replace) throws ConfigurationException {
        assert(properties != null) ;

        String key = Paths.get(properties).getFileName().toString() ;
        PropertiesConfiguration config = new PropertiesConfiguration(properties) ;
        if(!configs.containsKey(properties) || (configs.containsKey(properties) && replace)) {
            configs.put(key, config) ;
        } 
    }

    /**
     * Determines specified properties is loaded.
     * @param properties Properties name
     * @return true if specified properties is found, false if not.
     */
    public Boolean isLoaded(String properties)  {
        return configs.containsKey(properties) ;
    }

    public Boolean isNotLoaded(String properties)  {
        return !isLoaded(properties) ;
    }
    
    /**
     * Get a configuration value in String type 
     * 
     * @param properties properties file name 
     * @param configName configuration name
     * @return
     * @throws ProperitesNotFondException If it failed to search properties  
     * @throws ConfigNotFondException  if it failed to search configName
     */
    public String get(String properties, String configName) throws ProperitesNotFondException, ConfigNotFondException {
    	Configuration config = getConfig(properties) ;
        if (config.containsKey(configName)) {
            return config.getString(configName) ;
        } else {
            throw new ConfigNotFondException(configName) ;
        }
    }
    
    /**
     * Put a configuration item into the properties file  
     * @param properties Properties file name (full path)
     * @param configName Configuration name 
     * @param configValue Configuration value. It will turn to empty string if null value is passed.
     * @throws ConfigurationException if it failed to search configName
     * @throws NullPointerException if properties and/or configName is null
     * @throws ProperitesNotFondException if it failed to search properties 
     */
    public void put(String properties, String configName, String configValue) throws ConfigurationException, NullPointerException, ProperitesNotFondException {
    	Objects.requireNonNull(properties) ; 
    	Objects.requireNonNull(configName) ; 
    	
    	if(isNotLoaded(properties)) {
			load(properties, true) ;
    	}

    	PropertiesConfiguration config = (PropertiesConfiguration) getConfig(properties) ;
    	if(config.containsKey(configName)) {
    		config.setProperty(configName, StringUtils.defaultString(configValue)) ;
    	} else {
        	config.addProperty(configName, StringUtils.defaultString(configValue)) ;
    	}
    	config.save() ;
    }
    
    /**
     * 
     * @param properties
     * @param configName
     * @param configValue
     * @throws ConfigurationException
     * @throws ProperitesExistException
     * @throws NullPointerException
     * @throws ProperitesNotFondException
     */
    public void add(String properties, String configName, String configValue) throws ConfigurationException, ProperitesExistException, NullPointerException, ProperitesNotFondException {
    	Objects.requireNonNull(properties) ; 
    	Objects.requireNonNull(configName) ; 
    	
    	if(isNotLoaded(properties)) {
    		load(properties, true) ;
    	}

    	PropertiesConfiguration config = (PropertiesConfiguration) getConfig(properties) ;
    	config.addProperty(configName, StringUtils.defaultString(configValue)) ;
    	
    	config.save() ;
    }
    
}
