package ElementWrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputElement extends BaseElement{
    public InputElement(WebDriver driver, By locator, String elementName) {
        super(driver, locator, elementName);
    }

    public InputElement(WebDriver driver, By locator) {
        super(driver, locator, "Input Element");
    }
}
