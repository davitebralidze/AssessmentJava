package Pages.LoginPage;

import ElementWrappers.BaseElement;
import ElementWrappers.ButtonElement;
import ElementWrappers.InputElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LogInPage {
    static InputElement username = new InputElement(By.id("UserID"), "Username input field");
    static InputElement password = new InputElement(By.id("Password"), "Password");
    static ButtonElement enterButton = new ButtonElement(By.className("btn"), "Enter button");
    static BaseElement logInSpinner = new BaseElement(By.className("progress"));

    @Step("Log In to the mailbox")
    public static void logIn(String email, String password) {
        fillUsernameField(email);
        fillPasswordField(password);
        clickOnEnterButton();
        //assert the messages page to be opened
    }

    @Step("Fill the Username field")
    public static void fillUsernameField(String text) {
        username.fill(text);
    }

    @Step("Fill the Password field")
    public static void fillPasswordField(String text) {
        password.fill(text);
    }

    @Step("Click on the Enter button")
    public static void clickOnEnterButton() {
        enterButton.click();
    }

    @Step("Wait for the user to be logged in")
    public static void waitForUserToBeLoggedIn() {
        logInSpinner.waitElementInvisibility();
    }
}
