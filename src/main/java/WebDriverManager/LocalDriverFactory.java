package WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class LocalDriverFactory implements IDriverFactory {
    private final String browser;

    public LocalDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    @Override
    public WebDriver createDriver() {
        return switch (browser) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            case "safari" -> new SafariDriver();
            default -> throw new IllegalArgumentException("Invalid local browser: " + browser);
        };
    }
}