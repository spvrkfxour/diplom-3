package ru.yandex.practicum.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.constant.EnvConst.*;


public class LoginSteps {

    private final LoginPage loginPage;
    private final MainPage mainPage;
    private final UserApi userApi = new UserApi();
    private WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.mainPage = new MainPage(driver);
        this.driver = driver;
    }

    @Step("Open login page")
    public void openLoginPage() {
        loginPage.openPage();
    }

    @Step("Login user")
    public void loginUser(String email, String password) {
        loginPage.loginUser(email, password);
    }

    @Step("Create user")
    public void createUser(String email, String password, String name) {
        Response response = userApi.createUser(email, password, name);
        Allure.step("Create user: " + response.getBody().asString());
    }

    @Step("Check user is login")
    public void checkLoginUser() {
        mainPage.clickAccountProfileButton();
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(ACCOUNT_PROFILE_URL));
        assertEquals("Account profile page do not open", ACCOUNT_PROFILE_URL, driver.getCurrentUrl());
    }

    @Step("Redirect to main page")
    public void checkLoginRedirectToMain() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(URL));
        assertEquals("Incorrect URL after login user", URL, driver.getCurrentUrl());
    }
}
