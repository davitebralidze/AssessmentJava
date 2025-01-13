package Pages.MessagesPage;

import ElementWrappers.BaseElement;
import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InboxFolder {
    WebDriver driver;
    ButtonElement optionsDropdownButtonOfTheAttachmentOfTheReceivedMessage;
    ButtonElement saveInDocumentsButtonOfTheDropdown;
    FolderSelectionPopup folderSelectionPopup;
    MessagesPage messagesPage;

    public InboxFolder(WebDriver driver, MessagesPage messagesPage) {
        this.driver = driver;
        this.messagesPage = messagesPage;
        optionsDropdownButtonOfTheAttachmentOfTheReceivedMessage = new ButtonElement(driver, By.cssSelector("b.icon-Arrow-down"));
        saveInDocumentsButtonOfTheDropdown = new ButtonElement(driver, By.xpath("/html/body/div[5]/div/ul/li[3]/a"));
        folderSelectionPopup = new FolderSelectionPopup(driver);
    }

    public BaseElement getLastReceivedMessage(String subject) {
        return new BaseElement(driver, By.xpath("//div[contains(@class, 'listSubject') and text()='" + subject + "']"));
    }

    public BaseElement getAttachmentOfTheReceivedMessage(String fileName) {
        return new BaseElement(driver, By.xpath("//a[contains(@title, '"+fileName+"')]"));
    }

    public void waitForTheMessageInInbox(String messageSubject) {
        boolean isLastReceivedMessageVisible = false;
        int retry = 0;
        do {
            messagesPage.clickOnRefreshButton();
            isLastReceivedMessageVisible = getLastReceivedMessage(messageSubject).isDisplayed();
            retry++;
        } while (!isLastReceivedMessageVisible && retry < 20);
    }

    public void openTheMessage(String messageSubject) {
        getLastReceivedMessage(messageSubject).click();
    }

    public void saveTheAttachmentOfTheOpenedMessageInDocuments(String fileName) {
        getAttachmentOfTheReceivedMessage(fileName).hover();
        optionsDropdownButtonOfTheAttachmentOfTheReceivedMessage.click();
        saveInDocumentsButtonOfTheDropdown.click();
        folderSelectionPopup.clickOnMyDocuments();
        folderSelectionPopup.clickOnSaveButton();
    }


}
