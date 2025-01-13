package ElementWrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckboxElement extends BaseElement {
    public CheckboxElement(WebDriver driver, By locator, String elementName) {
        super(driver, locator, elementName);
    }

    public CheckboxElement(WebDriver driver, By locator) {
        super(driver, locator, "Checkbox Element");
    }
}
