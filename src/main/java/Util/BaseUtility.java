package Util;

import Pages.LandingPage.LandingPage;
import Pages.LoginPage.LogInPage;
import Pages.MessagesPage.MessagesPage;
import WebDriverManager.WebDriverManager;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Random;

public class BaseUtility {

    private static final Logger log = LoggerFactory.getLogger(BaseUtility.class);
    protected WebDriver driver;
    protected PropertyLoader propertyLoader;
    protected LandingPage landingPage;
    protected LogInPage logInPage;
    protected MessagesPage messagesPage;
    protected Faker faker;

    @BeforeSuite
    public void allureCleaner() {
        faker = new Faker();
        Utils.deleteAllureReports();
    }

    @BeforeMethod()
    @Parameters({"browser", "environment"})
    public void setup(final String browser, final String environment) {
        driver = WebDriverManager.getInstance(browser).getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        propertyLoader = new PropertyLoader(environment);
        driver.get(propertyLoader.returnConfigValue("url.base"));

        landingPage = new LandingPage(driver);
        logInPage = new LogInPage(driver);
        messagesPage = new MessagesPage(driver);

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
