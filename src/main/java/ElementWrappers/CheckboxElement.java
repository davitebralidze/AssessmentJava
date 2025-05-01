package ElementWrappers;

import org.openqa.selenium.By;

public class CheckboxElement extends BaseElement {
    public CheckboxElement(By locator, String elementName) {
        super(locator, elementName);
    }

    public CheckboxElement(By locator) {
        super( locator, "Checkbox Element");
    }
}
