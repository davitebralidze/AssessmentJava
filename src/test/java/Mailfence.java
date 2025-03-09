import PageCommonComponents.HeaderBar;
import Util.BaseUtility;
import Util.DummyFile;
import org.testng.annotations.Test;

public class Mailfence extends BaseUtility {

    @Test
    public void uploadFile() {
        String subject = faker.lorem().characters(10);
        DummyFile dummyPDF = new DummyFile("Test", DummyFile.FileFormat.PDF);
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

        dummyPDF.deleteFile();
    }
}