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

    public static String saveTheCurrentBrowserTabId() {
        return getDriver().getWindowHandle();
    }

    public static void switchToBrowserTabByIndex(int index) {
        Set<String> allWindowHandles = getDriver().getWindowHandles();
        List<String> windowList = new ArrayList<>(allWindowHandles);

        if (index >= 1 && index <= windowList.size()) {
            getDriver().switchTo().window(windowList.get(index));
        } else {
            throw new InvalidArgumentException("Invalid window index: " + index + ". Total open windows: " + windowList.size());
        }
    }

    public static void moveToBrowserTabByID(String windowHandle) {
        getDriver().switchTo().window(windowHandle);
    }

    public static void switchToNextBrowserTab() {
        Set<String> windowHandles = getDriver().getWindowHandles();
        List<String> windowList = new ArrayList<>(windowHandles);

        String currentHandle = getDriver().getWindowHandle();
        int currentIndex = windowList.indexOf(currentHandle);

        if (currentIndex == windowList.size() - 1) {
            throw new IndexOutOfBoundsException("Already on the last tab. Cannot switch to next.");
        }

        int nextIndex = currentIndex + 1;
        getDriver().switchTo().window(windowList.get(nextIndex));
    }

    public static void switchToPreviousBrowserTab() {
        Set<String> windowHandles = getDriver().getWindowHandles();
        List<String> windowList = new ArrayList<>(windowHandles);

        String currentHandle = getDriver().getWindowHandle();
        int currentIndex = windowList.indexOf(currentHandle);

        if (currentIndex == 0) {
            throw new IndexOutOfBoundsException("Already on the first tab. Cannot switch to previous.");
        }

        int previousIndex = currentIndex - 1;
        getDriver().switchTo().window(windowList.get(previousIndex));
    }

    public static void moveToFirstBrowserTab() {
        switchToBrowserTabByIndex(0);
    }

    public static void moveToLastBrowserTab() {
        Set<String> handles = getDriver().getWindowHandles();
        List<String> handleList = new ArrayList<>(handles);
        if (handleList.isEmpty()) {
            throw new IllegalStateException("No browser tabs open.");
        }
        getDriver().switchTo().window(handleList.getLast());
    }

    public static void closeCurrentBrowserTab() { getDriver().close(); }

    public static void closeBrowserTabByIndex(int index) {
        Set<String> windowHandles = getDriver().getWindowHandles();
        List<String> windowList = new ArrayList<>(windowHandles);

        if (index >= 0 && index <= windowList.size()) {
            getDriver().switchTo().window(windowList.get(index));
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

    @Attachment(value = "Element Screenshot", type = "image/png")
    public static byte[] takeElementScreenshot(WebElement element) {
        return element.getScreenshotAs(OutputType.BYTES);
    }
}
