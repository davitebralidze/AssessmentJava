package ElementWrappers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static WebDriverManager.WebDriverManager.getDriver;

public class BaseElement {

    By locator;
    String elementName;
    WebDriverWait wait;
    Actions actions;

    public BaseElement(By locator, String elementName) {
        this.locator = locator;
        this.elementName = elementName;
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));  // Customize the timeout as needed
        this.actions = new Actions(getDriver());
    }

    public BaseElement(By locator) {
        this(locator, "Base Element");
    }

    protected WebElement getElement() {
        return getDriver().findElement(locator);
    }

    public void click() {
        getElement().click();
    }

    public void fill(String text) {
        getElement().sendKeys(text);
    }

    public void scrollIntoView() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getElement());
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
