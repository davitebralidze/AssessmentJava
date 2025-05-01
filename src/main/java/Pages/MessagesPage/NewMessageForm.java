package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import ElementWrappers.CheckboxElement;
import ElementWrappers.InputElement;
import org.openqa.selenium.By;

public class NewMessageForm {

    static ButtonElement attachmentButton = new ButtonElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div[2]/div/div[5]/div/div[2]/div/div[3]/div/div[2]/div/div[1]/table/tbody/tr[1]/td[3]/div/a[2]"), "Attachment button");
    static InputElement attachFromComputerButtonInput = new InputElement(By.xpath("//input[@name='docgwt-uid-33']\n"), "Attach from your computer button");
    static InputElement emailReceiverInput = new InputElement(By.xpath("//*[@id=\"mailTo\"]/input"), "Email receiver input box");
    static ButtonElement sendButton = new ButtonElement(By.xpath("//*[@id=\"mailSend\"]/div/div[1]"), "Send email button");
    static InputElement subjectInput = new InputElement(By.id("mailSubject"), "Email subject input");
    static CheckboxElement checkBoxForTheUploadedFile = new CheckboxElement(By.xpath("//b[@class='checkIcon']"), "Checkbox for the uploaded file");

    public static void clickOnAttachmentButton() {
        attachmentButton.click();
    }

    public static void uploadFileFromYourComputer(String filePath) {
        attachFromComputerButtonInput.fill(filePath);
        checkBoxForTheUploadedFile.waitElementVisibility();
    }

    public static void fillEmailReceiverInput(String text) {
        emailReceiverInput.fill(text);
    }

    public static void fillSubjectInput(String text) {
        subjectInput.fill(text);
    }

    public static void clickOnSendButton() {
        sendButton.click();
    }
}
