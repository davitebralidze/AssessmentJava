package WebDriverManager;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public static void maximizeBrowserWindow() {
        getDriver().manage().window().maximize();
    }

    public static void setBrowserWindowResolution(int width, int height) { getDriver().manage().window().setSize(new Dimension(width, height)); }

    /**
     * Switches to the window at the specified 1-based index.
     *
     * @param index The 1-based index of the window to switch to. Index starts at 1.
     * @throws IllegalArgumentException if the index is out of bounds of the open windows.
     */
    public static void switchToBrowserTabByIndex(int index) {
        Set<String> allWindowHandles = getDriver().getWindowHandles();
        List<String> windowList = new ArrayList<>(allWindowHandles);

        if (index >= 1 && index <= windowList.size()) {
            getDriver().switchTo().window(windowList.get(index - 1));
        } else {
            throw new InvalidArgumentException("Invalid window index: " + index + ". Total open windows: " + windowList.size());
        }
    }

    public static void closeCurrentBrowserTab() { getDriver().close(); }

    /**
     * Closes the window at the specified 1-based index.
     *
     * @param index The 1-based index of the window/tab to close. Index starts at 1.
     * @throws IllegalArgumentException if the index is out of bounds of the open windows.
     */
    public static void closeBrowserTabByIndex(int index) {
        Set<String> windowHandles = getDriver().getWindowHandles();
        List<String> windowList = new ArrayList<>(windowHandles);

        if (index >= 1 && index <= windowList.size()) {
            getDriver().switchTo().window(windowList.get(index - 1));
            getDriver().close();
        } else {
            throw new IllegalArgumentException("Invalid window index: " + index + ". Total open windows: " + windowList.size());
        }
    }

    public static void setImplicitWait(int waitDurationInSeconds) { getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitDurationInSeconds)); }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
