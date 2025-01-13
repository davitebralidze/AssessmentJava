package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FolderSelectionPopup {

    ButtonElement myDocumentsButtonInPopup;
    ButtonElement saveButtonOnTheFoldersPopup;

    public FolderSelectionPopup(WebDriver driver) {
        myDocumentsButtonInPopup = new ButtonElement(driver, By.xpath("//*[@id=\"gwt-uid-68@538837161\"]/div[1]/div[2]"));
        saveButtonOnTheFoldersPopup = new ButtonElement(driver, By.xpath("//div[@class='btn GCSDBRWBO defaultBtn']"));
    }

    public void clickOnMyDocuments() {
        myDocumentsButtonInPopup.click();
    }

    public void clickOnSaveButton() {

        saveButtonOnTheFoldersPopup.click();
    }
}
