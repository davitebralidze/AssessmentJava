package WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {
    private static volatile WebDriverManager instance;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriverManager() {}

    private void initDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                tlDriver.set(new ChromeDriver(chromeOptions));
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                tlDriver.set(new FirefoxDriver(firefoxOptions));
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                tlDriver.set(new EdgeDriver(edgeOptions));
            }
            default -> throw new IllegalArgumentException("Invalid browser type passed: " + browser);
        }
    }

//    private void initDriver(String browser, int width, int height) {
//        switch (browser.toLowerCase()) {
//            case "chrome" -> {
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments(String.format("--window-size=%d,%d", width, height)); // Set window size
//                tlDriver.set(new ChromeDriver(chromeOptions));
//            }
//            case "firefox" -> {
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments(String.format("--width=%d", width));
//                firefoxOptions.addArguments(String.format("--height=%d", height));
//                tlDriver.set(new FirefoxDriver(firefoxOptions));
//            }
//            case "edge" -> {
//                EdgeOptions edgeOptions = new EdgeOptions();
//                edgeOptions.addArguments(String.format("--window-size=%d,%d", width, height)); // Set window size
//                tlDriver.set(new EdgeDriver(edgeOptions));
//            }
//            default -> throw new IllegalArgumentException("Invalid browser type passed: " + browser);
//        }
//    }

    public static WebDriverManager getInstance(String browser) {
        if (instance == null) {
            synchronized (WebDriverManager.class) {
                if (instance == null) {
                    instance = new WebDriverManager();
                }
            }
        }
        if (tlDriver.get() == null) {
            instance.initDriver(browser);
        }
        return instance;
    }

//    public static WebDriverManager getInstance(String browser, int width, int height) {
//        if (instance == null) {
//            synchronized (WebDriverManager.class) {
//                if (instance == null) {
//                    instance = new WebDriverManager();
//                }
//            }
//        }
//        if (tlDriver.get() == null) {
//            instance.initDriver(browser, width, height);
//        }
//        return instance;
//    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitBrowser() {
        if(tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}