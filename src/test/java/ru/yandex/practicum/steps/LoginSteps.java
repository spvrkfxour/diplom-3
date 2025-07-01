package ru.yandex.practicum.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static ru.yandex.practicum.constant.EnvConst.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.api.UserApi;
import ru.yandex.practicum.dto.CreateUserRequest;
import ru.yandex.practicum.dto.LoginUserRequest;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import java.time.Duration;


public class LoginSteps {

    private final LoginPage loginPage;
    private final MainPage mainPage;
    private final AccountPageSteps accountPageSteps;
    private final UserApi userApi = new UserApi();
    private WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.mainPage = new MainPage(driver);
        this.accountPageSteps = new AccountPageSteps(driver);
        this.driver = driver;
    }

    @Step("Login user")
    public void loginUser(String email, String password) {
        loginPage.loginUser(email, password);
    }

    @Step("Create user")
    public void createUser(CreateUserRequest request) {
        Response response = userApi.createUser(request);
        Allure.step("Create user: " + response.getBody().asString());
    }

    @Step("Check user is login in account page")
    public void checkUserIsLogin() {
        mainPage.clickAccountProfileButton();
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(ACCOUNT_PROFILE_PAGE_URL));
        assertEquals("Account profile page do not open", ACCOUNT_PROFILE_PAGE_URL, driver.getCurrentUrl());
    }

    @Step("Redirect to main page")
    public void checkLoginRedirectToMain() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(URL));
        assertEquals("Incorrect URL after login user", URL, driver.getCurrentUrl());
    }

    @Step("Check login user is correct")
    public void checkLoginUser(String email, String password) {
        loginUser(email, password);
        checkLoginRedirectToMain();
        checkUserIsLogin();
        accountPageSteps.checkAccountEmail(email);
    }

    @Step("Add access token to local storage")
    public void addAccessTokenToLocalStorage(LoginUserRequest request) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('accessToken', '" + userApi.getAccessToken(request) + "');");
    }

    @Step("Check null access token in local storage")
    public void checkNullAccessTokenInLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String token = (String) js.executeScript("return localStorage.getItem('accessToken');");
        assertNull("Access token not delete after logout", token);
    }
}
