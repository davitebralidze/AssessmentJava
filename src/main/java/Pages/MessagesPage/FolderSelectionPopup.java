package Pages.MessagesPage;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;

public class FolderSelectionPopup {

    static ButtonElement myDocumentsButtonInPopup = new ButtonElement(By.xpath("//*[@id=\"gwt-uid-68@538837161\"]/div[1]/div[2]"));
    static ButtonElement saveButtonOnTheFoldersPopup = new ButtonElement(By.xpath("//div[@class='btn GCSDBRWBO defaultBtn']"));

    public static void clickOnMyDocuments() {
        myDocumentsButtonInPopup.click();
    }

    public static void clickOnSaveButton() {
        saveButtonOnTheFoldersPopup.click();
    }
}
