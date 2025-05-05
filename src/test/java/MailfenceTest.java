import PageCommonComponents.HeaderBar;
import Pages.LandingPage.LandingPage;
import Pages.LoginPage.LogInPage;
import Pages.MessagesPage.InboxFolder;
import Pages.MessagesPage.MessagesPage;
import Util.BaseUtility;
import Util.DummyFile;
import Util.PropertyLoader;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
public class MailfenceTest extends BaseUtility {

    @Test
    public void uploadFile() {
        String subject = Faker.instance().lorem().characters(10);
        DummyFile dummyFile = new DummyFile(subject, DummyFile.FileFormat.PDF);
        dummyFile.createLoremIpsumFile();
        try {
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
        } finally {
            dummyFile.deleteFile();
        }
    }
}
