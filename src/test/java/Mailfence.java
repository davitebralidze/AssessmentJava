import PageCommonComponents.HeaderBar;
import Util.BaseUtility;
import Util.DummyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import java.awt.event.InputEvent;

import java.awt.*;

public class Mailfence extends BaseUtility {

    @Test
    public void uploadFile() {
        String subject = faker.lorem().characters(10);
        String fileName = faker.lorem().characters(5);
        DummyFile dummyPDF = new DummyFile(fileName, DummyFile.FileFormat.PDF);
        dummyPDF.createLoremIpsumFile();

        landingPage.clickOnSignInButton();
        logInPage.logIn(propertyLoader.returnConfigValue("userEmail"), propertyLoader.returnConfigValue("password"));
        logInPage.waitForUserToBeLoggedIn();
        messagesPage.navigateTo(HeaderBar.Pages.MESSAGES);
        messagesPage.clickOnNewMessageButton();
        messagesPage.sendEmail(propertyLoader.returnConfigValue("userEmail"), subject, dummyPDF.getFilePath());
        messagesPage.inboxFolder.waitForTheMessageInInbox(subject);
        messagesPage.inboxFolder.openTheMessage(subject);
        messagesPage.inboxFolder.saveTheAttachmentOfTheOpenedMessageInDocuments(dummyPDF.getFileName());
        messagesPage.navigateTo(HeaderBar.Pages.DOCUMENTS);


//        Actions actions = new Actions(driver);
//        WebElement element = driver.findElement(By.xpath(String.format("//div[contains(@class, 'GCSDBRWBPJB') and contains(text(), '%s')]", fileName)));
//        WebElement trash = driver.findElement(By.xpath("//*[@id=\"doc_tree_trash\"]"));
//        Point location = element.getLocation();
//        int x = location.getX();
//        int y = location.getY();
//
//        try {
////            actions.dragAndDrop(element, trash);
//            Robot robot = new Robot();
//            robot.mouseMove(x, y);
//            robot.mouseMove(x, y + 200);
//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.mouseMove(x, y);
//            actions.moveToElement(element).perform();
//            actions.moveToElement(element);
//            actions.moveToElement(trash);
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }

        dummyPDF.deleteFile();
    }
}