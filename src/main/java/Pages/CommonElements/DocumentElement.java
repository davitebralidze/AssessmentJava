package Pages.CommonElements;

import ElementWrappers.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DocumentElement extends BaseElement{

    String fileName;

    public DocumentElement(WebDriver driver, String fileName) {
        super(driver, By.xpath(String.format("//div[contains(@class, 'GCSDBRWBPJB') and contains(text(), '%s')]", fileName)), fileName);
        this.fileName = fileName;
    }

    public void dragAndDrop(WebElement targetElement) {

    }

}
