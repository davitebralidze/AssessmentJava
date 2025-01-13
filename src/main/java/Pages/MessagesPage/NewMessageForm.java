package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import ElementWrappers.CheckboxElement;
import ElementWrappers.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewMessageForm {

    ButtonElement attachmentButton;
    InputElement attachFromComputerButtonInput;
    InputElement emailReceiverInput;
    ButtonElement sendButton;
    InputElement subjectInput;
    CheckboxElement checkBoxForTheUploadedFile;

    public NewMessageForm(WebDriver driver) {
        attachmentButton = new ButtonElement(driver, By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div[2]/div/div[5]/div/div[2]/div/div[3]/div/div[2]/div/div[1]/table/tbody/tr[1]/td[3]/div/a[2]"), "Attachment button");
        attachFromComputerButtonInput = new InputElement(driver, By.xpath("//input[@name='docgwt-uid-33']\n"), "Attach from your computer button");
        emailReceiverInput = new InputElement(driver, By.xpath("//*[@id=\"mailTo\"]/input"), "Email receiver input box");
        sendButton = new ButtonElement(driver, By.xpath("//*[@id=\"mailSend\"]/div/div[1]"), "Send email button");
        subjectInput = new InputElement(driver, By.id("mailSubject"), "Email subject input");
        checkBoxForTheUploadedFile = new CheckboxElement(driver, By.xpath("//b[@class='checkIcon']"), "Checkbox for the uploaded file");
    }

    public void clickOnAttachmentButton() {
        attachmentButton.click();
    }

    public void uploadFileFromYourComputer(String filePath) {
        attachFromComputerButtonInput.fill(filePath);
        checkBoxForTheUploadedFile.waitElementVisibility();
    }

    public void fillEmailReceiverInput(String text) {
        emailReceiverInput.fill(text);
    }

    public void fillSubjectInput(String text) {
        subjectInput.fill(text);
    }

    public void clickOnSendButton() {
        sendButton.click();
    }
}
