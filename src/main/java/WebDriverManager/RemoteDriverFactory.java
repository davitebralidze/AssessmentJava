package WebDriverManager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverFactory implements IDriverFactory {
    private final String browser;
    private final String gridOS;
    private final String remoteURL;

    public RemoteDriverFactory(String browser, String gridOS, String remoteURL) {
        this.browser = browser.toLowerCase();
        this.gridOS = gridOS.toLowerCase();
        this.remoteURL = remoteURL;
    }

    @Override
    public WebDriver createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        try {
            capabilities.setBrowserName(browser);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid browser type passed: " + browser);
        }

        capabilities.setPlatform(switch (gridOS) {
            case "windows10" -> Platform.WIN10;
            case "windows11" -> Platform.WIN11;
            case "mac" -> Platform.MAC;
            default -> throw new IllegalArgumentException("Invalid OS: " + gridOS);
        });

        try {
            return new RemoteWebDriver(new URL(remoteURL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid remote URL", e);
        }
    }
}
