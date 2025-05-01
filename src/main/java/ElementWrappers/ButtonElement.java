package ElementWrappers;

import org.openqa.selenium.By;

public class ButtonElement extends BaseElement{

    public ButtonElement(By locator, String elementName) {
        super(locator, elementName);
    }

    public ButtonElement(By locator) {
        super(locator, "Button Element");
    }

}
