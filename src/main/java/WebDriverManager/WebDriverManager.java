package WebDriverManager;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            WebDriver driver = WebDriverFactory.createDriver();
            tlDriver.set(driver);
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
