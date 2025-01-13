package PageCommonComponents;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderBar {
    ButtonElement messagesButton;
    ButtonElement documentsButton;

    public HeaderBar(WebDriver driver) {
        messagesButton = new ButtonElement(driver, By.id("nav-mail"), "Messages Button");
        documentsButton = new ButtonElement(driver, By.id("nav-docs"), "Documents Button");
    }

    public void clickOnMessagesButton() {
        messagesButton.click();
    }

    public void clickOnDocumentsButton() {
        documentsButton.click();
    }

    public enum Pages {
        MESSAGES,
        DOCUMENTS
    }
}
