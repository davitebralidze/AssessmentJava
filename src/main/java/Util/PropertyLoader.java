package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyLoader {
    private final String environment;

    public PropertyLoader(String environment) {
        this.environment = environment;
    }

    public String returnConfigValue(final String property) {
        Logger logger = Logger.getLogger(PropertyLoader.class.getName());
        String direction = "data-qa.properties";
        switch (environment) {
            case "qa" -> direction = "env-properties/qa-env.properties";
            case "uat" -> direction = "env-properties/uat-env.properties";
            case "live" -> direction = "env-properties/live-env.properties";
            default -> {
                logger.severe("Such environment does not exist");
                System.exit(0);
            }
        }
        Properties properties = new Properties();
        try (InputStream propFileInpStream = PropertyLoader.class.getClassLoader().getResourceAsStream(direction)) {
            properties.load(propFileInpStream);
            return properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
