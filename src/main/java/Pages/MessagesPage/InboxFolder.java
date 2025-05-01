package Pages.MessagesPage;

import ElementWrappers.BaseElement;
import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;

public class InboxFolder {
    static ButtonElement optionsDropdownButtonOfTheAttachmentOfTheReceivedMessage = new ButtonElement(By.cssSelector("b.icon-Arrow-down"));
    static ButtonElement saveInDocumentsButtonOfTheDropdown = new ButtonElement(By.xpath("/html/body/div[5]/div/ul/li[3]/a"));

    public static BaseElement getLastReceivedMessage(String subject) {
        return new BaseElement(By.xpath("//div[contains(@class, 'listSubject') and text()='" + subject + "']"));
    }

    public static BaseElement getAttachmentOfTheReceivedMessage(String fileName) {
        return new BaseElement(By.xpath("//a[contains(@title, '"+fileName+"')]"));
    }

    public static void waitForTheMessageInInbox(String messageSubject) {
        boolean isLastReceivedMessageVisible;
        int retry = 0;
        do {
            MessagesPage.clickOnRefreshButton();
            isLastReceivedMessageVisible = getLastReceivedMessage(messageSubject).isDisplayed();
            retry++;
        } while (!isLastReceivedMessageVisible && retry < 20);
    }

    public static void openTheMessage(String messageSubject) {
        getLastReceivedMessage(messageSubject).click();
    }

    public static void saveTheAttachmentOfTheOpenedMessageInDocuments(String fileName) {
        getAttachmentOfTheReceivedMessage(fileName).hover();
        optionsDropdownButtonOfTheAttachmentOfTheReceivedMessage.click();
        saveInDocumentsButtonOfTheDropdown.click();
        FolderSelectionPopup.clickOnMyDocuments();
        FolderSelectionPopup.clickOnSaveButton();
    }

}
