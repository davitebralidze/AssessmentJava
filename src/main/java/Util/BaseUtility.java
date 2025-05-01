package Util;

import WebDriverManager.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static WebDriverManager.WebDriverManager.getDriver;

public class BaseUtility {

    @BeforeSuite
    public void allureCleaner() {
        Utils.deleteAllureReports();
    }

    @BeforeMethod
    public void setup() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        getDriver().get(PropertyLoader.getProperty("url.base"));
        getDriver().get("https://mailfence.com/");
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebDriverManager.quitBrowser();
    }
}
