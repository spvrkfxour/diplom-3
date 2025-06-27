package ru.yandex.practicum.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.pages.RegisterPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.constant.EnvConst.*;


public class RegisterSteps {

    private final RegisterPage registerPage;
    private final UserApi userApi = new UserApi();
    private WebDriver driver;
    private String accessToken;

    public RegisterSteps(WebDriver driver) {
        this.registerPage = new RegisterPage(driver);
        this.driver = driver;
    }

    @Step("Open register page")
    public void openRegisterPage() {
        registerPage.openPage();
    }

    @Step("Register user")
    public void registerUser(String name, String email, String password) {
        registerPage.registerUser(name, email, password);
    }

    @Step("Login from register page button")
    public void loginUserFromRegisterPageButton() {
        registerPage.clickLoginButton();
    }

    @Step("Redirect to login page")
    public void checkRegisterRedirectToLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlContains("/login"));
        assertEquals("Incorrect URL after registration user", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Step("Correct password error message")
    public void checkIncorrectPasswordErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(registerPage.getRegisterPasswordInputErrorElement()));
        assertTrue("Password error message element not on page", driver.findElement(registerPage.getRegisterPasswordInputErrorElement()).isDisplayed());
        assertEquals("Error message text is different", INCORRECT_PASSWORD_REGISTER_ERROR_MSG,
                driver.findElement(registerPage.getRegisterPasswordInputErrorElement()).getText());
        Allure.step("Password error message: " + driver.findElement(registerPage.getRegisterPasswordInputErrorElement()).getText());
    }

    @Step("Delete user")
    public void deleteUser(String email, String password) {
        accessToken = userApi.getAccessToken(email, password);
        if (accessToken != null) {
            Response response = userApi.deleteUser(accessToken);
            Allure.step("Delete user: " + response.getBody().asString());
        }
    }
}
