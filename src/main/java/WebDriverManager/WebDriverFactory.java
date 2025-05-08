package WebDriverManager;

import Util.PropertyLoader;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    private static final String browser = PropertyLoader.getProperty("BROWSER").toLowerCase();
    private static final String executionEnv = PropertyLoader.getProperty("EXECUTION_ENV").toLowerCase();
    //GRID-based properties
    private static final String gridOS = PropertyLoader.getProperty("GRID_OS");
    private static final String remoteURL = PropertyLoader.getProperty("REMOTE_URL");

    public static WebDriver createDriver() {
        return switch (executionEnv) {
            case "local" -> new LocalDriverFactory(browser).createDriver();
            case "remote" -> new RemoteDriverFactory(browser, gridOS, remoteURL).createDriver();
            default -> throw new IllegalArgumentException("Invalid executionEnv type passed: " + executionEnv);
        };
    }
}
