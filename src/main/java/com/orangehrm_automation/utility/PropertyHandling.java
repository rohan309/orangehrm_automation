package com.orangehrm_automation.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandling {
    Properties properties;

    public PropertyHandling() {
        try {
            String filePath = System.getProperty("user.dir") + "/config.properties";
            FileInputStream inputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperties(String key) {
        String value = properties.getProperty(key);
        System.out.println(value);
        return value;
    }
}
