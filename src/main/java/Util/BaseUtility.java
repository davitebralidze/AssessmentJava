package Util;

import WebDriverManager.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static WebDriverManager.WebDriverManager.getDriver;

public class BaseUtility {

    private static final Logger log = LoggerFactory.getLogger(BaseUtility.class);

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeSuite
    public void allureCleaner() {
//        Utils.deleteAllureReports();
    }

    @BeforeMethod()
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
