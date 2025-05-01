package Pages.LandingPage;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;

public class LandingPage {

    static ButtonElement logInButton = new ButtonElement(By.id("signin"), "Log In button");

    public static void clickOnSignInButton() {
        logInButton.click();
    }
}
