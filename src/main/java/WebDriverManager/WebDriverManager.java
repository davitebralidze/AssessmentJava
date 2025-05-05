package WebDriverManager;

import Util.PropertyLoader;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final String browser = PropertyLoader.getProperty("BROWSER").toLowerCase();
    private static final String executionEnv = PropertyLoader.getProperty("EXECUTION_ENV").toLowerCase();
    //GRID-based properties
    private static final String gridOS = PropertyLoader.getProperty("GRID_OS");
    private static final String remoteURL = PropertyLoader.getProperty("REMOTE_URL");

    private WebDriverManager() {}

//    private void initDriver() {
//        createDriverWithOptions(null);
//    }
//    private void initDriver(int width, int height) {
//        createDriverWithOptions(new Dimension(width, height));
//    }
//    private void createDriverWithOptions(Dimension size) {
//        String browser = PropertyLoader.returnConfigValue("BROWSER").toLowerCase();
//        switch (browser) {
//            case "chrome" -> {
//                ChromeOptions options = new ChromeOptions();
//                if (size == null) {
//                    options.addArguments("--start-maximized");
//                } else {
//                    options.addArguments(String.format("--window-size=%d,%d", size.width, size.height));
//                }
//                tlDriver.set(new ChromeDriver(options));
//            }
//            case "firefox" -> {
//                FirefoxOptions options = new FirefoxOptions();
//                if (size == null) {
//                    options.addArguments("--start-maximized");
//                } else {
//                    options.addArguments("--width=" + size.width);
//                    options.addArguments("--height=" + size.height);
//                }
//                tlDriver.set(new FirefoxDriver(options));
//            }
//            case "edge" -> {
//                EdgeOptions options = new EdgeOptions();
//                if (size == null) {
//                    options.addArguments("--start-maximized");
//                } else {
//                    options.addArguments(String.format("--window-size=%d,%d", size.width, size.height));
//                }
//                tlDriver.set(new EdgeDriver(options));
//            }
//            default -> throw new IllegalArgumentException("Invalid browser type passed: " + browser);
//        }
//    }

    private static void initDriver() {
        switch (browser) {
            case "chrome" -> tlDriver.set(new ChromeDriver());
            case "firefox" -> tlDriver.set(new FirefoxDriver());
            case "edge" -> tlDriver.set(new EdgeDriver());
            case "safari" -> tlDriver.set(new SafariDriver());
            default -> throw new IllegalArgumentException("Invalid browser type passed: " + browser);
        }
    }

    private static void initRemoteDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (gridOS.toLowerCase()) {
            case "windows11" -> capabilities.setPlatform(Platform.WIN11);
            case "windows10" -> capabilities.setPlatform(Platform.WIN10);
            case "mac" -> capabilities.setPlatform(Platform.MAC);
            default -> throw new IllegalArgumentException("Invalid OS type passed: " + gridOS);
        }

        try {
            capabilities.setBrowserName(browser);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid browser type passed: " + browser);
        }

        try {
            tlDriver.set(new RemoteWebDriver(new URL(remoteURL), capabilities));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed Grid URL", e);
        }

    }

    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            switch (executionEnv) {
                case "local" -> initDriver();
                case "remote" -> initRemoteDriver();
                default -> throw new IllegalArgumentException("Invalid executionEnv type passed: " + executionEnv);
            }
        }
        return tlDriver.get();
    }

    public static void quitBrowser() {
        if(tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}