package com.article.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class to store all project properties
 *
 * @author Gary A. Stafford
 */
public class ProjectProperties {

    private static ProjectProperties instance = null;
    private final Properties properties = new java.util.Properties();
    private final String ConfigurationFile = "config.properties";

    /**
     * @throws Exception
     */
    private ProjectProperties() throws Exception {
        readConfigProperties();
    }

    /**
     * Returns singleton instance of class.
     *
     * @return the instance
     */
    static public ProjectProperties getInstance() {
        if (instance == null) {
            try {
                instance = new ProjectProperties();
            } catch (Exception ex) {
                Logger.getLogger(ProjectProperties.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    private void readConfigProperties() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ConfigurationFile)) {
            properties.load(inputStream);
        }
    }

    /**
     * @return the properties object
     */
    public java.util.Properties getProperties() {
        return properties;
    }

    /**
     * @return the xmlConfigurationFile
     */
    public String getConfigurationFile() {
        return ConfigurationFile;
    }
}
