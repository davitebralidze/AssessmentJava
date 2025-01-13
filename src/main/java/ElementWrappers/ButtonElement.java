package ElementWrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonElement extends BaseElement{

    public ButtonElement(WebDriver driver, By locator, String elementName) {
        super(driver, locator, elementName);
    }

    public ButtonElement(WebDriver driver, By locator) {
        super(driver, locator, "Button Element");
    }

}
