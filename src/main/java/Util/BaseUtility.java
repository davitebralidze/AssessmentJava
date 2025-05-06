package Util;

import WebDriverManager.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

import static WebDriverManager.WebDriverManager.getDriver;

public class BaseUtility {

    @BeforeSuite
    public void allureCleaner() {
        Utils.deleteAllureReports();
    }

    @BeforeMethod
//    @Parameters("browser")
    public void setup(/*String browser*/) {
//        PropertyLoader.setProperty("BROWSER", browser); --> in case of parametrization you can set parameters from here
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        getDriver().get(PropertyLoader.getProperty("url.base"));
        getDriver().get("https://mailfence.com/");
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitBrowser();
    }
}