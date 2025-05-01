package PageCommonComponents;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;

public class HeaderBar {
    static ButtonElement messagesButton = new ButtonElement(By.id("nav-mail"), "Messages Button");
    static ButtonElement documentsButton = new ButtonElement(By.id("nav-docs"), "Documents Button");

    public static void clickOnMessagesButton() {
        messagesButton.click();
    }

    public static void clickOnDocumentsButton() {
        documentsButton.click();
    }

    public enum Pages {
        MESSAGES,
        DOCUMENTS
    }
}
