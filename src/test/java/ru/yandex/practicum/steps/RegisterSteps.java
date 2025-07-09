package ru.yandex.practicum.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.constant.EnvConst.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.dto.LoginUserRequest;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.RegisterPage;


public class RegisterSteps {

    private final RegisterPage registerPage;
    private final LoginPage loginPage;
    private final UserApi userApi = new UserApi();
    private WebDriver driver;
    private String accessToken;

    public RegisterSteps(WebDriver driver) {
        this.registerPage = new RegisterPage(driver);
        this.loginPage = new LoginPage(driver);
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
        loginPage.redirectToLoginPageLoader();
        assertEquals("Incorrect URL after registration user", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Step("Correct password error message")
    public void checkIncorrectPasswordErrorMessage() {
        registerPage.passwordInputErrorLoader();
        assertTrue("Password error message element not on page", driver.findElement(registerPage.getRegisterPasswordInputError()).isDisplayed());
        assertEquals("Error message text is different", INCORRECT_PASSWORD_REGISTER_ERROR_MSG,
                registerPage.getRegisterPasswordInputErrorText());
        Allure.step("Password error message: " + registerPage.getRegisterPasswordInputErrorText());
    }

    @Step("Get access token")
    public String getAccessToken(LoginUserRequest request) {
        return userApi.getAccessToken(request);
    }

    @Step("Delete user")
    public void deleteUser(LoginUserRequest request) {
        accessToken = getAccessToken(request);
        if (accessToken != null) {
            Response response = userApi.deleteUser(accessToken);
            Allure.step("Delete user: " + response.getBody().asString());
        }
    }
}
