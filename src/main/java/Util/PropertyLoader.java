package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    static Properties properties = new Properties();
    final static String configFilePath = "config.properties";
    final static ClassLoader classLoader = PropertyLoader.class.getClassLoader();

    static {

        try (InputStream configInputStream = classLoader.getResourceAsStream(configFilePath)) {
            properties.load(configInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String environment = (String) properties.get("ENVIRONMENT");
        String direction = String.format("env-properties/%s-env.properties", environment);

        try (InputStream propFileInpStream = classLoader.getResourceAsStream(direction)) {
            properties.load(propFileInpStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        return System.getProperty(propertyName, properties.getProperty(propertyName));
    }

    public static void setProperty(String propertyName, String newValue) {
        properties.setProperty(propertyName, newValue);
    }

}
