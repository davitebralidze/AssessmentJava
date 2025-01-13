package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import PageCommonComponents.HeaderBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesPage {

    HeaderBar headerBar;
    ButtonElement newMessageButton;
    ButtonElement refreshButton;
    NewMessageForm newMessageForm;
    public InboxFolder inboxFolder;

    public MessagesPage(WebDriver driver) {
        headerBar = new HeaderBar(driver);
        newMessageButton = new ButtonElement(driver, By.id("mailNewBtn"), "New message button");
        refreshButton = new ButtonElement(driver, By.className("icon16-Refresh"), "Refresh button");
        newMessageForm = new NewMessageForm(driver);
        inboxFolder = new InboxFolder(driver, this); // Pass this instance of MessagesPage
    }

    public void clickOnNewMessageButton() {
        newMessageButton.click();
    }

    public void clickOnRefreshButton() {
        refreshButton.click();
    }

    public void sendEmail(String receiver, String subject, String filePath) {
        newMessageForm.fillEmailReceiverInput(receiver);
        newMessageForm.fillSubjectInput(subject);
        newMessageForm.clickOnAttachmentButton();
        newMessageForm.uploadFileFromYourComputer(filePath);
        newMessageForm.clickOnSendButton();
    }

    public void navigateTo(HeaderBar.Pages pageName) {
        switch (pageName) {
            case MESSAGES -> headerBar.clickOnMessagesButton();
            case DOCUMENTS -> headerBar.clickOnDocumentsButton();
            default ->
                    throw new IllegalArgumentException("The option you provided for navigation is unavailable. Provided option: " + pageName);
        }
    }
}