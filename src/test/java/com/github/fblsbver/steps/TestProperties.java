package com.github.fblsbver.steps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private static Properties PROPERTIES;

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/test.properties")){
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        }
        catch (IOException e){
            System.out.println("Unable to read properties");
        }
    }

    public static String getProperty(String key){
        return (String) PROPERTIES.getOrDefault(key, "");
    }
}
