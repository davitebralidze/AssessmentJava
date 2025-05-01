package WebDriverManager;

import Util.PropertyLoader;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private static volatile WebDriverManager instance;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

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

    private void initDriver() {
        String browser = PropertyLoader.getProperty("BROWSER").toLowerCase();
        switch (browser) {
            case "chrome" -> tlDriver.set(new ChromeDriver());
            case "firefox" -> tlDriver.set(new FirefoxDriver());
            case "edge" -> tlDriver.set(new EdgeDriver());
            default -> throw new IllegalArgumentException("Invalid browser type passed: " + browser);
        }
    }

    public static WebDriver getDriver() {
        if (instance == null) {
            synchronized (WebDriverManager.class) {
                if (instance == null) {
                    instance = new WebDriverManager();
                }
            }
        }
        if (tlDriver.get() == null) {
            instance.initDriver();
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