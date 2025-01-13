package Pages.LoginPage;

import ElementWrappers.BaseElement;
import ElementWrappers.ButtonElement;
import ElementWrappers.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    InputElement username;
    InputElement password;
    ButtonElement enterButton;
    BaseElement logInSpinner;

    public LogInPage(WebDriver driver) {
        username = new InputElement(driver, By.id("UserID"), "Username input field");
        password = new InputElement(driver, By.id("Password"), "Password");
        enterButton = new ButtonElement(driver, By.className("btn"), "Enter button");
        logInSpinner = new BaseElement(driver, By.className("progress"));
    }

    public void logIn(String email, String password) {
        fillUsernameField(email);
        fillPasswordField(password);
        clickOnEnterButton();

        //assert the messages page to be opened
    }

    public void fillUsernameField(String text) {
        username.fill(text);
    }

    public void fillPasswordField(String text) {
        password.fill(text);
    }

    public void clickOnEnterButton() {
        enterButton.click();
    }

    public void waitForUserToBeLoggedIn() {
        logInSpinner.waitElementInvisibility();
    }
}
