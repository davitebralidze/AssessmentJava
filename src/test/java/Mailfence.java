import PageCommonComponents.HeaderBar;
import Util.BaseUtility;
import org.testng.annotations.Test;

public class Mailfence extends BaseUtility {

    @Test
    public void uploadFile() {
        String subject = faker.lorem().characters(10);

        landingPage.clickOnSignInButton();
        logInPage.logIn(propertyLoader.returnConfigValue("userEmail"), propertyLoader.returnConfigValue("password"));
        logInPage.waitForUserToBeLoggedIn();
        messagesPage.navigateTo(HeaderBar.Pages.MESSAGES);
        messagesPage.clickOnNewMessageButton();
        messagesPage.sendEmail(propertyLoader.returnConfigValue("userEmail"), subject, "C:\\Users\\davit.ebralidze\\IdeaProjects\\Mailfence\\checkme.pdf");
        messagesPage.inboxFolder.waitForTheMessageInInbox(subject);
        messagesPage.inboxFolder.openTheMessage(subject);
        messagesPage.inboxFolder.saveTheAttachmentOfTheOpenedMessageInDocuments("checkme");
        messagesPage.navigateTo(HeaderBar.Pages.DOCUMENTS);
    }

}
