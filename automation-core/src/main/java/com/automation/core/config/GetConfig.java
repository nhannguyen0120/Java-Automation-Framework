package com.automation.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class GetConfig {
    public static String getConfig(String property) {
        File filePath = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filePath));
            return prop.getProperty(property);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}