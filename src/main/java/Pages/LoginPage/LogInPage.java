package Pages.LoginPage;

import ElementWrappers.BaseElement;
import ElementWrappers.ButtonElement;
import ElementWrappers.InputElement;
import org.openqa.selenium.By;

public class LogInPage {
    static InputElement username = new InputElement(By.id("UserID"), "Username input field");
    static InputElement password = new InputElement(By.id("Password"), "Password");
    static ButtonElement enterButton = new ButtonElement(By.className("btn"), "Enter button");
    static BaseElement logInSpinner = new BaseElement(By.className("progress"));

    public static void logIn(String email, String password) {
        fillUsernameField(email);
        fillPasswordField(password);
        clickOnEnterButton();
        //assert the messages page to be opened
    }

    public static void fillUsernameField(String text) {
        username.fill(text);
    }

    public static void fillPasswordField(String text) {
        password.fill(text);
    }

    public static void clickOnEnterButton() {
        enterButton.click();
    }

    public static void waitForUserToBeLoggedIn() {
        logInSpinner.waitElementInvisibility();
    }
}
