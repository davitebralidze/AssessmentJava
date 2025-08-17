import PageCommonComponents.HeaderBar;
import Pages.LandingPage.LandingPage;
import Pages.LoginPage.LogInPage;
import Pages.MessagesPage.InboxFolder;
import Pages.MessagesPage.MessagesPage;
import Util.*;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

public class MailfenceTest extends BaseUtility {

    DummyFile dummyFile;

    @Test
    public void uploadFile() {
        String subject = Faker.instance().lorem().characters(10);
        dummyFile = new DummyFile(subject, DummyFile.FileFormat.PDF);
        dummyFile.createLoremIpsumFile();

        LandingPage.clickOnSignInButton();
        LogInPage.logIn(PropertyLoader.getProperty("userEmail"), PropertyLoader.getProperty("password"));
        LogInPage.waitForUserToBeLoggedIn();
        MessagesPage.navigateTo(HeaderBar.Pages.MESSAGES);
        MessagesPage.clickOnNewMessageButton();
        MessagesPage.sendEmail(PropertyLoader.getProperty("userEmail"), subject, dummyFile.getFilePath());
        InboxFolder.waitForTheMessageInInbox(subject);
        InboxFolder.openTheMessage(subject);
        InboxFolder.saveTheAttachmentOfTheOpenedMessageInDocuments(dummyFile.getFileName());
        MessagesPage.navigateTo(HeaderBar.Pages.DOCUMENTS);
    }

    @AfterMethod
    public void deleteDummyFile(Method method) {
        if (method.getName().equals("uploadFile")) {
            dummyFile.deleteFile();
        }
    }
}
