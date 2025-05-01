import PageCommonComponents.HeaderBar;
import Pages.LandingPage.LandingPage;
import Pages.LoginPage.LogInPage;
import Pages.MessagesPage.InboxFolder;
import Pages.MessagesPage.MessagesPage;
import Util.BaseUtility;
import Util.PropertyLoader;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class Mailfence extends BaseUtility {

    @Test
    public void uploadFile() {
        String subject = Faker.instance().lorem().characters(10);

        LandingPage.clickOnSignInButton();
        Assert.assertEquals(false, true);
//        LogInPage.logIn(PropertyLoader.getProperty("userEmail"), PropertyLoader.getProperty("password"));
//        LogInPage.waitForUserToBeLoggedIn();
//        MessagesPage.navigateTo(HeaderBar.Pages.MESSAGES);
//        MessagesPage.clickOnNewMessageButton();
//        MessagesPage.sendEmail(PropertyLoader.getProperty("userEmail"), subject, System.getProperty("user.dir")+ File.separator+"checkme.pdf");
//        InboxFolder.waitForTheMessageInInbox(subject);
//        InboxFolder.openTheMessage(subject);
//        InboxFolder.saveTheAttachmentOfTheOpenedMessageInDocuments("checkme");
//        MessagesPage.navigateTo(HeaderBar.Pages.DOCUMENTS);
    }
}
