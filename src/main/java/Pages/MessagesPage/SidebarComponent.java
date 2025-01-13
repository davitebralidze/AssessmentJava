package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SidebarComponent {

    ButtonElement inboxButton;

    public SidebarComponent(WebDriver driver) {
        inboxButton = new ButtonElement(driver, By.id("treeInbox"), "Inbox folder button");
    }

    public void clickOnInboxButton() {
        inboxButton.click();
    }

    public void navigateTo(Pages pageName) {
        switch (pageName) {
            case INBOX -> clickOnInboxButton();
            default ->
                    throw new IllegalArgumentException("The option you provided for navigation is unavailable. Provided option: " + pageName);
        }
    }

    public enum Pages {
        INBOX
    }
}
