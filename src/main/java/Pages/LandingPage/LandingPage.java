package Pages.LandingPage;

import ElementWrappers.ButtonElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LandingPage {

    static ButtonElement logInButton = new ButtonElement(By.id("signin"), "Log In button");

    @Step("Click on Sign In Button")
    public static void clickOnSignInButton() {
        logInButton.click();
    }
}
