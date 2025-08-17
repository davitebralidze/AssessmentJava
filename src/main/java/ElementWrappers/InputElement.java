package ElementWrappers;

import org.openqa.selenium.By;

public final class InputElement extends BaseElement{
    public InputElement(By locator, String elementName) {
        super( locator, elementName);
    }

    public InputElement(By locator) {
        super( locator, "Input Element");
    }
}
