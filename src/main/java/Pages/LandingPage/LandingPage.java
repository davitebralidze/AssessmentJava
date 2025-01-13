package Pages.LandingPage;

import ElementWrappers.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LandingPage {

    ButtonElement logInButton;

    public LandingPage(WebDriver driver) {
        logInButton = new ButtonElement(driver, By.id("signin"), "Log In button");
    }


    public void clickOnSignInButton() {
        logInButton.click();
    }
}
