package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.PasswordRecoveryPage;


public class PasswordRecoveryPageSteps {
    private final PasswordRecoveryPage passwordRecoveryPage;

    public PasswordRecoveryPageSteps(WebDriver driver) {
        this.passwordRecoveryPage = new PasswordRecoveryPage(driver);
    }

    @Step("Open password recovery page")
    public void openPasswordRecoveryPage() {
        passwordRecoveryPage.openPage();
    }

    @Step("Login from password recovery page button")
    public void loginUserFromPasswordRecoveryPageButton() {
        passwordRecoveryPage.clickLoginButton();
    }
}
