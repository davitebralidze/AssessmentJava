package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;

public class SidebarComponent {

    static ButtonElement inboxButton = new ButtonElement(By.id("treeInbox"), "Inbox folder button");

    public static void clickOnInboxButton() {
        inboxButton.click();
    }

    public static void navigateTo(Pages pageName) {
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
