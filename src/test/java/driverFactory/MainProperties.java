package driverFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by rgavrysh on 10/6/2016.
 */
public class MainProperties {

    public static String getProperty(String property) {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("main.properties"));
        } catch (IOException e) {
            System.out.println("Couldn't read main.properties file.");
            e.printStackTrace();
        }
        return properties.getProperty(property);
    }
}
