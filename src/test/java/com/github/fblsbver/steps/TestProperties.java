package com.github.fblsbver.steps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private static FileInputStream fileInputStream;
    private static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("D:\\Autotesting\\api-users-expandtesting\\src\\test\\resources\\config\\test.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(fileInputStream != null){
                try{
                    fileInputStream.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key){
        return PROPERTIES.getProperty(key);
    }
}
