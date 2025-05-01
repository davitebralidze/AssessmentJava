package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import PageCommonComponents.HeaderBar;
import org.openqa.selenium.By;

public class MessagesPage {

    static ButtonElement newMessageButton = new ButtonElement(By.id("mailNewBtn"), "New message button");
    static ButtonElement refreshButton = new ButtonElement(By.className("icon16-Refresh"), "Refresh button");

    public static void clickOnNewMessageButton() {
        newMessageButton.click();
    }

    public static void clickOnRefreshButton() {
        refreshButton.click();
    }

    public static void sendEmail(String receiver, String subject, String filePath) {
        NewMessageForm.fillEmailReceiverInput(receiver);
        NewMessageForm.fillSubjectInput(subject);
        NewMessageForm.clickOnAttachmentButton();
        NewMessageForm.uploadFileFromYourComputer(filePath);
        NewMessageForm.clickOnSendButton();
    }

    public static void navigateTo(HeaderBar.Pages pageName) {
        switch (pageName) {
            case MESSAGES -> HeaderBar.clickOnMessagesButton();
            case DOCUMENTS -> HeaderBar.clickOnDocumentsButton();
            default ->
                    throw new IllegalArgumentException("The option you provided for navigation is unavailable. Provided option: " + pageName);
        }
    }

}