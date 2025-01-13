package ElementWrappers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseElement {

    WebDriver driver;
    By locator;
    String elementName;
    WebDriverWait wait;
    Actions actions;

    public BaseElement(WebDriver driver, By locator, String elementName) {
        this.driver = driver;
        this.locator = locator;
        this.elementName = elementName;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Customize the timeout as needed
        this.actions = new Actions(driver);
    }

    public BaseElement(WebDriver driver, By locator) {
        this(driver, locator, "Base Element");
    }

    protected WebElement getElement() {
        return driver.findElement(locator);
    }

    //Add wait for element separately

    public void click() {
        getElement().click();
    }

    public void fill(String text) {
        getElement().sendKeys(text);
    }

    public void scrollIntoView() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement());
    }

    public void waitElementVisibility() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitElementInvisibility() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void hover() {
        actions.moveToElement(getElement()).perform();
    }

    public boolean isDisplayed() {
        try {
            return getElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
