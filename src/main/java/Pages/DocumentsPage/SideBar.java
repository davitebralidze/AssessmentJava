package Pages.DocumentsPage;

import ElementWrappers.BaseElement;
import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SideBar extends BaseElement {

    WebDriver driver;
    ButtonElement trashButton;

    public SideBar(WebDriver driver) {
        super(driver, By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div[3]"), "SideBar");
        this.driver =driver;
        this.trashButton = new ButtonElement(driver, By.xpath("//*[@id=\"doc_tree_trash\"]"));
    }

}
